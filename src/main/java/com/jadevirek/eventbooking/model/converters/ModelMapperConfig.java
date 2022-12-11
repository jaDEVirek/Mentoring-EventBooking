package com.jadevirek.eventbooking.model.converters;

import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * ModelMapperConfig as an example of loading bean from spring-xml context, called by methodName. In
 * the next stage it will be used in case of ORM mapping (e.g between DTO objects, and Entities).
 */
public class ModelMapperConfig {

  private static ModelMapper modelMapper;

  public ModelMapperConfig() throws Exception {
    if (modelMapper != null) {
      throw new Exception("Object already exist");
    }
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  public static ModelMapper getModelMapperInstance() {
    if (Objects.isNull(modelMapper)) {
      synchronized (ModelMapperConfig.class) {
        if (Objects.isNull(modelMapper)) {
          modelMapper = new ModelMapper();
        }
      }
    }
    modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE);
    return modelMapper;
  }
}
