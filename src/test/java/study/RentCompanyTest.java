package study;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import study.car.Avante;
import study.car.K5;
import study.car.Sonata;

public class RentCompanyTest {
	private static final String NEWLINE = System.getProperty("line.separator");

	@DisplayName("create_RentCompany 인스턴스 생성")
	@Test
	void create_GenerateInstance() {
		assertThat(RentCompany.create()).isInstanceOf(RentCompany.class);
	}

	@Test
	void addCar_CarInstance_InputToCarsList() {
		RentCompany company = RentCompany.create();
		K5 car = new K5(100);
		company.addCar(car);
		assertThat(company.getCars()).contains(car);
	}

	@Test
	public void report() throws Exception {
		RentCompany company = RentCompany.create(); // factory method를 사용해 생성
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
