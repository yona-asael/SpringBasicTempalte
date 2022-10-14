package com.deloitte.mule.tranning.config;

import com.deloitte.mule.tranning.converters.StatusConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig  extends WebMvcConfigurationSupport {

    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService formattingConversionService = super.mvcConversionService();
        formattingConversionService.addConverter(new StatusConverter());
        return formattingConversionService;
    }

}
