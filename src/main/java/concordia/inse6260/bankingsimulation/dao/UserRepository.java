package concordia.inse6260.bankingsimulation.dao;

/**
 * Created by ruixiangtan on 06/05/16.
 */

import concordia.inse6260.bankingsimulation.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}