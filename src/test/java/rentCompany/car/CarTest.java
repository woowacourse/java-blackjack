package rentCompany.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarTest {
	@DisplayName("자동차를 생성하는 기능")
	@Test
	void Car() {
		assertThat(new Sonata(350)).isNotNull();
		assertThat(new Avante(350)).isNotNull();
		assertThat(new K5(350)).isNotNull();
	}
}
