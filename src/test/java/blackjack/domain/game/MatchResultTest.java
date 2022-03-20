package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public class MatchResultTest {

	@Test
	@DisplayName("수익을 계산한다")
	void calculate_revenue() {
		Player player = new Player(new Name("pobi"));
		player.draw(Card.valueOf(Denomination.ACE, Suit.CLOVER));
		player.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));

		MatchResult matchResult = MatchResult.BLACKJACK;
		assertThat(matchResult.calculateRevenue(player)).isEqualTo(1500);
	}
}
