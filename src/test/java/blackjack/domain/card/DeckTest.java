package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
	List<Card> cards = new ArrayList<>();

	@BeforeEach
	void init() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
	}

	@DisplayName("카드가 중복되면 예외를 발생시킨다.")
	@Test
	void cardDuplicateTest() {
		cards.remove(0);
		Card card = cards.remove(0);

		cards.add(card);
		cards.add(card);

		Assertions.assertThatThrownBy(() -> new Deck(cards))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("카드는 중복될 수 없습니다.");
	}

	@DisplayName("카드가 52장이 아니라면 예외를 발생시킨다.")
	@Test
	void cardInitSizeTest() {
		cards.remove(0);

		Assertions.assertThatThrownBy(() -> new Deck(cards))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("덱의 사이즈가 52장이 아닙니다.");
	}

	@DisplayName("카드 1장을 드로우한다.")
	@Test
	void dealCardTest() {
		// given
		Deck deck = new Deck(cards);

		// when
		Card card = deck.drawCard();

		// then
		assertThat(deck)
			.extracting("cards")
			.isNotIn(card);
	}

	@DisplayName("카드 n장을 드로우한다.")
	@Test
	void dealCardsTest() {
		// given
		Deck deck = new Deck(cards);

		// when
		List<Card> cards = deck.drawCards(2);

		// then
		// CONCERN POINT: contains 메서드를 만들 지, Extract 방식을 사용할 지 고민해보기
		assertThat(deck)
			.extracting("cards")
			.isNotIn(cards);
	}

	@DisplayName("남은 카드가 없을 때 드로우하면 예외를 발생시킨다.")
	@Test
	void emptyDeckDrawTest() {
		// given
		Deck deck = new Deck(cards);
		deck.drawCards(Deck.INIT_SIZE);

		// when & then
		assertThatThrownBy(deck::drawCard)
			.isInstanceOf(IndexOutOfBoundsException.class);
	}
}