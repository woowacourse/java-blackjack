package rentcar;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class K5Test {
	@Test
	void create() {
		K5 k5 = new K5(260);
		assertAll(
				() -> assertThat(k5.getName()).isEqualTo("K5"),
				() -> assertThat(k5.getTripDistance()).isEqualTo(260),
				() -> assertThat(k5.getDistancePerLiter()).isEqualTo(13)
		);
	}
}
