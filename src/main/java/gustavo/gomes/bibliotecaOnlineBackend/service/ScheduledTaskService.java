package gustavo.gomes.bibliotecaOnlineBackend.service;

import gustavo.gomes.bibliotecaOnlineBackend.controller.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

    @Autowired
    private AuthController authController; // Injetando o AuthController

    // Método que será executado a cada 12 horas
    @Scheduled(fixedRate = 43200000) // 12 horas em milissegundos
    public void executeTask() {
        System.out.println("Executando tarefa agendada a cada 12 horas...");
        // Chame o método test() do AuthController
        authController.test();
    }
}

