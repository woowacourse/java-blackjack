package domain.card;

import static domain.card.Denomination.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	private Cards cards;

	@BeforeEach
	void setCards() {
		cards = new Cards();
	}

	@DisplayName("card를 추가한다")
	@Test
	void addCard() {
		Card card1 = new Card(Denomination.TWO, Suits.HEART);
		Card card2 = new Card(Denomination.THREE, Suits.DIAMOND);
		cards.addCard(card1);
		cards.addCard(card2);

		assertThat(cards.getCards()).containsExactly(card1, card2);
	}

	@DisplayName("card의 점수 합을 구한다")
	@Test
	void getSumOfScores() {
		cards = new TestCards(List.of(TWO, THREE)).getCards();

		assertThat(cards.calculateScore()).isEqualTo(5);
	}

	@DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21 이하인 경우 ACE를 11점으로 계산한다")
	@Test
	void aceIs11_IfSumOfScores21OrLess() {
		cards = new TestCards(List.of(FOUR, SIX, ACE)).getCards();

		assertThat(cards.calculateScore()).isEqualTo(21);
	}

	@DisplayName("ACE가 존재할 경우, 점수 합에 10을 더한 점수가 21을 넘으면 ACE를 1점으로 계산한다")
	@Test
	void aceIs1_IfSumOfScoresOver21() {
		cards = new TestCards(List.of(FIVE, SIX, ACE)).getCards();

		assertThat(cards.calculateScore()).isEqualTo(12);
	}

	@DisplayName("카드의 점수 합이 n점 이상인지 확인한다")
	@Test
	void checkScoreMoreThanN() {
		cards = new TestCards(List.of(TWO, THREE)).getCards();

		assertThat(cards.isHittable(5)).isFalse();
		assertThat(cards.isHittable(6)).isTrue();
	}

	private static class TestCards {

		private final Cards cards;

		TestCards(List<Denomination> denominations) {
			cards = new Cards();
			for (Denomination denomination : denominations) {
				cards.addCard(new Card(denomination, Suits.HEART));
			}
		}

		public Cards getCards() {
			return cards;
		}
	}
}
