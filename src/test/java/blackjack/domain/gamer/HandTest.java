package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;

class HandTest {

	Hand hand;

	@Test
	@DisplayName("ACE가 없을때의 숫자 합을 계산한다.")
	void sumWithoutAceTest() {
		hand = new Hand(List.of(
			new Card(Shape.CLOVER, Number.KING),
			new Card(Shape.HEART, Number.FIVE)
		));

		assertThat(hand.calculateScore()).isEqualTo(15);
	}

	@Test
	@DisplayName("숫자의 합이 21을 초과하는 경우, ACE를 1로 계산한다.")
	void sumWithOneAceTest() {
		hand = new Hand(List.of(
			new Card(Shape.HEART, Number.ACE),
			new Card(Shape.CLOVER, Number.KING),
			new Card(Shape.DIAMOND, Number.KING)
		));

		assertThat(hand.calculateScore()).isEqualTo(21);
	}

	@Test
	@DisplayName("숫자의 합이 21을 초과하지 않으면, ACE를 11로 계산한다.")
	void sumWithElevenAceTest() {
		hand = new Hand(List.of(
			new Card(Shape.HEART, Number.ACE),
			new Card(Shape.CLOVER, Number.KING)
		));

		assertThat(hand.calculateScore()).isEqualTo(21);
	}
}
