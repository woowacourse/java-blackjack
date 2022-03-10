package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DenominationTest {

	@Test
	@DisplayName("Denomination 값에서 포인트를 잘 반환하는지 확인하다.")
	void checkDenominationPoint() {

		assertThat(Denomination.FIVE.getPoint()).isEqualTo(5);

	}

}