package blackjack.domain.role;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.RedrawChoice;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.factory.CardMockFactory;

@DisplayName("Roles 테스트")
class RolesTest {

	final Roles roles = new Roles();

	@Test
	@DisplayName("딜러에게 카드가 제대로 배분되는지 확인")
	void distribute_Cards_To_Dealer() {
		final Deck deck = initDeck();

		roles.initDealer();
		final Role dealer = roles.distributeCardToDealer(deck);

		assertAll(
			() -> assertThat(dealer.getCardsName().size()).isEqualTo(1),
			() -> assertThat(dealer.getCardsName()).isEqualTo(List.of("A클로버"))
		);
	}

	@Test
	@DisplayName("플레이어에게 카드가 제대로 배분되는지 확인")
	void distribute_Cards_to_Player() {
		final Deck deck = initDeck();

		roles.joinPlayers(Map.of("player", 1000));
		final List<Role> players = roles.distributeCardToPlayers(deck);
		final Role actualPlayer = players.get(0);

		assertThat(actualPlayer.getCardsName()).isEqualTo(List.of("A클로버", "10클로버"));

	}

	@DisplayName("플레이어 추가 카드 드로우가 제대로 수행되는지 확인")
	@ParameterizedTest(name = "{index} {displayName} redrawChoice={0} expectedTheNumberOfCard={1}")
	@CsvSource(value = {"YES, 1", "NO, 0"})
	void check_Player_Draw_Card(final RedrawChoice redrawChoice, final int expectedTheNumberOfCard) {
		final Deck deck = initDeck();
		roles.joinPlayers(Map.of("player", 1000));
		final Role player = roles.drawPlayer(deck, redrawChoice, "player");

		assertThat(player.getCardsName().size()).isEqualTo(expectedTheNumberOfCard);
	}

	private Deck initDeck() {
		final List<Card> cards = Arrays.asList(CardMockFactory.of("A클로버"), CardMockFactory.of("10클로버"));
		return new DeckMock(cards);
	}

	@DisplayName("딜러 추가 드로우가 정상적으로 수행되는지 확인")
	@ParameterizedTest(name = "{index} {displayName}")
	@MethodSource("getDealerDeck")
	void check_dealer_Draw_Card(final Deck deck, final int expectedTheNumberOfCard) {
		roles.initDealer();
		roles.distributeCardToDealer(deck);
		final Role dealer = roles.drawDealer(deck);

		assertThat(dealer.getCardsName().size()).isEqualTo(expectedTheNumberOfCard);
	}

	private static Stream<Arguments> getDealerDeck() {
		final Deck deck1 = new DeckMock(List.of(CardMockFactory.of("6클로버"),
			CardMockFactory.of("K클로버"), CardMockFactory.of("2클로버")));
		final Deck deck2 = new DeckMock(List.of(CardMockFactory.of("7클로버"),
			CardMockFactory.of("K클로버"), CardMockFactory.of("2클로버")));
		return Stream.of(
			Arguments.of(deck1, 3),
			Arguments.of(deck2, 2)
		);

	}

	@DisplayName("재 드로우가 가능한 플레이어 이름이 제대로 반환되는지 확인")
	@ParameterizedTest(name = "{index} {displayName}")
	@MethodSource("getPlayerStatus")
	void check_Redrawable_player_name(final Deck deck, final String expectedPlayerName) {
		roles.joinPlayers(Map.of("player", 1000));
		roles.distributeCardToPlayers(deck);
		roles.drawPlayer(deck, RedrawChoice.YES, "player");
		assertThat(roles.getCurrentPlayerName()).isEqualTo(expectedPlayerName);
	}

	private static Stream<Arguments> getPlayerStatus() {
		final Deck deck1 = new DeckMock(List.of(CardMockFactory.of("9클로버"),
			CardMockFactory.of("K클로버"), CardMockFactory.of("A클로버")));
		final Deck deck2 = new DeckMock(List.of(CardMockFactory.of("Q클로버"),
			CardMockFactory.of("K클로버"), CardMockFactory.of("J클로버")));
		return Stream.of(
			Arguments.of(deck1, "player"),
			Arguments.of(deck2, "")
		);
	}

	@DisplayName("처음 드로우한 카드에 따라 배팅 금액이 제대로 합산 되는지 확인")
	@ParameterizedTest(name = "{index} {displayName}")
	@MethodSource("getBettingStatus")
	void check_Betting_Result_According_To_Black_Jack(final Deck deck, final int expectedResult) {
		roles.initDealer();
		roles.joinPlayers(Map.of("player", 1000));
		roles.distributeCardToDealer(deck);
		roles.distributeCardToPlayers(deck);
		List<Role> players = roles.calculatePlayerResult();

		assertThat(players.get(0).calculateBettingResult()).isEqualTo(expectedResult);
	}

	private static Stream<Arguments> getBettingStatus() {
		final Deck deck1 = new DeckMock(List.of(CardMockFactory.of("A클로버"),
			CardMockFactory.of("K클로버"), CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버")));
		final Deck deck2 = new DeckMock(List.of(CardMockFactory.of("2클로버"),
			CardMockFactory.of("3클로버"), CardMockFactory.of("A클로버"), CardMockFactory.of("K클로버")));

		return Stream.of(
			Arguments.of(deck1, 0),
			Arguments.of(deck2, 500)
		);
	}
}