package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.exceptions.PlayerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.testAssistant.TestFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

	@DisplayName("of()가 딜러 이름일 경우 예외를 던지는지 테스트")
	@Test
	void of_HasDealerName_ThrowPlayerException() {
		assertThatThrownBy(() -> createPlayerByName("딜러"))
				.isInstanceOf(PlayerException.class);
	}

	@DisplayName("receiveCard()가 카드를 받는지 테스트")
	@ParameterizedTest
	@MethodSource("receiveCard_Cards_GiveTopCardPlayer")
	void receiveCard_Cards_GiveTopCardPlayer(Player player, Card card, Player expect) {
		player.receiveCard(card);

		assertThat(player).isEqualTo(expect);
	}

	static Stream<Arguments> receiveCard_Cards_GiveTopCardPlayer() {
		return Stream.of(
				Arguments.of(createPlayerByHand(),
						createCard("ACE,SPADE"),
						createPlayerByHand("ACE,SPADE")),
				Arguments.of(createPlayerByHand("ACE,SPADE", "TWO,HEART"),
						createCard("THREE,CLUB"),
						createPlayerByHand("ACE,SPADE", "TWO,HEART", "THREE,CLUB"))
		);
	}

	@DisplayName("receiveCards()가 여러 장의 카드를 주는지 테스트")
	@ParameterizedTest
	@MethodSource("receiveCards_Cards_GiveCardsPlayer")
	void receiveCards_Cards_GiveCardsPlayer(Player player, List<Card> cards, Player expect) {
		player.receiveCards(cards);

		assertThat(player).isEqualTo(expect);
	}

	static Stream<Arguments> receiveCards_Cards_GiveCardsPlayer() {
		return Stream.of(
				Arguments.of(createPlayerByHand(),
						createCards(),
						createPlayerByHand()),
				Arguments.of(createPlayerByHand("ACE,SPADE"),
						createCards("TWO,HEART", "THREE,CLUB"),
						createPlayerByHand("ACE,SPADE", "TWO,HEART", "THREE,CLUB"))
		);
	}

	@DisplayName("canReceiveCard()가 버스트되지 않았을 시 true를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("canReceiveCard_NotBusted_ReturnTrue")
	void canReceiveCard_NotBusted_ReturnTrue(Player player) {
		assertThat(player.canReceiveCard()).isTrue();
	}

	static Stream<Player> canReceiveCard_NotBusted_ReturnTrue() {
		return Stream.of(createPlayerByHand("ACE,SPADE", "JACK,CLUB"),
				createPlayerByHand("TWO,CLUB"),
				createPlayerByHand("ACE,HEART"),
				createPlayerByHand("TEN,CLUB", "JACK,HEART", "ACE,SPADE"));
	}

	@DisplayName("canReceiveCard()가 버스트된 경우 false를 반환하는지 테스트")
	@ParameterizedTest
	@MethodSource("canReceiveCard_Busted_ReturnFalse")
	void canReceiveCard_Busted_ReturnFalse(Player player) {
		assertThat(player.canReceiveCard()).isFalse();
	}

	static Stream<Player> canReceiveCard_Busted_ReturnFalse() {
		return Stream.of(createPlayerByHand("TEN,CLUB", "JACK,HEART", "TWO,CLUB"),
				createPlayerByHand("TEN,CLUB", "JACK,HEART", "ACE,SPADE", "ACE,SPADE"),
				createPlayerByHand("TEN,SPADE", "KING,SPADE", "JACK,HEART"));
	}
}
