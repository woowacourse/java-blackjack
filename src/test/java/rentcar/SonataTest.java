package rentcar;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SonataTest {
	@Test
	void create() {
		Sonata sonata = new Sonata(150);
		assertAll(
				() -> assertThat(sonata.getName()).isEqualTo("Sonata"),
				() -> assertThat(sonata.getTripDistance()).isEqualTo(150f),
				() -> assertThat(sonata.getDistancePerLiter()).isEqualTo(10f)
		);
	}
}