package domain.gamer;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
	private Player player;

	@BeforeEach
	void setUp() {
		player = new Player(new Name("pobi"), Money.ZERO);

		player.hit(new Card(Symbol.EIGHT, Type.DIAMOND));
		player.hit(new Card(Symbol.SEVEN, Type.CLUB));
	}

	@Test
	@DisplayName("플레이어가 지급받은 카드를 갖고 있는지 확인")
	void receiveCard() {
		assertThat(player.getCards())
				.containsExactly(new Card(Symbol.EIGHT, Type.DIAMOND), new Card(Symbol.SEVEN, Type.CLUB));
	}

	@Test
	@DisplayName("20이하의 경우 추가 카드를 받을 수 있는지")
	void canReceiveMore() {
		player.hit(new Card(Symbol.FIVE, Type.DIAMOND));

		assertThat(player.canHit()).isTrue();
	}

	@Test
	@DisplayName("20초과의 경우 추가 카드를 받을 수 없는지")
	void canNotReceiveMore() {
		player.hit(new Card(Symbol.SIX, Type.DIAMOND));

		assertThat(player.canHit()).isFalse();
	}

	@Test
	@DisplayName("hit을 할 경우 카드가 하나 추가되는지 테스트")
	void hitTest() {
		player.hit(new Card(Symbol.SIX, Type.DIAMOND));

		assertThat(player.getCards()).hasSize(3);
	}

	@Test
	@DisplayName("플레이어가 처음에 2장의 패를 공개하는지 테스트")
	void firstOpenedCardsTest() {
		assertThat(player.firstOpenedCards()).hasSize(2);
	}
}