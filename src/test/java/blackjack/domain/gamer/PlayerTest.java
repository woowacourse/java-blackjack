package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

class PlayerTest {

	Name name = new Name("test");
	Player player;

	@Test
	@DisplayName("카드의 총합이 21 이하이면 카드를 받을 수 있다.")
	void receiveCardTest() {
		player = new Player(name, List.of(
			new Card(CardShape.CLOVER, CardNumber.KING),
			new Card(CardShape.HEART, CardNumber.FIVE)
		));

		assertThat(player.canReceiveCard()).isTrue();
	}

	@Test
	@DisplayName("카드의 총합이 21을 초과하면 카드를 받을 수 없다.")
	void cantReceiveCardTest() {
		player = new Player(name, List.of(
			new Card(CardShape.CLOVER, CardNumber.KING),
			new Card(CardShape.HEART, CardNumber.FIVE),
			new Card(CardShape.HEART, CardNumber.SEVEN)
		));

		assertThat(player.canReceiveCard()).isFalse();
	}
}
