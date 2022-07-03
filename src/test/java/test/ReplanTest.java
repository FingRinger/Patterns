package test;

import com.codeborne.selenide.Configuration;
import data.DataGenerator;
import data.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ReplanTest {

    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldReplanMeeting() {
        Configuration.holdBrowserOpen = true;
        Info user = DataGenerator.generateUser();
        $("[placeholder='Город']").setValue(user.getCity());
        $("[data-test-id='date'] input[class='input__control']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input[class='input__control']").setValue(DataGenerator.generateDate(6));
        $("[name='name']").setValue(user.getName());
        $("[name='phone']").val(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(byText("Запланировать")).click();
        $("[data-test-id='success-notification'] div[class='notification__content']").shouldBe(visible, Duration.ofSeconds(15))
                .should(text("Встреча успешно запланирована на " + DataGenerator.generateDate(6)));
        $(byText("Запланировать")).click();
        $("[data-test-id='date'] input[class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input[class='input__control']").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input[class='input__control']").setValue(DataGenerator.generateDate(10));
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification']").shouldBe(visible, Duration.ofSeconds(15));
        $x(".//span[contains(text(), 'Перепланировать')]//ancestor::button").click();
        $("[data-test-id='success-notification'] div[class='notification__content']").shouldBe(visible, Duration.ofSeconds(15))
                .should(text("Встреча успешно запланирована на " + DataGenerator.generateDate(10)));
    }
}
