package com.lucasi.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EurekaApplicationTests {

	@Test
	void contextLoads() {
	}

}


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonFixedBuilderTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("João", 25, "Masculino", "Rua A", "11111111", "joao@example.com");
    }

    @Test
    @DisplayName("Teste build com endereço e idade atualizados")
    public void testBuildWithAddressAndAge() {
        PersonFixedBuilder builder = new PersonFixedBuilder(person);
        PersonFixed personFixed = builder.withAddress("Rua B").withAge(30).build();

        Assertions.assertEquals("João", personFixed.getName());
        Assertions.assertEquals(30, personFixed.getAge());
        Assertions.assertEquals("Masculino", personFixed.getGender());
        Assertions.assertEquals("Rua B", personFixed.getAddress());
        Assertions.assertEquals("11111111", personFixed.getPhoneNumber());
        Assertions.assertNull(personFixed.getEmail());
    }

    @Test
    @DisplayName("Teste build sem atualizações")
    public void testBuildWithoutUpdates() {
        PersonFixedBuilder builder = new PersonFixedBuilder(person);
        PersonFixed personFixed = builder.build();

        Assertions.assertEquals("João", personFixed.getName());
        Assertions.assertEquals(25, personFixed.getAge());
        Assertions.assertEquals("Masculino", personFixed.getGender());
        Assertions.assertEquals("Rua A", personFixed.getAddress());
        Assertions.assertEquals("11111111", personFixed.getPhoneNumber());
        Assertions.assertNull(personFixed.getEmail());
    }
}

