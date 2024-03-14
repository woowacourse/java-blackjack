package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;

class PlayerTest {

	Player player;

	@Test
	@DisplayName("이름이 같으면 같은 플레이어로 판단한다.")
	void equalsTest() {
		Player player1 = new Player("name");
		Player player2 = new Player("name");

		assertThat(player1.equals(player2)).isTrue();
	}

	@Test
	@DisplayName("카드의 총합이 21 이하이면 카드를 받을 수 있다.")
	void receiveCardTest() {
		player = createTestPlayer(List.of(
			new Card(Shape.CLOVER, Number.KING),
			new Card(Shape.HEART, Number.FIVE)
		));

		assertThat(player.canReceiveCard()).isTrue();
	}

	@Test
	@DisplayName("카드의 총합이 21을 초과하면 카드를 받을 수 없다.")
	void cantReceiveCardTest() {
		player = createTestPlayer(List.of(
			new Card(Shape.CLOVER, Number.KING),
			new Card(Shape.HEART, Number.FIVE),
			new Card(Shape.HEART, Number.SEVEN)
		));

		assertThat(player.canReceiveCard()).isFalse();
	}

	private Player createTestPlayer(List<Card> cards) {
		return new Player("test", cards);
	}
}
