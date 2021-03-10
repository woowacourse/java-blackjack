package fuel_injection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RentCompanyTest {
    private static final String NEWLINE = System.getProperty("line.separator");

    @Test
    void report() {
        //given
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        //when
        String report = company.generateReport();

        //then
        assertThat(report).isEqualTo("Sonata : 15리터" + NEWLINE +
                "K5 : 20리터" + NEWLINE +
                "Sonata : 12리터" + NEWLINE +
                "Avante : 20리터" + NEWLINE +
                "K5 : 30리터" + NEWLINE);
    }
}
