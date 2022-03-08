package rentcompany;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RentCompanyTest {

    private final RentCompany rentCompany = new RentCompany();

    @Test
    @DisplayName("렌트 회사 객체를 정상적으로 생성한다.")
    void createRentCompany() {
        assertThatCode(() -> new RentCompany())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("렌트 회사에 차량을 추가한다.")
    void addCar() {
        rentCompany.addCar(new Sonata(100));
        rentCompany.addCar(new Avante(150));
        rentCompany.addCar(new K5(130));
        final int expected = 3;

        final int actual = rentCompany.getCountOfCar();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("렌트 회사에서 null을 추가하면 NullPointerException을 발생시킨다.")
    void checkAddCarException() {
        assertThatThrownBy(() -> rentCompany.addCar(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("렌트 회사는 주입할 연료량 보고서를 생성한다.")
    void generateReport() {
        final String expected = "SONATA : 15리터" + RentCompany.NEWLINE +
                "K5 : 20리터" + RentCompany.NEWLINE +
                "SONATA : 12리터" + RentCompany.NEWLINE +
                "AVANTE : 20리터" + RentCompany.NEWLINE +
                "K5 : 30리터" + RentCompany.NEWLINE;

        rentCompany.addCar(new Sonata(150));
        rentCompany.addCar(new K5(260));
        rentCompany.addCar(new Sonata(120));
        rentCompany.addCar(new Avante(300));
        rentCompany.addCar(new K5(390));

        final String report = rentCompany.generateReport();

        assertThat(report).isEqualTo(expected);
    }
}
