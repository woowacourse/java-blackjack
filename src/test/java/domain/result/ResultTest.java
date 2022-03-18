package domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;

public class ResultTest {

	private Card card_A;
	private Card card_2;
	private Card card_Q;
	private Card card_K;
	private Card card_6;
	private Card card_9;
	private List<Card> cards_20;
	private List<Card> cards_15;
	private List<Card> cards_BURST;
	private List<Card> cards_17;
	private Players players;
	private Dealer dealer_17;
	private Dealer dealer_BURST;
	private Player pobi;
	private Player jason;
	private Player woni;
	private Player gugu;
	private Result result;
	private Result result_dealer_burst;

	@BeforeEach
	void setUp() {
		card_A = new Card(Denomination.ACE, Suit.CLOVER);
		card_2 = new Card(Denomination.TWO, Suit.CLOVER);
		card_Q = new Card(Denomination.QUEEN, Suit.CLOVER);
		card_K = new Card(Denomination.KING, Suit.CLOVER);
		card_6 = new Card(Denomination.SIX, Suit.CLOVER);
		card_9 = new Card(Denomination.NINE, Suit.CLOVER);
		cards_20 = new ArrayList<>(Arrays.asList(card_A, card_9));
		cards_15 = new ArrayList<>(Arrays.asList(card_9, card_6));
		cards_BURST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
		cards_17 = new ArrayList<>(Arrays.asList(card_A, card_6));
		pobi = new Player(new Name("pobi"), new Hand(cards_20), new Betting(10000));
		jason = new Player(new Name("jason"), new Hand(cards_15), new Betting(20000));
		woni = new Player(new Name("woni"), new Hand(cards_BURST), new Betting(30000));
		gugu = new Player(new Name("gugu"), new Hand(cards_17), new Betting(40000));
		players = new Players(Arrays.asList(pobi, jason, woni, gugu));
		dealer_17 = new Dealer(new Hand(List.of(card_A, card_6)));
		dealer_BURST = new Dealer(new Hand(List.of(card_K, card_Q, card_2)));
		result = new Result(players.getResult(dealer_17));
		result_dealer_burst = new Result(players.getResult(dealer_BURST));
	}

	@Test
	@DisplayName("딜러의 최종 수익 확인")
	void getDealerMoney() {
		assertThat(result.getDealerMoney()).isEqualTo(40000);
	}

	@Test
	@DisplayName("딜러의 최종 수익 확인")
	void getDealerMoneyAtDealerBurst() {
		assertThat(result_dealer_burst.getDealerMoney()).isEqualTo(-40000);
	}
}
