package fuel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RentCompanyTest {

    private static final String NEWLINE = System.getProperty("line.separator");

    @DisplayName("자동차 추가 시 null 체크")
    @Test
    void add_car_null() {
        RentCompany company = RentCompany.create();

        assertThatThrownBy(() -> company.addCar(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null 객체는 추가할 수 없습니다.");
    }

    @Test
    public void report() throws Exception {
        RentCompany company = RentCompany.create(); // factory method 를 사용해 생성
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
}
