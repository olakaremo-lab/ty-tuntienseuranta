package com.olak.tyotuntiseuranta;

import java.time.LocalDate;
import java.time.LocalTime;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import org.springframework.context.annotation.Import;

import com.olak.tyotuntiseuranta.web.WebSecurityConfig;
import com.olak.tyotuntiseuranta.web.domain.User;
import com.olak.tyotuntiseuranta.web.domain.UserRepository;
import com.olak.tyotuntiseuranta.web.domain.Work;
import com.olak.tyotuntiseuranta.web.domain.WorkRepository;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(WebSecurityConfig.class)
public class WorkRepositoryTest {
    @Autowired 
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;
    

    @Test 
    void testsave(){
        User user = new User();
        user.setUsername("testikäyttis");
        user.setFirstname("joku");
        user.setLastname("jokin");
        user.setPasswordHash("666");
        user.setRole("USER");

        userRepository.save(user);

        Work esim = new Work();
        esim.setTask("Testi");
        esim.setBreaktime(30);
        esim.setDate(LocalDate.of(2026, 11, 9));
        esim.setBeginning(LocalTime.of(9, 0));
        esim.setEnd(LocalTime.of(18, 0));
        esim.setUser(user);

        workRepository.save(esim);

        Work haettuesim = workRepository.findById(esim.getId()).orElseThrow();
        assertThat(haettuesim).isNotNull();
        assertThat(haettuesim.getTask()).isEqualTo("Testi");
        assertThat(haettuesim.getDate());
        assertThat(haettuesim.getBreaktime()).isEqualTo(30);
        assertThat(haettuesim.getUser().getUsername()).isEqualTo("testikäyttis");
    }
}
