package fuelInjection;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RentCompanyTest {

    @Test
    @DisplayName("RentCompany 생성 테스트")
    void createValidCheckRentCompany() {
        assertThat(RentCompany.create()).isNotNull();
    }

    @Test
    @DisplayName("RentCompany에 자동차 추가 테스트")
    void addCar() {
        RentCompany company = RentCompany.create();
        company.addCar(new Sonata(150));
        company.addCar(new K5(260));
        company.addCar(new Sonata(120));
        company.addCar(new Avante(300));
        company.addCar(new K5(390));

        assertThat(company.getCars().size()).isEqualTo(5);
    }
}
