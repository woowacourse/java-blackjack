package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.constant.Suit;

public class BlackjackTest {

	@Nested
	@DisplayName("플레이어 카드 뽑는 여부 체크")
	class IsPickCardByPlayer {

		@DisplayName("플레이어의 점수가 BUST_SCORE 이하라면 true를 반환한다.")
		@Test
		void isPickCardByPlayers() {
			// given
			final Card card1 = new Card(Rank.ACE, Suit.CLUB);
			final Card card2 = new Card(Rank.TEN, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Participant participant = new Participant(cardHand);
			final Player player = new Player("", participant);
			final Blackjack blackjack = new Blackjack();

			// when
			boolean actual = blackjack.isPickCardByPlayer(player);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("플레이어의 점수가 BUST_SCORE 초과라면 false를 반환한다.")
		@Test
		void isPickCardByPlayers1() {
			// given
			final Card card1 = new Card(Rank.TEN, Suit.CLUB);
			final Card card2 = new Card(Rank.TEN, Suit.CLUB);
			final Card card3 = new Card(Rank.TWO, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2, card3));
			final Participant participant = new Participant(cardHand);
			final Player player = new Player("", participant);
			final Blackjack blackjack = new Blackjack();

			// when
			boolean actual = blackjack.isPickCardByPlayer(player);

			// then
			assertThat(actual).isFalse();
		}

	}

	@Nested
	@DisplayName("딜러 카드 뽑는 여부 체크")
	class IsPickCardByDealer {

		@DisplayName("딜러 점수가 주어진 조건 이하라면 true를 반환한다.")
		@Test
		void isPickCardByDealer() {
			// given
			final Card card1 = new Card(Rank.SIX, Suit.CLUB);
			final Card card2 = new Card(Rank.TEN, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Dealer dealer = new Dealer(cardHand);
			final Blackjack blackjack = new Blackjack();

			// when
			boolean actual = blackjack.isPickCardByDealer(dealer);

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("딜러 점수가 주어진 조건 초과라면 false를 반환한다.")
		@Test
		void isPickCardByDealer1() {
			// given
			final Card card1 = new Card(Rank.TEN, Suit.CLUB);
			final Card card2 = new Card(Rank.SEVEN, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Dealer dealer = new Dealer(cardHand);
			final Blackjack blackjack = new Blackjack();

			// when
			boolean actual = blackjack.isPickCardByDealer(dealer);

			// then
			assertThat(actual).isFalse();
		}
	}

	@Nested
	@DisplayName("BUST_SCORE를 기준으로 점수를 계산한다.")
	class CalculateScore {

		@DisplayName("BUST_SCORE를 기준으로 점수를 계산한다.")
		@Test
		void calculateScore() {
			// given
			final Card card1 = new Card(Rank.ACE, Suit.CLUB);
			final Card card2 = new Card(Rank.ACE, Suit.HEART);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Participant participant = new Participant(cardHand);
			final Blackjack blackjack = new Blackjack();

			// when
			final int actual = blackjack.calculateScore(participant);

			// then
			assertThat(actual).isEqualTo(12);
		}
	}

	@Nested
	@DisplayName("BUST_SCORE를 기준으로 duel을 진행한다.")
	class DuelDealerVsPlayer {

		@DisplayName("Dealer와 player는 결투를 진행한다.")
		@Test
		void duelDealerVsPlayer() {
			// given
			final Card card1 = new Card(Rank.TEN, Suit.CLUB);
			final Card card2 = new Card(Rank.SEVEN, Suit.CLUB);
			final CardHand cardHand = new CardHand(List.of(card1, card2));
			final Participant participant = new Participant(cardHand);
			final Dealer dealer = new Dealer(cardHand);
			final Player player = new Player("", participant);
			final Blackjack blackjack = new Blackjack();

			// when & then
			assertThatNoException().isThrownBy(() -> blackjack.duelDealerVsPlayer(dealer, player));

		}
	}
}
