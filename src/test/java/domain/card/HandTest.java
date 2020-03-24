package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {
	private Hand hand;

	@BeforeEach
	void setUp() {
		hand = Hand.fromEmpty();
		hand.add(new Card(Symbol.SEVEN, Type.CLUB));
		hand.add(new Card(Symbol.TWO, Type.CLUB));
	}

	@Test
	@DisplayName("카드가 정상적으로 추가되는지 테스트합니다.")
	void addTest() {
		assertThat(hand.hasTwoCards()).isTrue();
	}

	@Test
	@DisplayName("가지고 있는 카드를 정확하게 계산하는지 테스트합니다.")
	void calculateTest() {
		assertThat(hand.calculate()).isEqualTo(9);
		hand.add(new Card(Symbol.ACE, Type.DIAMOND));
		assertThat(hand.calculate()).isEqualTo(10);
	}

	@Test
	@DisplayName("에이스가 포함되지 않은 경우 false반환")
	void hasNotAceTest() {
		assertThat(hand.hasAce()).isFalse();
	}

	@Test
	@DisplayName("에이스가 포함된 경우 True반환")
	void hasAceTest() {
		hand.add(new Card(Symbol.ACE, Type.DIAMOND));
		assertThat(hand.hasAce()).isTrue();
	}

}