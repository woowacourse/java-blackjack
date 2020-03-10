package study.car;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class K5Test {
	@Test
	void K5_InputTripDistance_GenerateInstance() {
		assertThat(new K5(100)).isInstanceOf(K5.class);
	}
}
