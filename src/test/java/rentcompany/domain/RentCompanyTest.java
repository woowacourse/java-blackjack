package rentcompany.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RentCompanyTest {
    private static final String NEWLINE = System.getProperty("line.separator");
    private RentCompany company; // factory method를 사용해 생성

    @BeforeEach
    void setUp() {
        company = RentCompany.create();

        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));
    }

    @DisplayName("렌트회사 생성 및 차량 추가 테스트")
    @Test
    public void createCars() {
        assertThat(company.size()).isEqualTo(5);
    }

    @DisplayName("연료량 구하는 보고서 작성 테스트")
    @Test
    public void report() {
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
