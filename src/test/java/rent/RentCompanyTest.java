package rent;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rent.car.Avante;
import rent.car.Car;
import rent.car.Sonata;

public class RentCompanyTest {

    private static final String NEWLINE = System.getProperty("line.separator");

    @DisplayName("팩토리 메소드로 회사 인스턴스를 만든다.")
    @Test
    public void createCompanyWithFactoryMethod() {
        //then
        Assertions.assertAll(
                () -> assertThatCode(RentCompany::create),
                () -> assertThat(RentCompany.create()).isInstanceOf(RentCompany.class)
        );
    }

    @DisplayName("회사에 렌트카를 추가 할 수 있다.")
    @Test
    public void testAddCar() {
        //given
        RentCompany rentCompany = RentCompany.create();
        Avante avante = new Avante(100);

        //when & then
        assertThatNoException().isThrownBy(() -> rentCompany.addCar(avante));
    }

    @DisplayName("회사가 차량 별 주입해야 할 연료에 대한 보고서를 출력한다.")
    @Test
    public void testReportGeneration() {
        //given
        RentCompany rentCompany = RentCompany.create();
        Car car = new Sonata(100);
        rentCompany.addCar(car);

        //when
        String report = rentCompany.generateReport();

        //then
        assertThat(report).isEqualTo(
                "Sonata : 10리터" + NEWLINE
        );
    }
}
