package fuel;

import static fuel.RentCompany.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;

public class RentCompanyTest {

	@Test
	public void report() {
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
