package test;

import com.codeborne.selenide.Configuration;
import data.DataGenerator;
import data.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ReplanTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
     //   Info validUser = DataGenerator.Registration.generateUser("ru");
        Configuration.holdBrowserOpen = true;
        $("[placeholder='Город']").setValue(DataGenerator.generateCity("ru"));


    }



}
