package info.interventure.coffeemachine.service;

import info.interventure.coffeemachine.model.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;

@Service
public class MilkService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    private final RestTemplate restTemplate;

    @Value("${custom.milk-service.base-url}")
    private String baseUrl;

    @Autowired
    public MilkService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getMilk(final Instruction instruction) {
        logger.info("Getting milk from milk tank");

        HttpHeaders headers = new HttpHeaders();
        headers.set(CORRELATION_ID_HEADER, MDC.get(CORRELATION_ID_HEADER));
        HttpEntity<Instruction> request = new HttpEntity<>(instruction, headers);
        restTemplate.postForEntity(baseUrl + "/milk/make", request, String.class);
    }
}
