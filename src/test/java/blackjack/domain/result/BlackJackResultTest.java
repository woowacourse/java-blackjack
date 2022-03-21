package blackjack.domain.result;

import static blackjack.CardConstant.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Cards;

class BlackJackResultTest {

	private final Cards playerCards = new Cards();
	private final Cards dealerCards = new Cards();

	@Test
	@DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 승리")
	void playerBlackJackAndDealerNotBlackJAck() {
		playerCards.add(CLOVER_ACE);
		playerCards.add(CLOVER_TEN);
		dealerCards.add(CLOVER_FIVE);

		dealerCards.add(CLOVER_FIVE);
		dealerCards.add(CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.BLACKJACK_WIN);
	}

	@Test
	@DisplayName("플레이어가 버스트 아닌데 딜러가 버스트면 승리")
	void playerNotBustAndDealerBust() {
		playerCards.add(CLOVER_ACE);
		playerCards.add(CLOVER_ACE);

		dealerCards.add(CLOVER_TEN);
		dealerCards.add(CLOVER_ACE);
		dealerCards.add(CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.WIN);
	}

	@Test
	@DisplayName("둘 다 버스트가 아닐 때 숫자가 높으면 승리")
	void notBustCompareWin() {
		playerCards.add(CLOVER_TEN);
		playerCards.add(CLOVER_TEN);

		dealerCards.add(CLOVER_TEN);
		dealerCards.add(CLOVER_FIVE);
		dealerCards.add(CLOVER_ACE);
		dealerCards.add(CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.WIN);
	}

	@Test
	@DisplayName("둘 다 버스트이면 플레이어 패배")
	void bothBust() {
		playerCards.add(CLOVER_TEN);
		playerCards.add(CLOVER_ACE);
		playerCards.add(CLOVER_ACE);

		dealerCards.add(CLOVER_TEN);
		dealerCards.add(CLOVER_ACE);
		dealerCards.add(CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.LOSE);
	}

	@Test
	@DisplayName("딜러만 블랙잭이면 플레이어 패배")
	void playerNotBlackJackAndDealerBlackJack() {
		playerCards.add(CLOVER_FIVE);
		playerCards.add(CLOVER_FIVE);
		playerCards.add(CLOVER_ACE);

		dealerCards.add(CLOVER_TEN);
		dealerCards.add(CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.LOSE);
	}

	@Test
	@DisplayName("둘 다 버스트가 아닐 때 숫자가 낮으면 패배")
	void notBustCompareLose() {
		playerCards.add(CLOVER_TEN);
		playerCards.add(CLOVER_FIVE);

		dealerCards.add(CLOVER_TEN);
		dealerCards.add(CLOVER_FIVE);
		dealerCards.add(CLOVER_ACE);
		dealerCards.add(CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.LOSE);
	}

	@Test
	@DisplayName("둘 다 블랙잭이면 무승부")
	void bothBlackJack() {
		playerCards.add(CLOVER_TEN);
		playerCards.add(CLOVER_ACE);

		dealerCards.add(CLOVER_TEN);
		dealerCards.add(CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.DRAW);
	}

	@Test
	@DisplayName("둘 다 버스트 아니고 숫자 같으면 무승부")
	void compareDraw() {
		playerCards.add(CLOVER_TEN);
		playerCards.add(CLOVER_FIVE);

		dealerCards.add(CLOVER_TEN);
		dealerCards.add(CLOVER_FIVE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result).isEqualTo(BlackJackResult.DRAW);
	}

	@Test
	@DisplayName("블랙잭으로 이기면 수익률 1.5")
	void blackJackWinProfit() {
		playerCards.add(CLOVER_TEN);
		playerCards.add(CLOVER_ACE);

		dealerCards.add(CLOVER_FIVE);
		dealerCards.add(CLOVER_FIVE);
		dealerCards.add(CLOVER_ACE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result.getProfit()).isEqualTo(1.5);
	}

	@Test
	@DisplayName("블랙잭이 아니게 이기면 수익률 1")
	void normalWinProfit() {
		playerCards.add(CLOVER_FIVE);
		playerCards.add(CLOVER_FIVE);
		playerCards.add(CLOVER_ACE);

		dealerCards.add(CLOVER_FIVE);
		dealerCards.add(CLOVER_FIVE);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result.getProfit()).isEqualTo(1);
	}

	@Test
	@DisplayName("패배하면 수익률 -1")
	void loseProfit() {
		playerCards.add(CLOVER_FIVE);
		playerCards.add(CLOVER_FIVE);
		playerCards.add(CLOVER_ACE);

		dealerCards.add(CLOVER_ACE);
		dealerCards.add(CLOVER_TEN);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result.getProfit()).isEqualTo(-1);
	}

	@Test
	@DisplayName("무승부이면 수익률 0")
	void drawProfit() {
		playerCards.add(CLOVER_ACE);
		playerCards.add(CLOVER_TEN);

		dealerCards.add(CLOVER_ACE);
		dealerCards.add(CLOVER_TEN);

		BlackJackResult result = BlackJackResult.of(playerCards, dealerCards);
		assertThat(result.getProfit()).isEqualTo(0);
	}
}