package blakcjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeTest {
	@DisplayName("생성 성공")
	@Test
	void create() {
		final Outcome outcome = new Outcome(0, 1, 0);
		assertThat(outcome).isEqualTo(new Outcome(0, 1, 0));
	}
}