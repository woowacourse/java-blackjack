package participant;

import static org.assertj.core.api.SoftAssertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardHand;
import card.Rank;
import card.Score;
import card.Suit;
import paticipant.Participant;

public class ParticipantTest {

	private static final Score BUST_SCORE = Score.from(21);

	@Nested
	@DisplayName("검증 로직")
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
			assertSoftly(s -> {
				s.assertThat(bustActual).isTrue();
				s.assertThat(notBustActual).isFalse();
			});
		}

		@DisplayName("카드가 2장이고 21점이라면, true 아니라면 false를 반환한다.")
		@Test
		void isBlackjack() {
			// given
			final List<Card> blackjackCards = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.ACE, Suit.CLUB)
			);
			final Participant blackjackParticipant = new Participant(new CardHand(blackjackCards));
			final List<Card> notBustedCards = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TEN, Suit.SPADE)
			);
			final Participant notBlackjackParticipant = new Participant(new CardHand(notBustedCards));

			// when
			final boolean blackjackActual = blackjackParticipant.isBlackjack(BUST_SCORE);
			final boolean notBlackjackActual = notBlackjackParticipant.isBlackjack(BUST_SCORE);

			// then
			assertSoftly(s -> {
				s.assertThat(blackjackActual).isTrue();
				s.assertThat(notBlackjackActual).isFalse();
			});
		}
	}
}
