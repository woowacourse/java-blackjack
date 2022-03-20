package domain.result;

import static domain.card.Cards.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;

public class ResultTest {
	private List<Card> cards_20;
	private List<Card> cards_15;
	private List<Card> cards_BURST;
	private List<Card> cards_17;

	@BeforeEach
	void setUp() {
		cards_20 = new ArrayList<>(Arrays.asList(ACE_CLOVER, NINE_CLOVER));
		cards_15 = new ArrayList<>(Arrays.asList(NINE_CLOVER, SIX_CLOVER));
		cards_BURST = new ArrayList<>(Arrays.asList(KING_CLOVER, QUEEN_CLOVER, TWO_CLOVER));
		cards_17 = new ArrayList<>(Arrays.asList(ACE_CLOVER, SIX_CLOVER));
	}

	@Test
	@DisplayName("딜러의 최종 수익 확인")
	void getDealerMoney() {
		Participant pobi = new Participant(new Name("pobi"), new Hand(cards_20), new Betting(10000));
		Participant jason = new Participant(new Name("jason"), new Hand(cards_15), new Betting(20000));
		Participant woni = new Participant(new Name("woni"), new Hand(cards_BURST), new Betting(30000));
		Participant gugu = new Participant(new Name("gugu"), new Hand(cards_17), new Betting(40000));
		Dealer dealer = new Dealer(new Hand(cards_17));
		Players players = new Players(Arrays.asList(pobi, jason, woni, gugu));
		Result result = Result.of(dealer, players);
		assertThat(result.getDealerMoney()).isEqualTo(40000);
	}

	@Test
	@DisplayName("딜러의 최종 수익 확인 - 딜러가 Bust일 경우")
	void getDealerMoneyAtDealerBurst() {
		Participant pobi = new Participant(new Name("pobi"), new Hand(cards_20), new Betting(10000));
		Participant jason = new Participant(new Name("jason"), new Hand(cards_15), new Betting(20000));
		Participant woni = new Participant(new Name("woni"), new Hand(cards_BURST), new Betting(30000));
		Participant gugu = new Participant(new Name("gugu"), new Hand(cards_17), new Betting(40000));
		Dealer dealer = new Dealer(new Hand(cards_BURST));
		Players players = new Players(Arrays.asList(pobi, jason, woni, gugu));
		Result result = Result.of(dealer, players);
		assertThat(result.getDealerMoney()).isEqualTo(-40000);
	}

	@Test
	@DisplayName("플레이어의 최종 수익 확인")
	void getPlayerMoney() {
		Participant pobi = new Participant(new Name("pobi"), new Hand(cards_20), new Betting(10000));
		Dealer dealer = new Dealer(new Hand(cards_BURST));
		Players players = new Players(Arrays.asList(pobi));
		Result result = Result.of(dealer, players);
		assertThat(result.getPlayerMoney(pobi)).isEqualTo(10000);
	}

	@Test
	@DisplayName("플레이어의 최종 수익 확인_플레이어가 블랙잭일 때")
	void getPlayerMoneyAtPlayerBlackJack() {
		Hand hand = new Hand(List.of(ACE_CLOVER, QUEEN_CLOVER));
		Participant pobi = new Participant(new Name("pobi"), hand, new Betting(10000));
		Dealer dealer = new Dealer(new Hand(cards_17));
		Players players = new Players(Arrays.asList(pobi));
		Result result = Result.of(dealer, players);
		assertThat(result.getPlayerMoney(pobi)).isEqualTo(15000);
	}
}
