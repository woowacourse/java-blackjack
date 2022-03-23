package rentalcompany;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rentalcompany.car.Avante;
import rentalcompany.car.K5;
import rentalcompany.car.Sonata;

public class RentalCompanyTest {
    private static final String NEWLINE = System.getProperty("line.separator");

    @Test
    @DisplayName("렌탈카 상태 확인 기능 테스트")
    public void report() {
        RentalCompany company = RentalCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        String report = company.generateReport();
        assertThat(report)
                .isEqualTo(
                        "Sonata : 15리터" + NEWLINE +
                                "K5 : 20리터" + NEWLINE +
                                "Sonata : 12리터" + NEWLINE +
                                "Avante : 20리터" + NEWLINE +
                                "K5 : 30리터" + NEWLINE
                );
    }
}
