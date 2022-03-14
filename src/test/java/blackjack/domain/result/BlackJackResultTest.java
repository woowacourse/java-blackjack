package blackjack.domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;

class BlackJackResultTest {

	private Cards playerCards = new Cards();
	private Cards dealerCards = new Cards();

	@Test
	@DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 승리")
	void playerBlackJackAndDealerNotBlackJAck() {
		playerCards.add(getAce());
		playerCards.add(getTen());
		dealerCards.add(getFive());

		dealerCards.add(getFive());
		dealerCards.add(getAce());

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		Assertions.assertThat(result).isEqualTo(BlackJackResult.WIN);
	}

	@Test
	@DisplayName("플레이어가 버스트 아닌데 딜러가 버스트면 승리")
	void playerNotBustAndDealerBust() {
		playerCards.add(getAce());
		playerCards.add(getAce());

		dealerCards.add(getTen());
		dealerCards.add(getAce());
		dealerCards.add(getAce());

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		Assertions.assertThat(result).isEqualTo(BlackJackResult.WIN);
	}

	@Test
	@DisplayName("둘 다 버스트가 아닐 때 숫자가 높으면 승리")
	void notBustCompareWin() {
		playerCards.add(getTen());
		playerCards.add(getTen());

		dealerCards.add(getTen());
		dealerCards.add(getFive());
		dealerCards.add(getAce());
		dealerCards.add(getAce());

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		Assertions.assertThat(result).isEqualTo(BlackJackResult.WIN);
	}

	@Test
	@DisplayName("둘 다 버스트이면 플레이어 패배")
	void bothBust() {
		playerCards.add(getTen());
		playerCards.add(getAce());
		playerCards.add(getAce());

		dealerCards.add(getTen());
		dealerCards.add(getAce());
		dealerCards.add(getAce());

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		Assertions.assertThat(result).isEqualTo(BlackJackResult.LOSE);
	}

	@Test
	@DisplayName("딜러만 블랙잭이면 플레이어 패배")
	void playerNotBlackJackAndDealerBlackJack() {
		playerCards.add(getFive());
		playerCards.add(getFive());
		playerCards.add(getAce());

		dealerCards.add(getTen());
		dealerCards.add(getAce());

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		Assertions.assertThat(result).isEqualTo(BlackJackResult.LOSE);
	}

	@Test
	@DisplayName("둘 다 버스트가 아닐 때 숫자가 낮으면 패배")
	void notBustCompareLose() {
		playerCards.add(getTen());
		playerCards.add(getFive());

		dealerCards.add(getTen());
		dealerCards.add(getFive());
		dealerCards.add(getAce());
		dealerCards.add(getAce());

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		Assertions.assertThat(result).isEqualTo(BlackJackResult.LOSE);
	}

	@Test
	@DisplayName("둘 다 블랙잭이면 무승부")
	void bothBlackJack() {
		playerCards.add(getTen());
		playerCards.add(getAce());

		dealerCards.add(getTen());
		dealerCards.add(getAce());

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		Assertions.assertThat(result).isEqualTo(BlackJackResult.DRAW);
	}

	@Test
	@DisplayName("둘 다 버스트 아니고 숫자 같으면 무승부")
	void compareDraw() {
		playerCards.add(getTen());
		playerCards.add(getFive());

		dealerCards.add(getTen());
		dealerCards.add(getFive());

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		Assertions.assertThat(result).isEqualTo(BlackJackResult.DRAW);
	}

	private Card getAce() {
		return Card.getInstance(CardShape.CLOVER, CardNumber.ACE);
	}

	private Card getTen() {
		return Card.getInstance(CardShape.CLOVER, CardNumber.TEN);
	}

	private Card getFive() {
		return Card.getInstance(CardShape.CLOVER, CardNumber.FIVE);
	}
}