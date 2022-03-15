package rent;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rent.car.Avante;
import rent.car.Car;
import rent.car.K5;
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

    @DisplayName("여러대의 차량에 대한 보고서를 생성한다.")
    @Test
    public void testReportGenerationWithMultipleCars() {
        //given
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        //when
        String report = company.generateReport();

        //then
        assertThat(report).isEqualTo(
                "Sonata : 15리터" + NEWLINE +
                        "K5 : 20리터" + NEWLINE +
                        "Sonata : 12리터" + NEWLINE +
                        "Avante : 20리터" + NEWLINE +
                        "K5 : 30리터" + NEWLINE
        );
    }
}
