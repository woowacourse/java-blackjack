package domain.rule;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RuleTest {
	@Test
	@DisplayName("블랙잭인지 확인하는 테스트")
	void hasTwoCardsTest() {
		Hand hand = Hand.createEmpty();
		hand.add(new Card(Symbol.TEN, Type.DIAMOND));
		assertThat(Rule.isNotBlackJack(hand)).isTrue();

		hand.add(new Card(Symbol.ACE, Type.HEART));
		assertThat(Rule.isBlackJack(hand)).isTrue();
	}

	@Test
	@DisplayName("버스트인지 확인하는 테스트")
	void bustTest() {
		Hand hand = Hand.createEmpty();
		hand.add(new Card(Symbol.TEN, Type.DIAMOND));
		assertThat(Rule.isNotBust(hand)).isTrue();

		hand.add(new Card(Symbol.TEN, Type.HEART));
		hand.add(new Card(Symbol.TEN, Type.HEART));
		assertThat(Rule.isBust(hand)).isTrue();
	}

	@Test
	@DisplayName("카드를 더 받을 수 있는지 확인하는 테스트")
	void canHitTest() {
		Hand hand = Hand.createEmpty();
		hand.add(new Card(Symbol.TEN, Type.DIAMOND));
		assertThat(Rule.canHit(hand, 21)).isTrue();

		hand.add(new Card(Symbol.TEN, Type.HEART));
		hand.add(new Card(Symbol.ACE, Type.HEART));
		assertThat(Rule.canHit(hand, 21)).isFalse();
	}
}