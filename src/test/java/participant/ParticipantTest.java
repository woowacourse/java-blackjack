package participant;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Card;
import card.CardHand;
import card.Rank;
import card.Suit;
import duel.DuelResult;
import paticipant.Participant;

public class ParticipantTest {

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
			final boolean bustActual = bustedParticipant.isBust();
			final boolean notBustActual = notBustedParticipant.isBust();

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
			final boolean blackjackActual = blackjackParticipant.isBlackjack();
			final boolean notBlackjackActual = notBlackjackParticipant.isBlackjack();

			// then
			assertSoftly(s -> {
				s.assertThat(blackjackActual).isTrue();
				s.assertThat(notBlackjackActual).isFalse();
			});
		}
	}

	@Nested
	@DisplayName("duel result 반환 로직")
	class CalculateDuelResult {

		@DisplayName("win이 가장 많다면 WIN을 반환한다.")
		@Test
		void calculateDuelResult1() {
			// given
			final Participant participant = new Participant();
			participant.writeDuelResult(DuelResult.WIN);

			// when
			final DuelResult duelResult = participant.calculateDuelResult();

			// then
			assertThat(duelResult).isEqualTo(DuelResult.WIN);
		}

		@DisplayName("lose가 가장 많다면 LOSE를 반환한다.")
		@Test
		void calculateDuelResult2() {
			// given
			final Participant participant = new Participant();
			participant.writeDuelResult(DuelResult.LOSE);

			// when
			final DuelResult duelResult = participant.calculateDuelResult();

			// then
			assertThat(duelResult).isEqualTo(DuelResult.LOSE);
		}

		@DisplayName("DRAW가 가장 많다면 DRAW를 반환한다.")
		@Test
		void calculateDuelResult3() {
			// given
			final Participant participant = new Participant();
			participant.writeDuelResult(DuelResult.DRAW);

			// when
			final DuelResult duelResult = participant.calculateDuelResult();

			// then
			assertThat(duelResult).isEqualTo(DuelResult.DRAW);
		}
	}
}
