package blackjack.domain.role;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.factory.CardMockFactory;

@DisplayName("Roles 테스트")
class RolesTest {

	final Roles roles = new Roles();

	@Test
	@DisplayName("딜러에게 카드가 제대로 배분되는지 확인")
	void distribute_Cards_To_Dealer() {
		final Deck deck = new DeckMock(initCards());

		roles.initDealer();
		final Role dealer = roles.distributeCardToDealer(deck);

		assertAll(
			() -> assertThat(dealer.getCardsInformation().size()).isEqualTo(1),
			() -> assertThat(dealer.getCardsInformation()).isEqualTo(List.of("A클로버"))
		);
	}

	@Test
	@DisplayName("플레이어에게 카드가 제대로 배분되는지 확인")
	void distribute_Cards_to_Player() {
		final List<Card> cards = initCards();
		final Deck deck = new DeckMock(cards);

		roles.joinPlayers(List.of("player"));
		final List<Role> players = roles.distributeCardToPlayers(deck);
		final Role actualPlayer = players.get(0);

		assertThat(actualPlayer.getCardsInformation()).isEqualTo(List.of("A클로버", "10클로버"));

	}

	private List<Card> initCards() {
		return Arrays.asList(CardMockFactory.of("A클로버"), CardMockFactory.of("10클로버"));
	}

}