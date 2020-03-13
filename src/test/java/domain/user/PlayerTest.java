package domain.user;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;

public class PlayerTest {

	@Test
	void isBlackjackTest() {
		Player player = new Player("동글");
		player.addCards(new Card(CLOVER, ACE),
			new Card(CLOVER, JACK));

		assertThat(player.isBlackjack()).isTrue();
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
