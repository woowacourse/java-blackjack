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
import domain.card.Rank;
import domain.card.Suit;
import domain.card.deckstrategy.GenerationDeckStrategy;
import domain.result.WinOrLose;

public class PlayersTest {

	Card card_A = new Card(Rank.RANK_ACE, Suit.CLOVER);
	Card card_2 = new Card(Rank.RANK_TWO, Suit.CLOVER);
	Card card_Q = new Card(Rank.RANK_QUEEN, Suit.CLOVER);
	Card card_K = new Card(Rank.RANK_KNIGHT, Suit.CLOVER);
	Card card_6 = new Card(Rank.RANK_SIX, Suit.CLOVER);
	List<Card> cards_21 = new ArrayList<>(Arrays.asList(card_A, card_Q));
	List<Card> cards_BURST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
	Dealer dealerBlackJack = new Dealer(cards_21);

	Players players;
	Dealer dealer_17;

	@BeforeEach
	void setUp() {
		players = new Players(
			Arrays.asList(new Player(new Name("pobi"), cards_21), new Player(new Name("jason"), cards_BURST)));
		dealer_17 = new Dealer(List.of(card_A, card_6));
	}

	@Test
	@DisplayName("이름으로 플레이어 손패 반환")
	void showHandByName() {
		Name name = new Name("pobi");
		assertThat(players.getCardsByName(name)).isEqualTo(Arrays.asList("A클로버", "Q클로버"));
	}
	
	@Test
	@DisplayName("이름으로 플레이어 카드 추가")
	void addCardByName() {
		Name name = new Name("pobi");
		players.addCardByName(name, new Card(Rank.RANK_ACE, Suit.DIAMOND));
		assertThat(players.getCardsByName(name)).isEqualTo(Arrays.asList("A클로버", "Q클로버", "A다이아몬드"));
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
		Map<Name, WinOrLose> resultMap = players.getResultAtBlackJack(dealerBlackJack);
		assertThat(resultMap.get(new Name("pobi"))).isEqualTo(WinOrLose.DRAW);
		assertThat(resultMap.get(new Name("jason"))).isEqualTo(WinOrLose.LOSE);
	}

	@Test
	@DisplayName("최종 게임 결과 반환")
	void getResultAtFinal() {
		Map<Name, WinOrLose> resultMap = players.getResultAtFinal(dealer_17);
		assertThat(resultMap.get(new Name("pobi"))).isEqualTo(WinOrLose.WIN);
		assertThat(resultMap.get(new Name("jason"))).isEqualTo(WinOrLose.LOSE);
	}

	@Test
	@DisplayName("초기 카드에 한명이 블랙잭이고 딜러가 나중에 블랙잭인 경우")
	void getResultAtFinal_PlayerBlackJack() {
		class TestGenerationDeckStrategy implements GenerationDeckStrategy {

			@Override
			public Queue<Card> generateCardsForBlackJack() {
				return new LinkedList<>(Arrays.asList(new Card(Rank.RANK_FOUR, Suit.CLOVER)));
			}
		}
		Deck deck = Deck.from(new TestGenerationDeckStrategy());
		dealer_17.addCard(deck.draw());
		Map<Name, WinOrLose> resultMap = players.getResultAtFinal(dealer_17);
		assertThat(resultMap.get(new Name("pobi"))).isEqualTo(WinOrLose.WIN);
		assertThat(resultMap.get(new Name("jason"))).isEqualTo(WinOrLose.LOSE);
	}
}
