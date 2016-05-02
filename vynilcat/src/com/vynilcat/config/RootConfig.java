package com.vynilcat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vynilcat.Album;
import com.vynilcat.data.DataRepository;
import com.vynilcat.exceptions.CVException;
import com.vynilcat.services.AdminService;

@Configuration
@ComponentScan(basePackageClasses={Album.class, WebInitializer.class, DataRepository.class, CVException.class, AdminService.class}, excludeFilters={@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)}) // Excluye WebConfig
public class RootConfig {

}
