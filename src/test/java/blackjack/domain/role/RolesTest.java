package blackjack.domain.role;

import static org.assertj.core.api.AssertionsForClassTypes.*;

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
		final List<Card> cards = Arrays.asList(CardMockFactory.of("A클로버"),
			CardMockFactory.of("10클로버"));
		final Deck deck = new DeckMock(cards);
		roles.initDealer();
		final Role dealer = roles.distributeCardToDealer(deck);
		assertThat(dealer.getCardsInformation().size()).isEqualTo(1);
	}

}