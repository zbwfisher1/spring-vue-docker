package com.zbwfisher.datasource.configs;
import com.github.nickvl.xspring.core.log.aop.AOPLogger;
import com.github.nickvl.xspring.core.log.aop.UniversalLogAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Collections;
import java.util.Set;

/**
 * Created by zbw on 17/6/27.
 */


@Configuration
@EnableAspectJAutoProxy
public class LoggerConfig {

    private static final boolean SKIP_NULL_FIELDS = true;
    private static final int CROP_THRESHOLD = 7;
    private static final Set<String> EXCLUDE_SECURE_FIELD_NAMES = Collections.<String>emptySet();

    @Bean
    public AOPLogger getLoggerBean() {
        AOPLogger aopLogger = new AOPLogger();
        aopLogger.setLogAdapter(new UniversalLogAdapter(SKIP_NULL_FIELDS, CROP_THRESHOLD, EXCLUDE_SECURE_FIELD_NAMES));
        return aopLogger;
    }
}