package com.example.springbatchv1.config.batch;

import com.example.springbatchv1.model.Stock;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchStockReaderConfig {

    public static final String[] FILED_NAMES = {"symbol",
            "description",
            "category2",
            "category3",
            "GICSSector",
            "marketCap",
            "dividendYield",
            "priceToTTMEarnings",
            "priceToTTMSales",
            "priceToBookValue"};

    @Bean
    public FlatFileItemReader<Stock> stockCsvReader() {
        return new FlatFileItemReaderBuilder<Stock>()
                .name("stockCsvReader")
                .resource(new ClassPathResource("data/Stocks_in_the_SP_500_Index.csv"))
                .linesToSkip(1)
                .delimited()
                .names(FILED_NAMES)
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Stock.class);
                }})
                .build();
    }

    @Bean
    public LineMapper<Stock> lineMapper() {
        DefaultLineMapper<Stock> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(FILED_NAMES);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(stockFieldSetMapper());
        return lineMapper;
    }

    private FieldSetMapper<Stock> stockFieldSetMapper() {
        return fieldSet -> {
            Stock stock = new Stock(
                    fieldSet.readString("symbol"),
                    fieldSet.readString("description"),
                    fieldSet.readString("category2"),
                    fieldSet.readString("category3"),
                    fieldSet.readString("GICSSector"),
                    fieldSet.readString("marketCap"),
                    fieldSet.readString("dividendYield"),
                    fieldSet.readString("priceToTTMEarnings"),
                    fieldSet.readString("priceToTTMSales"),
                    fieldSet.readString("priceToBookValue")
            );
            return stock;
        };
    }
}
