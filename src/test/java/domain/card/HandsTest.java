package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandsTest {
	@Test
	@DisplayName("빈 Hands 생성 테스트")
	void createTest() {
		assertThat(Hands.createEmpty()).isInstanceOf(Hands.class);
	}

	@Test
	@DisplayName("카드의 합을 올바르게 계산하는지 테스트")
	void sumOfCardTest() {
		Hands hands = Hands.createEmpty();
		hands.add(new Card(Symbol.EIGHT, Type.DIAMOND));
		hands.add(new Card(Symbol.SEVEN, Type.CLUB));
		hands.add(new Card(Symbol.SIX, Type.DIAMOND));
		assertThat(hands.sumOfCards()).isEqualTo(21);

		hands.add(new Card(Symbol.ACE, Type.HEART));
		assertThat(hands.sumOfCards()).isEqualTo(22);
	}

	@Test
	@DisplayName("에이스를 포함하는지 여부를 테스트")
	void hasAceTest() {
		Hands hands = Hands.createEmpty();
		hands.add(new Card(Symbol.EIGHT, Type.DIAMOND));
		assertThat(hands.hasAce()).isFalse();

		hands.add(new Card(Symbol.ACE, Type.CLUB));
		assertThat(hands.hasAce()).isTrue();
	}
}