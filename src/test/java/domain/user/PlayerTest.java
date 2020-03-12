package domain.user;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

public class PlayerTest {

	@Test
	void isBlackjackTest() {
		Player player = new Player("동글");
		player.addCards(Arrays.asList(new Card(Symbol.CLOVER, Type.ACE), 
			new Card(Symbol.CLOVER, Type.JACK)));

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
