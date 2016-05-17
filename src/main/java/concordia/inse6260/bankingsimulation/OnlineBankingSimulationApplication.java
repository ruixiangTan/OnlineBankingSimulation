package concordia.inse6260.bankingsimulation;

import concordia.inse6260.bankingsimulation.dao.UserRepository;
import concordia.inse6260.bankingsimulation.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static concordia.inse6260.bankingsimulation.service.AccountServices.initializeUser;

/**
 * Created by Ruixiang on 10/26/2015.
 */
@SpringBootApplication
public class OnlineBankingSimulationApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBankingSimulationApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        /*userRepository.save(initializeUser(new User()));
        userRepository.save(initializeUser(new User()));
        userRepository.save(initializeUser(new User()));*/
    }
}
