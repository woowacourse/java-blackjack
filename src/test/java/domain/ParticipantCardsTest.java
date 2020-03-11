package domain;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParticipantCardsTest {
	private ParticipantCards cards;

	@BeforeEach
	void setUp() {
		cards = new ParticipantCards();
		cards.add(new Card(Symbol.FOUR, Shape.다이아몬드));
		cards.add(new Card(Symbol.FIVE, Shape.스페이드));
	}

	@Test
	@DisplayName("카드 점수 계산하는 기능 테스트 - Ace 미포함")
	void calculateScore() {
		assertThat(cards.calculateScore()).isEqualTo(9);
	}

	@Test
	@DisplayName("카드 점수 계산하는 기능 테스트 - Ace 포함")
	void calculateScoreWithAce() {
		cards.add((new Card(Symbol.ACE, Shape.하트)));
		assertThat(cards.calculateScore()).isEqualTo(20);

		cards.add(new Card(Symbol.FOUR, Shape.클로버));
		assertThat(cards.calculateScore()).isEqualTo(14);
	}

}
