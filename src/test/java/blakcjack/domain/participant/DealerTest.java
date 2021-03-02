package blakcjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
	@DisplayName("딜러 객체 생성 성공")
	@Test
	void create() {
		final Dealer dealer = new Dealer();
		assertThat(dealer).isEqualTo(new Dealer());
	}
}
