package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import constant.Emblem;

public class DeckTest {

	@Nested
	@DisplayName("덱 생성")
	class CreateDeck {

		@DisplayName("Deck에 동일한 카드가 들어오면, 예외가 발생한다.")
		@Test
		public void validateDuplicate() throws Exception {
			// given
			final Card club = new Card(Rank.TWO, Emblem.CLUB);
			final var q = new ArrayDeque<>(List.of(club, club));

			// when & then
			assertThatThrownBy(() -> new Deck(q))
				.isInstanceOf(IllegalArgumentException.class);
		}

	}

	@Nested
	@DisplayName("카드를 순서대로 뽑는다.")
	class pickCard {
		@DisplayName("카드를 올바르게 뽑아온다.")
		@Test
		public void pickCard() throws Exception {
			// given
			final Rank cardNumber = Rank.TWO;
			final var d = new ArrayDeque<>(List.of(new Card(cardNumber, Emblem.CLUB)));
			final var deck = new Deck(d);
			final var expected = new Card(cardNumber, Emblem.CLUB);

			// when
			final var actual = deck.pickCard();

			// then
			assertThat(actual).isEqualTo(expected);
		}

	}
}
