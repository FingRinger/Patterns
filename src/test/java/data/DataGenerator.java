package data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private static Faker faker;

    private DataGenerator() {

    }

    @BeforeAll
    static void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    public static String generateDate(int days) {
    String date =  LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    return date;
    }

    public static String generateCity(String locale) {
    String city = faker.address().city();
    return city;
    }

    public static String generateName(String locale) {
        String name = faker.name().username();
        return name;
    }

    public static String generatePhone(String locale) {
       String phone = faker.phoneNumber().phoneNumber();
       return  phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static Info generateUser(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new Info (generateDate(4),
                    generateCity("ru"),
                    generateName("ru"),
                    generatePhone("ru"));
        }
    }


}