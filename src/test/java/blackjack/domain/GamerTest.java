package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

class GamerTest {

	@Test
	@DisplayName("딜러가 카드를 추가한다.")
	void addCard() {
		Gamer gamer = new Gamer();
		CardFactory cardFactory = new CardFactory(Card.getCards());
		Card card = cardFactory.draw();

		gamer.addCard(card);
		List<Card> cards = gamer.getCards();

		assertThat(cards.size()).isEqualTo(1);
	}

	@ParameterizedTest
	@CsvSource(value = {"14:true", "16:false"}, delimiter = ':')
	@DisplayName("보유 카드 번호 합이 특정 숫자를 넘었는지 확인")
	void checkCardsNumberSum(int input, boolean result) {
		Gamer gamer = new Gamer();
		Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.TEN);
		Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.FIVE);

		gamer.addCard(card1);
		gamer.addCard(card2);

		assertThat(gamer.isOverThan(input)).isEqualTo(result);
	}

	@Test
	@DisplayName("보유 카드 번호 합 반환")
	void calculateCardsNumberSum() {
		Gamer gamer = new Gamer();
		Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.TEN);
		Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.FIVE);

		gamer.addCard(card1);
		gamer.addCard(card2);

		int sum = gamer.getCardsNumberSum();
		assertThat(sum).isEqualTo(15);
	}
}