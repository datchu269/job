package com.example.validateauto;

import com.example.validateauto.validate.Configuration;
import com.example.validateauto.validate.XMLConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidateAutoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testWeightedRoundRobin() throws Exception {

        Configuration config = new XMLConfiguration("src/test/resources/jdiameter-weightedroundrobin-config.xml");

        System.out.println(config);
    }

    @Test
    public void testWeightedLeastConnections() throws Exception {

        Configuration config = new XMLConfiguration("src/test/resources/jdiameter-weightedleastconnections-config.xml");
        System.out.println(config);

    }
}
