package participant;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import card.Card;
import card.CardHand;
import card.Rank;
import card.Suit;
import paticipant.Participant;
import paticipant.Player;

public class PlayerTest {

	@Nested
	@DisplayName("플레아어가 카드를 뽑을지 여부를 반환한다.")
	class IsPickCard {

		@DisplayName("점수가 21점 이하라면, 카드를 뽑을 수 있는지 여부로 true를 반환하고 초과라면, true를 반환한다.")
		@ParameterizedTest
		@MethodSource("providePlayerHand")
		public void isPickCard(final List<Card> cards, final boolean expected) {
			// given
			final CardHand cardHand = new CardHand(cards);
			final Participant participant = new Participant(cardHand);
			final Player p = new Player("", participant);

			// when
			final boolean actual = p.isPickCard();

			// then
			assertThat(actual).isEqualTo(expected);
		}

		private static Stream<Arguments> providePlayerHand() {
			return Stream.of(
				Arguments.of(
					List.of(new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TEN, Suit.DIAMOND),
						new Card(Rank.ACE, Suit.CLUB)), true),
				Arguments.of(
					List.of(new Card(Rank.TEN, Suit.DIAMOND),
						new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TWO, Suit.CLUB)), false)
			);
		}
	}

	@Nested
	@DisplayName("플레이어의 Bust 여부 체크")
	class IsBust {

		@DisplayName("플레이어의 현재 점수가 bustScore 초과라면 true를 반환한다.")
		@Test
		void isBust() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.TEN, Suit.CLUB), new Card(Rank.TEN, Suit.SPADE),
				new Card(Rank.TEN, Suit.HEART));
			final Participant participant = new Participant(new CardHand(playerCards));
			final Player bustedPlayer = new Player("", participant);

			// when
			boolean actual = bustedPlayer.isBust();

			// then
			assertThat(actual).isTrue();
		}

		@DisplayName("플레이어의 현재 점수가 bustScore 이하려면 false를 반환한다.")
		@Test
		void isBust1() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.TEN, Suit.HEART));
			final Participant participant = new Participant(new CardHand(playerCards));
			final Player noBustedPlayer = new Player("", participant);

			// when
			boolean actual = noBustedPlayer.isBust();

			// then
			assertThat(actual).isFalse();
		}
	}

	@Nested
	@DisplayName("player의 점수를 계산한다.")
	class CalculateAllGameScore {

		@DisplayName("player의 현재 점수를 계산한다.")
		@Test
		void calculateAllScore() {
			// given
			final List<Card> playerCards = List.of(new Card(Rank.ACE, Suit.CLUB), new Card(Rank.TEN, Suit.HEART));
			final Participant participant = new Participant(new CardHand(playerCards));
			final Player player = new Player("", participant);

			// when
			final int actual = player.calculateAllScore().value();

			// then
			assertThat(actual).isEqualTo(21);
		}
	}

}
