package money;

import static org.assertj.core.api.Assertions.*;

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
import paticipant.Player;

public class WagerResultCalculatorTest {

	@Nested
	@DisplayName("베팅 결과 계산")
	class CalculateWagerResult {

		@DisplayName("플레이어가 Bust이고, 1회 패배라면, 모든 돈을 잃는다.")
		@Test
		void calculatePlayerBust() {
			// given
			final WagerResultCalculator wagerResultCalculator = new WagerResultCalculator();
			final List<Card> cards = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TEN, Suit.HEART),
				new Card(Rank.TEN, Suit.SPADE)
			);
			final Participant participant = new Participant(new CardHand(cards));
			participant.writeDuelResult(DuelResult.LOSE);
			final Player player = new Player("파랑", participant);

			final Money wager = new Money(1000);

			// when
			final Money wagerResult = wagerResultCalculator.calculate(player, wager);

			// then
			assertThat(wagerResult.getValue()).isZero();
		}

		@DisplayName("플레이어가 무승부라면, 금액을 그대로 반환한다.")
		@Test
		void calculatePlayerBust1() {
			// given
			final WagerResultCalculator wagerResultCalculator = new WagerResultCalculator();
			final List<Card> cards = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TEN, Suit.HEART),
				new Card(Rank.TEN, Suit.SPADE)
			);
			final Participant participant = new Participant(new CardHand(cards));
			participant.writeDuelResult(DuelResult.DRAW);
			final Player player = new Player("파랑", participant);

			final Money wager = new Money(1_000);

			// when
			final Money wagerResult = wagerResultCalculator.calculate(player, wager);

			// then
			assertThat(wagerResult.getValue()).isEqualTo(1_000);
		}

		@DisplayName("플레이어가 우승이면서, 블랙잭이라면 2.5배를 반환한다. (베팅금액의 1.5배를 더해서 반환)")
		@Test
		void calculatePlayerBust2() {
			// given
			final WagerResultCalculator wagerResultCalculator = new WagerResultCalculator();
			final List<Card> cards = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.ACE, Suit.HEART)
			);
			final Participant participant = new Participant(new CardHand(cards));
			participant.writeDuelResult(DuelResult.WIN);
			final Player player = new Player("파랑", participant);

			final Money wager = new Money(1_000);

			// when
			final Money wagerResult = wagerResultCalculator.calculate(player, wager);

			// then
			assertThat(wagerResult.getValue()).isEqualTo(2_500);
		}

		@DisplayName("플레이어가 우승이면서, 블랙잭이 아니라면 2배를 반환한다. (베팅금액을 추가로 더해서 반환)")
		@Test
		void calculatePlayerBust3() {
			// given
			final WagerResultCalculator wagerResultCalculator = new WagerResultCalculator();
			final Participant participant = new Participant();
			participant.writeDuelResult(DuelResult.WIN);
			final Player player = new Player("파랑", participant);

			final Money wager = new Money(1_000);

			// when
			final Money wagerResult = wagerResultCalculator.calculate(player, wager);

			// then
			assertThat(wagerResult.getValue()).isEqualTo(1_500);
		}
	}
}
