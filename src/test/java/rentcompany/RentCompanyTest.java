package rentcompany;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RentCompanyTest {

    @Test
    @DisplayName("렌트 회사 객체를 정상적으로 생성한다.")
    void createRentCompany() {
        assertThatCode(() -> new RentCompany())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("렌트 회사에 차량을 추가한다.")
    void addCar() {
        RentCompany rentCompany = new RentCompany();

        rentCompany.addCar(new Sonata(100));
        rentCompany.addCar(new Avante(150));
        rentCompany.addCar(new K5(130));

        int actual = rentCompany.getCountOfCar();
        assertThat(actual).isEqualTo(3);
    }

    @Test
    @DisplayName("렌트 회사에서 null을 추가하면 NullPointerException을 발생시킨다.")
    void checkAddCarException() {
        RentCompany rentCompany = new RentCompany();

        assertThatThrownBy(() -> rentCompany.addCar(null))
                .isInstanceOf(NullPointerException.class);
    }
}
