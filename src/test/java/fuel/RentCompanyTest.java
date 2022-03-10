package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RentCompanyTest {

    @Test
    void report() {
        RentCompany company = RentCompany.create();
        assertThat(company).isInstanceOf(RentCompany.class);
    }
}
