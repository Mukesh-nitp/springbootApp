package com.salaj.app.salajwebapp.seeder;

import com.salaj.app.salajwebapp.address.model.City;
import com.salaj.app.salajwebapp.address.model.State;
import com.salaj.app.salajwebapp.io.entity.UserEntity;
import com.salaj.app.salajwebapp.io.repository.CityRepository;
import com.salaj.app.salajwebapp.io.repository.StateRepository;
import com.salaj.app.salajwebapp.io.repository.UserRepository;
import com.salaj.app.salajwebapp.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedCityTable();
        seedStateTable();
    }

    private void seedUsersTable() {
        String sql = "SELECT email FROM users U WHERE U.email = 'test@test.com' LIMIT 1";
        List<UserEntity> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(u == null || u.size() <= 0) {
            UserEntity user = new UserEntity();

            user.setFirstname("Salaj");
            user.setLastname("admin");
            user.setEmail("test@test.com");
            String publicUserId = utils.generateUserId(30);
            user.setUserId(publicUserId);
            user.setEncryptedPassword(bCryptPasswordEncoder.encode("test123"));

            userRepository.save(user);
        }
    }

    private void seedCityTable() {

        if (cityRepository.count() <= 0) {
            String[] cities = {"Mumbai", "Delhi", "Bangalore", "Hyderabad", "Ahmedabad", "Chennai", "Kolkata", "Surat", "Pune", "Jaipur"};
            City city;
            for (String cityName : cities){
                city = new City();
                city.setName(cityName);
                cityRepository.save(city);
            }
        }
    }

    private void seedStateTable() {

        if (stateRepository.count() <= 0) {
            String[] states = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Goa", "Gujarat", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka",
                                "Kerala","Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim",
                                "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"};
            State state;
            for (String stateName : states){
                state = new State();
                state.setName(stateName);
                stateRepository.save(state);
            }
        }
    }
}
