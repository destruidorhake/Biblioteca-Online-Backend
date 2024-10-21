package gustavo.gomes.bibliotecaOnlineBackend.config.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTaskUtil {

    @Autowired
    private RestTemplate restTemplate;

    // URL do seu endpoint
    private static final String TEST_URL = "https://biblioteca-online-backend-production.up.railway.app/api/auth/test"; // Altere para o URL apropriado

    @Scheduled(fixedRate = 43200000) // 12 horas em milissegundos
    public void executeTest() {
        try {
            String response = restTemplate.getForObject(TEST_URL, String.class);
            System.out.println("Response from test endpoint: " + response);
        } catch (Exception e) {
            System.err.println("Error calling test endpoint: " + e.getMessage());
        }
    }
}

