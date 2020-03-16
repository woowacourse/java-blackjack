package blackjack.player.domain;

import static blackjack.card.domain.CardBundleHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;

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
				aCardBundle(CardNumber.KING, CardNumber.KING, CardNumber.TWO), false
			),
			Arguments.of(
				aCardBundle(CardNumber.KING, CardNumber.ACE), true
			)
		);
	}

	@DisplayName("플레이어의 이름이 없으면 Exception")
	@ParameterizedTest
	@NullAndEmptySource
	void name(String arongName) {
		assertThatThrownBy(() -> new Gambler(new CardBundle(), arongName))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("플레이어가 겜블러인지, 딜러인지 확인")
	@ParameterizedTest
	@MethodSource("playerProvider")
	void test(Player player, boolean gamblerExpect, boolean dealerExpect) {
		assertThat(player.isDealer()).isEqualTo(dealerExpect);
		assertThat(player.isGambler()).isEqualTo(gamblerExpect);
	}

	@DisplayName("플레이어의 패가 블랙잭인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"ACE,false", "KING,true"})
	void isBlackjack(CardNumber cardNumber, boolean expect) {
		//given
		Player player = new Dealer(new CardBundle());
		Symbol club = Symbol.CLUB;
		player.addCard(Card.of(club, CardNumber.ACE));
		player.addCard(Card.of(club, cardNumber));

		//when
		boolean isBlackjack = player.isBlackjack();
		//then
		assertThat(isBlackjack).isEqualTo(expect);
	}

	@DisplayName("플레이어의 패가 21이하인지 확인")
	@ParameterizedTest
	@MethodSource("cardBundleProvider")
	void isBurst(CardBundle cardBundle, boolean expect) {
		//given
		Player player = new Dealer(cardBundle);
		//when
		boolean notBurst = player.isNotBurst();
		//than
		assertThat(notBurst).isEqualTo(expect);
	}

	@DisplayName("플레이어의 패가 21이하인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"ACE,true", "TWO,false"})
	void isNotBurst(CardNumber cardNumber, boolean expect) {
		//given
		Player player = new Dealer(new CardBundle());
		player.addCard(Card.of(Symbol.HEART, CardNumber.TEN));
		player.addCard(Card.of(Symbol.HEART, CardNumber.TEN));
		player.addCard(Card.of(Symbol.HEART, cardNumber));
		//when
		boolean notBurst = player.isNotBurst();
		//then
		assertThat(notBurst).isEqualTo(expect);
	}
}