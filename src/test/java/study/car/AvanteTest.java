package study.car;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AvanteTest {
	@Test
	void Avante_InputTripDistance_GenerateInstance() {
		assertThat(new Avante(100)).isInstanceOf(Avante.class);
	}
}
