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
		Player player = Player.valueOf("동글");
		player.addCards(new Card(CLOVER, ACE),
			new Card(CLOVER, JACK));

		assertThat(player.isBlackjack()).isTrue();
	}
}
