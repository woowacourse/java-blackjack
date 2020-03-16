package practice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class RentCompanyTest {
    private static final String NEWLINE = System.getProperty("line.separator");

    @Test
    public void report() throws Exception {
        RentCompany company = RentCompany.create(); // factory method를 사용해 생성
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        String report = company.generateReport();
        Assertions.assertThat(report).isEqualTo(
                "practice.Sonata : 15리터" + NEWLINE +
                        "practice.K5 : 20리터" + NEWLINE +
                        "practice.Sonata : 12리터" + NEWLINE +
                        "practice.Avante : 20리터" + NEWLINE +
                        "practice.K5 : 30리터" + NEWLINE
        );
    }
}
