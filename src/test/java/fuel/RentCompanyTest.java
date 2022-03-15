package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RentCompanyTest {

    private static final String NEWLINE = System.lineSeparator();

    @DisplayName("create 팩토리 메소드로 RentCompany 인스턴스를 생성할 수 있다.")
    @Test
    void create() {
        RentCompany company = RentCompany.create();

        assertThat(company).isExactlyInstanceOf(RentCompany.class);
    }

    @DisplayName("create 팩토리 메소드는 매번 다른 RentCompany 인스턴스를 생성한다.")
    @Test
    void create_isNotSingleton() {
        RentCompany company = RentCompany.create();
        RentCompany anotherCompany = RentCompany.create();

        assertThat(company).isNotEqualTo(anotherCompany);
    }

    @DisplayName("addCar 를 통해 회사에 자동차를 추가할 수 있다.")
    @Test
    void addCar() {
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(250));
        List<Car> currentCars = company.addCar(new Avante(1000));

        assertThat(currentCars.size()).isEqualTo(3);
    }

    @DisplayName("generateReport 를 통해 각 차량 별로 주입해야 할 연료량 정보를 반환한다.")
    @Test
    void generateReport() {
        RentCompany company = RentCompany.create();
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
