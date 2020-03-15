package study.car;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SonataTest {
	@Test
	void Sonata_InputTripDistance_GenerateInstance() {
		assertThat(new Sonata(100)).isInstanceOf(Sonata.class);
	}
}
