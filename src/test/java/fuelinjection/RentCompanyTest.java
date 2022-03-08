package fuelinjection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RentCompanyTest {

    private static final String NEWLINE = System.getProperty("line.separator");

    @Test
    @DisplayName("자동차 1대 추가 성공")
    void addCar1_OK() {
        // give
        RentCompany company = RentCompany.create();

        // when
        company.addCar(new Sonata(150));
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

/*
    @Test
    public void report() {
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        String report = company.generateReport();
        assertThat(report).isEqualTo(
                "Sonata : 15리터" + NEWLINE +
                        "K5 : 20리터" + NEWLINE +
                        "Sonata : 12리터" + NEWLINE +
                        "Avante : 20리터" + NEWLINE +
                        "K5 : 30리터" + NEWLINE
        );
    }
*/
}
