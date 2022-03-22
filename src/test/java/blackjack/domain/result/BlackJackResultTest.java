package blackjack.domain.result;

import static blackjack.CardConstant.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;

class BlackJackResultTest {

	@Test
	@DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 승리")
	void playerBlackJackAndDealerNotBlackJAck() {
		Cards playerCards = new Cards(CLOVER_ACE, CLOVER_TEN);
		Cards dealerCards = new Cards(CLOVER_FIVE, CLOVER_FIVE, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.BLACKJACK_WIN);
	}

	@Test
	@DisplayName("플레이어가 버스트 아닌데 딜러가 버스트면 승리")
	void playerNotBustAndDealerBust() {
		Cards playerCards = new Cards(CLOVER_ACE, CLOVER_ACE);
		Cards dealerCards = new Cards(CLOVER_TEN, CLOVER_ACE, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.WIN);
	}

	@ParameterizedTest
	@CsvSource(value = {"TEN:WIN", "EIGHT:LOSE", "NINE:DRAW"}, delimiter = ':')
	@DisplayName("둘 다 버스트가 아닐 때 숫자 합에 따라 승패가 갈린다.")
	void notBustCompareWin(String cardNumber, String result) {
		Cards playerCards = new Cards(CLOVER_TEN,
			Card.getInstance(CardShape.CLOVER, CardNumber.valueOf(cardNumber)));
		Cards dealerCards = new Cards(CLOVER_TEN, CLOVER_NINE);

		BlackJackResult blackJackResult = BlackJackResult.of(playerCards, dealerCards);
		assertThat(blackJackResult).isEqualTo(BlackJackResult.valueOf(result));
	}

	@Test
	@DisplayName("둘 다 버스트이면 플레이어 패배")
	void bothBust() {
		Cards playerCards = new Cards(CLOVER_TEN, CLOVER_ACE, CLOVER_ACE);
		Cards dealerCards = new Cards(CLOVER_TEN, CLOVER_ACE, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.LOSE);
	}

	@Test
	@DisplayName("딜러만 블랙잭이면 플레이어 패배")
	void playerNotBlackJackAndDealerBlackJack() {
		Cards playerCards = new Cards(CLOVER_FIVE, CLOVER_FIVE, CLOVER_ACE);
		Cards dealerCards = new Cards(CLOVER_TEN, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.LOSE);
	}

	@Test
	@DisplayName("둘 다 블랙잭이면 무승부")
	void bothBlackJack() {
		Cards playerCards = new Cards(CLOVER_TEN, CLOVER_ACE);
		Cards dealerCards = new Cards(CLOVER_TEN, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.DRAW);
	}

	@Test
	@DisplayName("블랙잭으로 이기면 수익률 1.5")
	void blackJackWinProfit() {
		Cards playerCards = new Cards(CLOVER_ACE, CLOVER_TEN);
		Cards dealerCards = new Cards(CLOVER_FIVE, CLOVER_FIVE, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result.getProfit()).isEqualTo(1.5);
	}

	@Test
	@DisplayName("블랙잭이 아니게 이기면 수익률 1")
	void normalWinProfit() {
		Cards playerCards = new Cards(CLOVER_ACE, CLOVER_ACE);
		Cards dealerCards = new Cards(CLOVER_TEN, CLOVER_ACE, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result.getProfit()).isEqualTo(1);
	}

	@Test
	@DisplayName("패배하면 수익률 -1")
	void loseProfit() {
		Cards playerCards = new Cards(CLOVER_TEN, CLOVER_ACE, CLOVER_ACE);
		Cards dealerCards = new Cards(CLOVER_TEN, CLOVER_ACE, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result.getProfit()).isEqualTo(-1);
	}

	@Test
	@DisplayName("무승부이면 수익률 0")
	void drawProfit() {
		Cards playerCards = new Cards(CLOVER_TEN, CLOVER_ACE);
		Cards dealerCards = new Cards(CLOVER_TEN, CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result.getProfit()).isEqualTo(0);
	}
}