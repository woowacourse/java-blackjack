package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

class DealerTest {

	@Test
	@DisplayName("딜러가 카드를 추가한다.")
	void addCard() {
		Dealer dealer = new Dealer();
		CardFactory cardFactory = new CardFactory(Card.getCards());
		Card card = cardFactory.draw();

		dealer.addCard(card);
		List<Card> cards = dealer.getCards();

		assertThat(cards.size()).isEqualTo(1);
	}

	@ParameterizedTest
	@CsvSource(value = {"14:true", "16:false"}, delimiter = ':')
	@DisplayName("보유 카드 번호 합이 특정 숫자를 넘었는지 확인")
	void checkCardsNumberSum(int input, boolean result) {
		Dealer dealer = new Dealer();
		Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.TEN);
		Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.FIVE);

		dealer.addCard(card1);
		dealer.addCard(card2);

		assertThat(dealer.isOverThan(input)).isEqualTo(result);
	}

	@Test
	@DisplayName("보유 카드 번호 합 반환")
	void calculateCardsNumberSum() {
		Dealer dealer = new Dealer();
		Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.TEN);
		Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.FIVE);

		dealer.addCard(card1);
		dealer.addCard(card2);

		int sum = dealer.getCardsNumberSum();
		assertThat(sum).isEqualTo(15);
	}
}