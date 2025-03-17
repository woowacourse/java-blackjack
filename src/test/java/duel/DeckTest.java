package duel;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Card;
import card.Deck;
import card.Rank;
import card.Suit;

public class DeckTest {

	@Nested
	@DisplayName("덱 생성")
	class CreateDeck {

		@DisplayName("Deck에 동일한 카드가 들어오면, 예외가 발생한다.")
		@Test
		public void validateDuplicate() {
			// given
			final Card club = new Card(Rank.TWO, Suit.CLUB);
			final var q = new ArrayDeque<>(List.of(club, club));

			// when & then
			assertThatThrownBy(() -> new Deck(q))
				.isInstanceOf(IllegalArgumentException.class);
		}

	}

	@Nested
	@DisplayName("카드를 순서대로 뽑는다.")
	class pickCard {
		@DisplayName("카드를 첫 번째부터 차례대로 뽑아서 반환한다.")
		@Test
		public void pickCard() {
			// given
			final Rank rank = Rank.TWO;
			final var d = new ArrayDeque<>(List.of(new Card(rank, Suit.CLUB)));
			final var deck = new Deck(d);
			final var expected = new Card(rank, Suit.CLUB);

			// when
			final var actual = deck.pickCard();

			// then
			assertThat(actual).isEqualTo(expected);
		}
	}

	@DisplayName("덱에 카드가 존재하지 않을 때, 카드를 뽑는다면 예외가 발생한다.")
	@Test
	public void pickCardEmpty() {
		// given
		final var deck = new Deck(new ArrayDeque<>());
		// when&then
		assertThatThrownBy(deck::pickCard)
			.isInstanceOf(NullPointerException.class)
			.hasMessageContaining("덱에 남아있는 카드가 없습니다.");
	}

}
