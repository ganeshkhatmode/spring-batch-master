package com.batch.user_processing.config;

import com.batch.user_processing.batch.UserItemProcessor;
import com.batch.user_processing.batch.UserItemWriter;
import com.batch.user_processing.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class SpringBatchConfig {



    @JobScope
    @Bean
    public FlatFileItemReader<User> reader(){
      return new FlatFileItemReaderBuilder<User>()
              .name("csv-reader")
              .resource(new ClassPathResource("users.csv"))
              .delimited()
              .names(new String[]{"name","mobileNumber","age","city"})
              .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                  setTargetType(User.class);
              }})
              .build();
    }

    @Bean
    public ItemProcessor<User,User> processor(){
        log.info("Item is processing in processor.");
        return new UserItemProcessor();
    }

    @Bean
    public ItemWriter<User> writerDB(){
        log.info("Item is writing in database.");
        return new UserItemWriter();
    }

    @JobScope
    @Bean
    public FlatFileItemWriter<User> writer(){
        log.info("Item is writing");
        return new FlatFileItemWriterBuilder<User>()
                .name("csv-writer")
                .resource(new FileSystemResource("resources/output.csv"))
                .lineAggregator(new DelimitedLineAggregator<User>(){{
                    setDelimiter(",");
                    setFieldExtractor(new BeanWrapperFieldExtractor<>(){{
                        setNames(new String[]{"name","mobileNumber","age","city"});
                    }});
                }})
                .headerCallback(writer -> writer.write("name,mobileNumber,age,city"))
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step){
        return new JobBuilder("csv-job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step-builder", jobRepository)
                .<User,User>chunk(10, platformTransactionManager)
                .reader(reader())
                .processor(processor())
//                .writer(writer())
                .writer(writerDB())
                .build();
    }
}
