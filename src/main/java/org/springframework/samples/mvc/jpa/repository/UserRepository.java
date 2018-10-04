package org.springframework.samples.mvc.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.mvc.jpa.entity.User;

/**
 * Created by lys on 2018/10/4.
 *
 * @author lys
 * @version 3.0.0-SNAPSHOT
 * @since 3.0.0-SNAPSHOT
 */

public interface UserRepository extends JpaRepository<User,Long> {
}
