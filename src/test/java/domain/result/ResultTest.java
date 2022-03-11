package domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;

public class ResultTest {

	Card card_A = new Card(Rank.RANK_A, Suit.CLOVER);
	Card card_2 = new Card(Rank.RANK_2, Suit.CLOVER);
	Card card_Q = new Card(Rank.RANK_Q, Suit.CLOVER);
	Card card_K = new Card(Rank.RANK_K, Suit.CLOVER);
	Card card_6 = new Card(Rank.RANK_6, Suit.CLOVER);
	Card card_9 = new Card(Rank.RANK_9, Suit.CLOVER);
	List<Card> cards_20 = new ArrayList<>(Arrays.asList(card_A, card_9));
	List<Card> cards_15 = new ArrayList<>(Arrays.asList(card_9, card_6));
	List<Card> cards_BURST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
	List<Card> cards_17 = new ArrayList<>(Arrays.asList(card_A, card_6));
	List<List<Card>> initCards = new ArrayList<>(Arrays.asList(cards_20, cards_15, cards_BURST, cards_17));
	List<Name> names = Arrays.asList(new Name("pobi"), new Name("jason"), new Name("woni"), new Name("gugu"));

	Players players = new Players(names, initCards);
	Dealer dealer_17 = new Dealer(List.of(card_A, card_6));
	Dealer dealer_BURST = new Dealer(List.of(card_K, card_Q, card_2));

	Result result = new Result(players.getResultAtFinal(dealer_17));
	Result result_dealer_burst = new Result(players.getResultAtFinal(dealer_BURST));

	@Test
	@DisplayName("딜러도 safe, 플레이어 safe, 플레이어 승")
	void getVersusOfPlayer_Pobi() {
		assertThat(result.getVersusOfPlayer(new Name("pobi")).getResult()).isEqualTo("승");
	}

	@Test
	@DisplayName("딜러도 safe, 플레이어 safe, 무승부")
	void getVersusOfPlayer_Gugu() {
		assertThat(result.getVersusOfPlayer(new Name("gugu")).getResult()).isEqualTo("무");
	}

	@Test
	@DisplayName("딜러도 safe, 플레이어 safe, 플레이어 패")
	void getVersusOfPlayer_Jason() {
		assertThat(result.getVersusOfPlayer(new Name("jason")).getResult()).isEqualTo("패");
	}

	@Test
	@DisplayName("딜러도 safe, 플레이어 burst, 플레이어 패")
	void getVersusOfPlayer_Woni() {
		assertThat(result.getVersusOfPlayer(new Name("woni")).getResult()).isEqualTo("패");
	}

	@Test
	@DisplayName("딜러도 burst, 플레이어 safe, 플레이어 승")
	void getVersusOfPlayer_Pobi2() {
		assertThat(result_dealer_burst.getVersusOfPlayer(new Name("pobi")).getResult()).isEqualTo("승");
	}

	@Test
	@DisplayName("딜러도 burst, 플레이어 burst, 플레이어 패")
	void getVersusOfPlayer_Woni2() {
		assertThat(result_dealer_burst.getVersusOfPlayer(new Name("woni")).getResult()).isEqualTo("패");
	}

	@Test
	@DisplayName("딜러 결과 확인")
	void getDealerResult() {
		assertThat(Arrays.asList(result.getDealerWinCount(), result.getDealerDrawCount(),
			result.getDealerLoseCount()))
			.isEqualTo(Arrays.asList(2, 1, 1));
	}
}
