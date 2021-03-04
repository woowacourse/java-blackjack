package rentcompany;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import rentcompany.car.Avante;
import rentcompany.car.K5;
import rentcompany.car.Sonata;

public class RentCompanyTest {
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void report() {
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        String report = company.generateReport();
        assertThat(report).isEqualTo(
            "Sonata : 15리터" + NEW_LINE +
                "K5 : 20리터" + NEW_LINE +
                "Sonata : 12리터" + NEW_LINE +
                "Avante : 20리터" + NEW_LINE +
                "K5 : 30리터" + NEW_LINE
        );
    }
}
