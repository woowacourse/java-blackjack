package rentcar;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AvanteTest {
	@Test
	void create() {
		Avante avante = new Avante(300);
		assertAll(
				() -> assertThat(avante.getName()).isEqualTo("Avante"),
				() -> assertThat(avante.getTripDistance()).isEqualTo(300),
				() -> assertThat(avante.getDistancePerLiter()).isEqualTo(15)
		);
	}
}