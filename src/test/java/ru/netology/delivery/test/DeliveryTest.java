package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import io.qameta.allure.selenide.AllureSelenide;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.delivery.data.DataGenerator.*;

class DeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldBePositiveChangeDate() {
        $("[data-test-id='city'] input").setValue(generateCity("ru"));
        String planningDate = generateDate(4);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id='name'] input").setValue(generateName("ru"));
        $("[data-test-id='phone'] input.input__control").setValue(generatePhone("ru"));
        $("[data-test-id='agreement'] ").click();
        $("div>button").click();
        $("[data-test-id='success-notification']").should(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String planningDate2 = generateDate(8);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(planningDate2);
        $("div>button").click();
        $("[data-test-id='replan-notification']").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification']").should(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate2), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }
    @Test
    void shouldBeNegativeChangeDate() {
        $("[data-test-id='city'] input").setValue(generateCity("ru"));
        String planningDate = generateDate(11);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(planningDate);
        $("[data-test-id='name'] input").setValue(generateName("ru"));
        $("[data-test-id='phone'] input.input__control").setValue(generatePhone("ru"));
        $("[data-test-id='agreement'] ").click();
        $("div>button").click();
        $("[data-test-id='success-notification']").should(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
        String planningDate2 = generateDate(20);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(planningDate2);
        $("div>button").click();
        $("[data-test-id='replan-notification']").should(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification']").should(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно не запланирована на " + planningDate2), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }

    @Test
    void testUser() {
        System.out.println(generateCity("ru"));
        System.out.println(generatePhone("ru"));
        System.out.println(generateName("ru"));
    }

}