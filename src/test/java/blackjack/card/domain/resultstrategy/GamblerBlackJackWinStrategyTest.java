package blackjack.card.domain.resultstrategy;

import static blackjack.card.domain.CardBundleHelper.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.card.domain.CardBundle;
import blackjack.card.domain.component.CardNumber;

class GamblerBlackJackWinStrategyTest {

	@DisplayName("두개의 카드번들을 비교하여 겜블러가 블랙잭 승리인지 확인")
	@Test
	void isResult() {
		//given
		GameResultStrategy gamblerBlackJackWinStrategy = new GamblerBlackJackWinStrategy();
		CardBundle gamblerCardBundle = aCardBundle(CardNumber.TEN, CardNumber.ACE);
		CardBundle dealerCardBundle = aCardBundle(CardNumber.TEN, CardNumber.TWO);

		// when
		boolean actual = gamblerBlackJackWinStrategy.isResult(dealerCardBundle, gamblerCardBundle);

		//then
		assertThat(actual).isTrue();
	}
}