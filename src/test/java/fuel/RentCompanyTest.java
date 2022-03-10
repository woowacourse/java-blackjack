package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RentCompanyTest {

    private static final String NEWLINE = System.getProperty("line.separator");

    @Test
    void report() {
        RentCompany company = RentCompany.create();
        assertThat(company).isInstanceOf(RentCompany.class);
    }

    @Test
    void addCarToCompany() {
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));

        assertThat(company.size()).isEqualTo(1);
    }

    @Test
    void generateReport() {
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new Sonata(200));

        assertThat(company.report()).isEqualTo(
                "Sonata : 15리터" + NEWLINE + "Sonata : 20리터"
        );
    }
}
