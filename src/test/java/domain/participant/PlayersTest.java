package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;

public class PlayersTest {

	private Card card_A;
	private Card card_2;
	private Card card_Q;
	private Card card_K;
	private Card card_6;
	private List<Card> cards_21;
	private List<Card> cards_BURST;
	private Dealer dealerBlackJack;
	private Dealer dealer_17;
	private Player pobi;
	private Player jason;
	private Players players;

	@BeforeEach
	void setUp() {
		card_A = new Card(Rank.ACE, Suit.CLOVER);
		card_2 = new Card(Rank.TWO, Suit.CLOVER);
		card_Q = new Card(Rank.QUEEN, Suit.CLOVER);
		card_K = new Card(Rank.KNIGHT, Suit.CLOVER);
		card_6 = new Card(Rank.SIX, Suit.CLOVER);
		cards_21 = new ArrayList<>(Arrays.asList(card_A, card_Q));
		cards_BURST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
		dealerBlackJack = new Dealer(new Hand(cards_21));
		pobi = new Player(new Name("pobi"), new Hand(cards_21), new Betting(0));
		jason = new Player(new Name("jason"), new Hand(cards_BURST), new Betting(0));
		players = new Players(
			Arrays.asList(pobi, jason));
		List<Card> cards = new ArrayList<>(List.of(card_A, card_6));
		dealer_17 = new Dealer(new Hand(cards));
	}

	@Test
	@DisplayName("이름으로 플레이어가 Bust 인지 판별")
	void isBustByName() {
		Name name = new Name("jason");
		assertThat(players.checkBustByName(name)).isTrue();
	}

	@Test
	@DisplayName("모든 플레이어가 Bust 인지 판별")
	void isBustAllBust() {
		assertThat(players.checkAllBust()).isFalse();
	}

	@Test
	@DisplayName("이름으로 플레이어가 블랙잭 인지 판별")
	void isBlackJackByName() {
		Name name = new Name("pobi");
		assertThat(players.checkMaxScoreByName(name)).isTrue();
	}
}
