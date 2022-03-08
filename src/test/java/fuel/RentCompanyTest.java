package fuel;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RentCompanyTest {

	@Test
	@DisplayName("차의 이동거리가 마이너스이면 예외")
	void carDistanceInvalid() {
		assertThatThrownBy(() -> new Sonata(-1))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("이동거리에 따른 연료 주입 확인")
	void needFuelCheck() {
		Car car = new Sonata(150);
		assertThat(car.getChargeQuantity()).isEqualTo(15);
	}

	private static final String NEWLINE = System.getProperty("line.separator");

	@Test
	@DisplayName("연료량 보고서 확인")
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


