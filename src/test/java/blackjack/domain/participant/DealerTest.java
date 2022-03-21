package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class DealerTest {

	@Test
	@DisplayName("딜러는 16초과이면 더 이상 게임을 진행할 수 없다.")
	void is_finished() {
		Dealer player = new Dealer();

		player.draw(Card.valueOf(Denomination.SEVEN, Suit.CLOVER));
		player.draw(Card.valueOf(Denomination.JACK, Suit.CLOVER));

		assertThat(player.isFinished()).isTrue();
	}
}
