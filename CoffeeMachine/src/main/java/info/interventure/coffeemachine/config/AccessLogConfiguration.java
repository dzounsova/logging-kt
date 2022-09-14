package info.interventure.coffeemachine.config;

import ch.qos.logback.access.tomcat.LogbackValve;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessLogConfiguration {

    @Value("${logging.access.config}")
    private String configurationFileName;

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> accessLogsCustomizer() {
        return factory -> {
            var logbackValve = new LogbackValve();
            logbackValve.setFilename(configurationFileName);
            logbackValve.setAsyncSupported(true);
            logbackValve.setQuiet(true);
            factory.addContextValves(logbackValve);
        };
    }
}
