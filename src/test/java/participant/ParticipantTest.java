package participant;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardHand;
import card.Rank;
import card.Suit;
import paticipant.Participant;

public class ParticipantTest {

	private static final int BUST_SCORE = 21;

	@Nested
	@DisplayName("Bust 여부 검증")
	class IsBust {

		@DisplayName("점수가 bustScore를 초과한다면 true, 아니라면 false를 반환한다.")
		@Test
		void isBust() {
			// given
			final List<Card> bustedCards = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TEN, Suit.SPADE),
				new Card(Rank.TWO, Suit.CLUB)
			);
			final Participant bustedParticipant = new Participant(new CardHand(bustedCards));
			final List<Card> notBustedCards = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TEN, Suit.SPADE),
				new Card(Rank.ACE, Suit.CLUB)
			);
			final Participant notBustedParticipant = new Participant(new CardHand(notBustedCards));

			// when
			final boolean bustActual = bustedParticipant.isBust(BUST_SCORE);
			final boolean notBustActual = notBustedParticipant.isBust(BUST_SCORE);

			// then
			assertThat(bustActual).isTrue();
			assertThat(notBustActual).isFalse();
		}
	}
}
