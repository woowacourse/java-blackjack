package fuelinjection;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RentCompanyTest {

    private static final String NEWLINE = System.getProperty("line.separator");

    private static Stream<Arguments> provideSource() {
        return Stream.of(
            Arguments.of(new Sonata(150)),
            Arguments.of(new Avante(200)),
            Arguments.of(new K5(300))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSource")
    @DisplayName("자동차 1대 추가 성공")
    void addCar1_OK(Car car) {
        // give
        RentCompany company = RentCompany.create();

        // when
        company.addCar(car);
        int actual = company.getCarCount();

        // then
        assertThat(actual).isEqualTo(1);
    }

    @Test
    @DisplayName("자동차 2대 추가 성공")
    void addCar2_OK() {
        // give
        RentCompany company = RentCompany.create();

        // when
        company.addCar(new Sonata(150));
        company.addCar(new Sonata(200));
        int actual = company.getCarCount();

        // then
        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("자동차 보고서 생성")
    void report() {
        // give
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        // when
        String actual = company.generateReport();

        // then
        assertThat(actual).isEqualTo(
            "Sonata : 15리터" + NEWLINE +
                "K5 : 20리터" + NEWLINE +
                "Sonata : 12리터" + NEWLINE +
                "Avante : 20리터" + NEWLINE +
                "K5 : 30리터" + NEWLINE
        );
    }
}
