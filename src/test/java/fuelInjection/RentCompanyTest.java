package fuelInjection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RentCompanyTest {

    private static final String NEWLINE = System.getProperty("line.separator");

    private RentCompany company;

    @BeforeEach
    void setUp() {
        company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));
    }

    @Test
    @DisplayName("RentCompany 생성 테스트")
    void createValidCheckRentCompany() {
        assertThat(RentCompany.create()).isNotNull();
    }

    @Test
    @DisplayName("RentCompany에 자동차 추가 테스트")
    void addCar() {
        assertThat(company.getCars().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("연료량을 확인할 수 있는 보고시 생성 테스트")
    void generateReport() {
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
