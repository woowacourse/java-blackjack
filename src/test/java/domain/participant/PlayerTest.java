package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;
import domain.result.WinOrLose;

public class PlayerTest {

	@Test
	@DisplayName("딜러 블랙잭 발생 시 승패 판단")
	void compareAtDealerBlackJack() {
		Hand handForPlayer = new Hand(
			List.of(new Card(Denomination.EIGHT, Suit.CLOVER), new Card(Denomination.ACE, Suit.CLOVER)));
		Player player = new Player(new Name("pobi"), handForPlayer, new Betting(0));

		Hand handForDealer = new Hand(
			List.of(new Card(Denomination.JACK, Suit.CLOVER), new Card(Denomination.ACE, Suit.CLOVER)));
		Dealer dealer = new Dealer(handForDealer);

		assertThat(player.getResult(dealer)).isEqualTo(WinOrLose.LOSE);
	}

	@Test
	@DisplayName("최종 결과를 위한 승패 판단")
	void compareAtFinal() {
		Hand handForPlayer = new Hand(
			List.of(new Card(Denomination.EIGHT, Suit.CLOVER), new Card(Denomination.ACE, Suit.CLOVER)));
		Player player = new Player(new Name("pobi"), handForPlayer, new Betting(0));

		Hand handForDealer = new Hand(
			List.of(new Card(Denomination.JACK, Suit.CLOVER), new Card(Denomination.ACE, Suit.CLOVER)));
		Dealer dealer = new Dealer(handForDealer);

		assertThat(player.getResult(dealer)).isEqualTo(WinOrLose.LOSE);
	}

	@Test
	@DisplayName("플레이어 블랙잭 발생 시 승패 판단")
	void compareAtPlayerBlackJack() {
		Hand handForPlayer = new Hand(
			List.of(new Card(Denomination.TEN, Suit.CLOVER), new Card(Denomination.ACE, Suit.CLOVER)));
		Player player = new Player(new Name("pobi"), handForPlayer, new Betting(0));

		Hand handForDealer = new Hand(
			List.of(new Card(Denomination.NINE, Suit.CLOVER), new Card(Denomination.ACE, Suit.CLOVER)));
		Dealer dealer = new Dealer(handForDealer);

		assertThat(player.getResult(dealer)).isEqualTo(WinOrLose.BLACK_JACK_WIN);
	}

	@Test
	@DisplayName("플레이어, 딜러 블랙잭 동시 발생 시 승패 판단")
	void compareAtPlayerAndDealerBlackJack() {
		Hand handForPlayer = new Hand(
			List.of(new Card(Denomination.TEN, Suit.CLOVER), new Card(Denomination.ACE, Suit.CLOVER)));
		Player player = new Player(new Name("pobi"), handForPlayer, new Betting(0));

		Hand handForDealer = new Hand(
			List.of(new Card(Denomination.JACK, Suit.CLOVER), new Card(Denomination.ACE, Suit.CLOVER)));
		Dealer dealer = new Dealer(handForDealer);

		assertThat(player.getResult(dealer)).isEqualTo(WinOrLose.DRAW);
	}
}
