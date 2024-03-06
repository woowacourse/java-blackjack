package blackjack.domain.gamer;

import static java.util.List.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

	@Test
	@DisplayName("플레이어의 이름은 중복을 허용하지 않는다.")
	void duplicatePlayerNamesTest() {
		assertThatThrownBy(() -> new Players(of("hogi", "sang", "hogi")))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("플레이어는 적어도 2명 이상이어야 한다.")
	void minimumPlayerCountTest() {
		assertThatThrownBy(() -> new Players(of("a")))
			.isInstanceOf(IllegalArgumentException.class);

	}

	@Test
	@DisplayName("플레이어는 최대 8명까지만 가능하다.")
	void maximumPlayerCountTest() {
		assertThatThrownBy(() -> new Players(of("a", "b", "c", "d", "e", "f", "g", "h", "g")))
			.isInstanceOf(IllegalArgumentException.class);

	}
}
