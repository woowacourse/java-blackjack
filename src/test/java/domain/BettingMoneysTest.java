package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BettingMoneysTest {
	@Test
	void constructor() {
		assertThat(new BettingMoneys()).isInstanceOf(BettingMoneys.class);
	}
}
