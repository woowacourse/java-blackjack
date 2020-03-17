package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
	@Test
	@DisplayName("빈 Hands 생성 테스트")
	void createTest() {
		assertThat(Hand.createEmpty()).isInstanceOf(Hand.class);
	}

	@Test
	@DisplayName("2개의 카드를 갖는지를 올바르게 판단하는지 테스트")
	void hasTwoCardsTest() {
		Hand hand = Hand.createEmpty();
		hand.add(new Card(Symbol.EIGHT, Type.DIAMOND));
		assertThat(hand.hasTwoCards()).isFalse();

		hand.add(new Card(Symbol.ACE, Type.HEART));
		assertThat(hand.hasTwoCards()).isTrue();
	}

	@Test
	@DisplayName("카드의 합을 올바르게 계산하는지 테스트")
	void sumOfCardTest() {
		Hand hand = Hand.createEmpty();
		hand.add(new Card(Symbol.EIGHT, Type.DIAMOND));
		hand.add(new Card(Symbol.SEVEN, Type.CLUB));
		hand.add(new Card(Symbol.SIX, Type.DIAMOND));
		assertThat(hand.sumOfCards()).isEqualTo(21);

		hand.add(new Card(Symbol.ACE, Type.HEART));
		assertThat(hand.sumOfCards()).isEqualTo(22);
	}

	@Test
	@DisplayName("에이스를 포함하는지 여부를 테스트")
	void hasAceTest() {
		Hand hand = Hand.createEmpty();
		hand.add(new Card(Symbol.EIGHT, Type.DIAMOND));
		assertThat(hand.hasAce()).isFalse();

		hand.add(new Card(Symbol.ACE, Type.CLUB));
		assertThat(hand.hasAce()).isTrue();
	}
}