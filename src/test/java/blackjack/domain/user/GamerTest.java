package blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.MockDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckStrategy;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

class GamerTest {
	@Test
	@DisplayName("카드 분배 기능 이용했을 때 게이머의 패가 1장 늘어나는지 확인")
	void distribute_card() {
		//given
		DeckStrategy deck = new Deck();
		Gamer gamer = new Player("pobi", 1000, deck);
		//when
		int originalSize = gamer.getCards().size();
		gamer.addCard(deck.distributeCard());
		//then
		int changedSize = gamer.getCards().size();
		assertThat(changedSize).isEqualTo(originalSize + 1);
	}

	@Test
	@DisplayName("에이스가 포함된 패일 경우 최적의 스코어가 반환되는지 확인")
	void check_optimal_ace_sum() {
		// given
		DeckStrategy deck = new MockDeck(List.of(Card.of(Denomination.ACE, Suit.HEART),
			Card.of(Denomination.ACE, Suit.SPADE)));
		Gamer gamer = new Player("pobi", 1000, deck);
		gamer.addCard(Card.of(Denomination.NINE, Suit.SPADE));
		// when
		// then
		assertThat(gamer.getScore()).isEqualTo(21);
	}
}
