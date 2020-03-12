package blackjack.player;

import static blackjack.card.CardBundleHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.card.Card;
import blackjack.card.CardBundle;
import blackjack.card.component.CardNumber;
import blackjack.card.component.Symbol;

class PlayerTest {

	private static Stream<Arguments> playerProvider() {
		return Stream.of(
			Arguments.of(
				new Dealer(new CardBundle()), false, true
			),
			Arguments.of(
				new Gambler(new CardBundle(), "pobi"), true, false
			)
		);
	}

	private static Stream<Arguments> cardBundleProvider() {
		return Stream.of(
			Arguments.of(
				aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.TWO), true
			),
			Arguments.of(
				aCardBundle(CardNumber.KING, CardNumber.ACE), false
			)
		);
	}

	@DisplayName("플레이어가 겜블러인지, 딜러인지 확인")
	@ParameterizedTest
	@MethodSource("playerProvider")
	void test(Player player, boolean gamblerResult, boolean dealerResult) {
		assertThat(player.isDealer()).isEqualTo(dealerResult);
		assertThat(player.isGambler()).isEqualTo(gamblerResult);
	}

	@DisplayName("플레이어의 패가 블랙잭인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"ACE,false", "KING,true"})
	void isBlackjack(CardNumber cardNumber, boolean result) {
		//given
		Player player = new Dealer(new CardBundle());
		Symbol club = Symbol.CLUB;
		player.addCard(Card.of(club, CardNumber.ACE));
		player.addCard(Card.of(club, cardNumber));

		//when
		boolean isBlackjack = player.isBlackjack();
		//then
		assertThat(isBlackjack).isEqualTo(result);
	}

	@DisplayName("플레이어의 패가 21을 초과했는지 확인")
	@ParameterizedTest
	@MethodSource("cardBundleProvider")
	void isBurst(CardBundle cardBundle, boolean result) {
		//given
		Player player = new Dealer(cardBundle);
		//when
		boolean isBurst = player.isBurst();
		//than
		assertThat(isBurst).isEqualTo(result);
	}
}