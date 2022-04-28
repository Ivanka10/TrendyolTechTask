package com.trendyol.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {
    private final Properties properties = new Properties();
    private FileInputStream inputStream = null;
    private String filePath = null;
    private final Logger logger = LogManager.getLogger(PropertyReader.class);

    public String propertyFile(String keyToFile){
        try{
            inputStream = new FileInputStream("src/test/resources/test.properties");
            properties.load(inputStream);
            filePath = properties.getProperty(keyToFile);
        }catch (IOException e){
            logger.error(e.getClass());
        }finally {
            if(!Objects.isNull(inputStream)){
                try{
                    inputStream.close();
                }catch (IOException e){
                    logger.error(e.getClass());
                }
            }
        }
        return filePath;
    }
}
