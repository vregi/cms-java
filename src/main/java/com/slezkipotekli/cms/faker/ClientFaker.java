package com.slezkipotekli.cms.faker;

import com.github.javafaker.Faker;
import com.slezkipotekli.cms.entity.Client;

/**
 * @author vregi, 12/13/2024
 */
public class ClientFaker {
    private static final Faker faker = new Faker();

    public static Client createFakeClient() {
        Client client = new Client();

        client.setId(null); // Let the database handle ID generation.
        client.setName(faker.name().fullName());
        client.setEmail(faker.internet().emailAddress());
        client.setPhoneNumber(faker.phoneNumber().subscriberNumber(10)); // Generates a 10-digit number.

        // Projects will be handled separately.
        client.setProjects(null);

        return client;
    }
}
