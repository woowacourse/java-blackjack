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
import paticipant.Dealer;
import paticipant.Participant;
import paticipant.Player;
import paticipant.Players;

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
			final Players players = new Players(List.of(player));

			final List<Card> cards1 = List.of(
				new Card(Rank.TEN, Suit.CLUB),
				new Card(Rank.TEN, Suit.HEART)
			);
			final Participant participant1 = new Participant(new CardHand(cards1));
			final Dealer dealer = new Dealer(participant1);

			final Money wager = new Money(1000);

			// when
			final Money wagerResult = wagerResultCalculator.calculate(player, wager);

			// then
			assertThat(wagerResult.getValue()).isZero();
		}
	}
}
