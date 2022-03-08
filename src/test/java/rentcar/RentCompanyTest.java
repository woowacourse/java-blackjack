package rentcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rentcar.Avante;
import rentcar.K5;
import rentcar.RentCompany;
import rentcar.Sonata;

import static org.assertj.core.api.Assertions.assertThat;

public class RentCompanyTest {
    private static final String NEWLINE = System.getProperty("line.separator");

    @Test
    public void report() throws Exception {
        RentCompany rentCompany = new RentCompany();
        rentCompany.addCar(new Avante(300));
        rentCompany.addCar(new Sonata(300));
        rentCompany.addCar(new Sonata(300));
        rentCompany.addCar(new K5(300));
        rentCompany.addCar(new K5(300));

        String report = rentCompany.generateReport();
        assertThat(report).isEqualTo(
                "Sonata : 15리터" + NEWLINE +
                        "K5 : 20리터" + NEWLINE +
                        "Sonata : 12리터" + NEWLINE +
                        "Avante : 20리터" + NEWLINE +
                        "K5 : 30리터" + NEWLINE
        );
    }
}


