package rentcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RentCompanyTest {

    private static final String NEWLINE = System.getProperty("line.separator");

    @Test
    void report() throws Exception {
        RentCompany company = RentCompany.create(); // factory method를 사용해 생성
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

    @Test
    void report2() throws Exception {
        RentCompany company = RentCompany.create(); // factory method를 사용해 생성
        company.addCar(new Sonata(100));
        company.addCar(new K5(130));
        company.addCar(new Avante(150));

        String report = company.generateReport();
        assertThat(report).isEqualTo(
            "Sonata : 10리터" + NEWLINE +
                "K5 : 10리터" + NEWLINE +
                "Avante : 10리터" + NEWLINE
        );
    }
}
