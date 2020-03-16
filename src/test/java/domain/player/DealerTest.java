package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DealerTest {
	@DisplayName("딜러 이름 테스트")
	@Test
	void init() {
		Dealer dealer = new Dealer();
		assertThat(dealer.getName()).isEqualTo("딜러");
	}
}
