package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RentCompanyTest {

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
}
