package domain.participant;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

class DealerTest {
	@Test
	@DisplayName("16이하의 경우 추가 카드를 받을 수 있는지")
	void canReceiveMore() {
		Player player = new Player("pobi");
		player.receiveCard(new Card(Symbol.FIVE, Type.DIAMOND));
		player.receiveCard(new Card(Symbol.SEVEN, Type.CLUB));
		player.receiveCard(new Card(Symbol.FOUR, Type.DIAMOND));

		assertThat(player.canReceiveMore()).isTrue();
	}

	@Test
	@DisplayName("16초과의 경우 추가 카드를 받을 수 없는지")
	void canNotReceiveMore() {
		Player player = new Player("pobi");
		player.receiveCard(new Card(Symbol.FIVE, Type.DIAMOND));
		player.receiveCard(new Card(Symbol.EIGHT, Type.CLUB));
		player.receiveCard(new Card(Symbol.FOUR, Type.DIAMOND));

		assertThat(player.canReceiveMore()).isFalse();
	}
}