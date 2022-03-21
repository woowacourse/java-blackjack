package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

class PlayersTest {

	@Test
	@DisplayName("플레이어가 모두 블랙잭인지 확인한다.")
	void is_all_blackjack() {
		Player player1 = new Player(new Name("pobi"));
		Player player2 = new Player(new Name("jason"));

		player1.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));
		player1.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));
		player2.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));
		player2.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));

		Players players = new Players(Arrays.asList(player1, player2));

		assertThat(players.isAllBlackJack()).isTrue();
	}
}
