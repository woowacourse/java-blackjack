package domain;

import domain.card.PlayerCards;
import domain.card.cardfactory.Card;
import domain.card.cardfactory.Shape;
import domain.card.cardfactory.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerCardsTest {
	private PlayerCards cards;

	@BeforeEach
	void setUp() {
		cards = new PlayerCards();
		cards.add(new Card(Symbol.FOUR, Shape.DIAMOND));
		cards.add(new Card(Symbol.FIVE, Shape.SPADE));
	}

	@Test
	@DisplayName("카드 점수 계산하는 기능 테스트 - Ace 미포함")
	void calculateScore() {
		assertThat(cards.calculateScore()).isEqualTo(9);
	}

	@Test
	@DisplayName("카드 점수 계산하는 기능 테스트 - Ace 포함")
	void calculateScoreWithAce() {
		cards.add((new Card(Symbol.ACE, Shape.HART)));
		assertThat(cards.calculateScore()).isEqualTo(20);

		cards.add(new Card(Symbol.FOUR, Shape.CLOVER));
		assertThat(cards.calculateScore()).isEqualTo(14);
	}
}
