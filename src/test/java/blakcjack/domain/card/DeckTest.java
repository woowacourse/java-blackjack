package blakcjack.domain.card;

import blakcjack.domain.shufflestrategy.RandomShuffleStrategy;
import blakcjack.domain.shufflestrategy.ShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static blakcjack.domain.card.EmptyDeckException.EMPTY_DECK_ERROR;
import static org.assertj.core.api.Assertions.*;

class DeckTest {
	@DisplayName("덱 사이즈가 정확 52장이 맞는지 체크")
	@Test
	void create_deck_sizeShouldBe52() {
		final Deck deck = new Deck(new RandomShuffleStrategy());

		assertThatCode(() -> consumeAllCards(deck)).doesNotThrowAnyException();
		assertThatThrownBy(deck::pop).isInstanceOf(EmptyDeckException.class)
				.hasMessage(EMPTY_DECK_ERROR);
	}

	private void consumeAllCards(final Deck deck) {
		for (int i = 0; i < 52; i++) {
			deck.pop();
		}
	}

	@DisplayName("덱 내 중복되는 카드가 없는지 확인")
	@Test
	void create_deck_noDuplicateCards() {
		final int cardSize = 52;
		final Deck deck = new Deck(new RandomShuffleStrategy());
		final Set<Card> cards = new HashSet<>();

		for (int i = 0; i < cardSize; i++) {
			cards.add(deck.pop());
		}

		assertThat(cards).hasSize(cardSize);
	}

	@DisplayName("카드를 뽑는 메서드가 제대로 카드를 뽑는지")
	@Test
	void draw() {
		final ShuffleStrategy nonShuffleStrategy = cards -> {
		};
		final Deck deck = new Deck(nonShuffleStrategy);
		final CardSymbol spade = CardSymbol.values()[3];
		final CardNumber k = CardNumber.values()[12];
		final CardNumber q = CardNumber.values()[11];
		final CardNumber j = CardNumber.values()[10];

		assertThat(deck.pop()).isEqualTo(Card.of(spade, k));
		assertThat(deck.pop()).isEqualTo(Card.of(spade, q));
		assertThat(deck.pop()).isEqualTo(Card.of(spade, j));
	}
}