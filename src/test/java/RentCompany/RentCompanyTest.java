package RentCompany;

import RentCompany.car.Avante;
import RentCompany.car.K5;
import RentCompany.car.Sonata;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RentCompanyTest {
    private static final String NEWLINE = System.getProperty("line.separator");

    @DisplayName("자동차를 생성하는 기능")
    @Test
    void Car() {
        assertThat(new Sonata(350)).isNotNull();
        assertThat(new Avante(350)).isNotNull();
        assertThat(new K5(350)).isNotNull();
    }


    //    @Test
//    public void report() throws Exception {
//        RentCompany company = RentCompany.create();
//        company.addCar(new Sonata(150));
//        company.addCar(new K5(260));
//        company.addCar(new Sonata(120));
//        company.addCar(new Avante(300));
//        company.addCar(new K5(390));
//
//        String report = company.generateReport();
//        assertThat(report).isEqualTo(
//                "Sonata : 15리터" + NEWLINE +
//                        "K5 : 20리터" + NEWLINE +
//                        "Sonata : 12리터" + NEWLINE +
//                        "Avante : 20리터" + NEWLINE +
//                        "K5 : 30리터" + NEWLINE
//        );
//    }
}