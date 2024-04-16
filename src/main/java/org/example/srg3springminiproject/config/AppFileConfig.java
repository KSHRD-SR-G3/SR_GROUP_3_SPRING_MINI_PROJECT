//package org.example.srg3springminiproject.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class AppFileConfig implements WebMvcConfigurer {
//
//    private final static String path = "src/main/resources/profile_images";
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //get profile images path to display
//        registry.addResourceHandler("/**").addResourceLocations("file: "+path+"/");
//    }
//}
