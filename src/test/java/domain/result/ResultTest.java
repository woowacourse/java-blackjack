package domain.result;

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
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;

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
	private Result result;
	private Result result_dealer_burst;

	@BeforeEach
	void setUp() {
		card_A = new Card(Rank.ACE, Suit.CLOVER);
		card_2 = new Card(Rank.TWO, Suit.CLOVER);
		card_Q = new Card(Rank.QUEEN, Suit.CLOVER);
		card_K = new Card(Rank.KNIGHT, Suit.CLOVER);
		card_6 = new Card(Rank.SIX, Suit.CLOVER);
		card_9 = new Card(Rank.NINE, Suit.CLOVER);
		cards_20 = new ArrayList<>(Arrays.asList(card_A, card_9));
		cards_15 = new ArrayList<>(Arrays.asList(card_9, card_6));
		cards_BURST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
		cards_17 = new ArrayList<>(Arrays.asList(card_A, card_6));
		players = new Players(
			Arrays.asList(new Player(new Name("pobi"), new Hand(cards_20)),
				new Player(new Name("jason"), new Hand(cards_15)),
				new Player(new Name("woni"), new Hand(cards_BURST)), new Player(new Name("gugu"), new Hand(cards_17))));
		dealer_17 = new Dealer(new Hand(List.of(card_A, card_6)));
		dealer_BURST = new Dealer(new Hand(List.of(card_K, card_Q, card_2)));
		result = new Result(players.getResultAtFinal(dealer_17));
		result_dealer_burst = new Result(players.getResultAtFinal(dealer_BURST));
	}

	@Test
	@DisplayName("딜러도 safe, 플레이어 safe, 플레이어 승")
	void getVersusOfPlayer_Pobi() {
		assertThat(result.getResultOfPlayer(new Name("pobi")).getResult()).isEqualTo("승");
	}

	@Test
	@DisplayName("딜러도 safe, 플레이어 safe, 무승부")
	void getVersusOfPlayer_Gugu() {
		assertThat(result.getResultOfPlayer(new Name("gugu")).getResult()).isEqualTo("무");
	}

	@Test
	@DisplayName("딜러도 safe, 플레이어 safe, 플레이어 패")
	void getVersusOfPlayer_Jason() {
		assertThat(result.getResultOfPlayer(new Name("jason")).getResult()).isEqualTo("패");
	}

	@Test
	@DisplayName("딜러도 safe, 플레이어 burst, 플레이어 패")
	void getVersusOfPlayer_Woni() {
		assertThat(result.getResultOfPlayer(new Name("woni")).getResult()).isEqualTo("패");
	}

	@Test
	@DisplayName("딜러도 burst, 플레이어 safe, 플레이어 승")
	void getVersusOfPlayer_Pobi2() {
		assertThat(result_dealer_burst.getResultOfPlayer(new Name("pobi")).getResult()).isEqualTo("승");
	}

	@Test
	@DisplayName("딜러도 burst, 플레이어 burst, 플레이어 패")
	void getVersusOfPlayer_Woni2() {
		assertThat(result_dealer_burst.getResultOfPlayer(new Name("woni")).getResult()).isEqualTo("패");
	}

	@Test
	@DisplayName("딜러 결과 확인")
	void getDealerResult() {
		assertThat(Arrays.asList(result.getDealerWinCount(), result.getDealerDrawCount(),
			result.getDealerLoseCount()))
			.isEqualTo(Arrays.asList(2, 1, 1));
	}
}
