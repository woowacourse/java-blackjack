package domain.user;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.CardFactory;

public class PlayerTest {

	@Test
	void getInitialCardTest() {
		Player player = new Player("동글");
		player.addCards(CardFactory.create());

		assertThat(player.getInitialCard().size()).isEqualTo(2);
	}

	@DisplayName("이름이 빈문자열일 때")
	@Test
	void createTest() {
		assertThatThrownBy(() -> new Player(""))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("이름이 널일 때")
	@Test
	void createTest2() {
		assertThatThrownBy(() -> new Player(null))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
