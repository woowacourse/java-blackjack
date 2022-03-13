package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Deck 태스트")
class DeckTest {

	private Deck deck = new Deck();

	@Test
	@DisplayName("카드 장수 검증")
	void check_Construct_Cards_Size() {
		assertThat(deck).extracting("cards")
			.asList()
			.size()
			.isEqualTo(52);
	}

	@Nested
	@DisplayName("카드를 드로우할 때")
	class DrawCardsTest {

		@Test
		@DisplayName("카드가 있으면 드로우된다")
		void has_Enough_Cars() {
			assertThatNoException().isThrownBy(deck::draw);
		}

		@Test
		@DisplayName("카드가 없으면 예외를 발생시킨다")
		void lack_Of_Cars() {
			drawAll();
			assertThatThrownBy(deck::draw)
				.isInstanceOf(NoSuchElementException.class)
				.hasMessageContaining("드로우 가능한 카드가 더이상 없습니다.");
		}
	}

	@Test
	@DisplayName("덱이 생성될 때 중복된 카드를 생성하는지 검증")
	void check_Construct_Duplicated_Card() {
		Set<Card> cards = drawAll();
		assertThat(cards.size()).isEqualTo(52);
	}

	private Set<Card> drawAll() {
		Set<Card> cards = new HashSet<>();
		for (int i = 0; i < 52; i++) {
			cards.add(deck.draw());
		}
		return cards;
	}
}
