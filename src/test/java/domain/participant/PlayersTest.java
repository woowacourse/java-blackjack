package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import domain.card.deckstrategy.GenerationDeckStrategy;
import domain.result.WinOrLose;

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
		pobi = new Player(new Name("pobi"), new Hand(cards_21), 0);
		jason = new Player(new Name("jason"), new Hand(cards_BURST), 0);
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

	@Test
	@DisplayName("딜러가 블랙잭일 경우 결과 반환")
	void getResultAtBlackJack() {
		Map<Name, WinOrLose> resultMap = players.getResult(dealerBlackJack);
		assertThat(resultMap.get(new Name("pobi"))).isEqualTo(WinOrLose.DRAW);
		assertThat(resultMap.get(new Name("jason"))).isEqualTo(WinOrLose.LOSE);
	}

	@Test
	@DisplayName("최종 게임 결과 반환")
	void getResultAtFinal() {
		Map<Name, WinOrLose> resultMap = players.getResult(dealer_17);
		assertThat(resultMap.get(new Name("pobi"))).isEqualTo(WinOrLose.WIN);
		assertThat(resultMap.get(new Name("jason"))).isEqualTo(WinOrLose.LOSE);
	}

	@Test
	@DisplayName("초기 카드에 한명이 블랙잭이고 딜러가 나중에 블랙잭인 경우")
	void getResultAtFinal_PlayerBlackJack() {
		class TestGenerationDeckStrategy implements GenerationDeckStrategy {

			@Override
			public Queue<Card> generateCardsForBlackJack() {
				return new LinkedList<>(Arrays.asList(new Card(Rank.FOUR, Suit.CLOVER)));
			}
		}
		Deck deck = Deck.from(new TestGenerationDeckStrategy());
		dealer_17.addCard(deck.draw());
		Map<Name, WinOrLose> resultMap = players.getResult(dealer_17);
		assertThat(resultMap.get(new Name("pobi"))).isEqualTo(WinOrLose.WIN);
		assertThat(resultMap.get(new Name("jason"))).isEqualTo(WinOrLose.LOSE);
	}
}
