package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.WinOrLose;

public class PlayerTest {
	private Player player;

	@BeforeEach
	void setUp() {
		Hand handForPlayer = new Hand(
			List.of(new Card(Rank.EIGHT, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER)));
		player = new Player(new Name("pobi"), handForPlayer, new Betting(0));
	}

	@Test
	@DisplayName("블랙잭 발생 시 승패 판단")
	void compareAtBlackJack() {
		List<Card> handForDealer = new ArrayList<>(
			List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER)));
		Dealer dealer = new Dealer(new Hand(handForDealer));
		assertThat(player.getResult(dealer)).isEqualTo(WinOrLose.LOSE);
	}

	@Test
	@DisplayName("최종 결과를 위한 승패 판단")
	void compareAtFinal() {
		Hand handForDealer = new Hand(
			List.of(new Card(Rank.JACK, Suit.CLOVER), new Card(Rank.ACE, Suit.CLOVER)));
		Dealer dealer = new Dealer(handForDealer);
		assertThat(player.getResult(dealer)).isEqualTo(WinOrLose.LOSE);
	}

}
