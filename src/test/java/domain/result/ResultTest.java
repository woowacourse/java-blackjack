package domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
    Card card_4 = new Card(Rank.RANK_4, Suit.CLOVER);
    Card card_Q = new Card(Rank.RANK_Q, Suit.CLOVER);
    Card card_K = new Card(Rank.RANK_K, Suit.CLOVER);
    Card card_6 = new Card(Rank.RANK_6, Suit.CLOVER);
    Card card_9 = new Card(Rank.RANK_9, Suit.CLOVER);
    List<Card> cards_20 = new ArrayList<>(Arrays.asList(card_A, card_9));
    List<Card> cards_15 = new ArrayList<>(Arrays.asList(card_9, card_6));
    List<Card> cards_BURST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
    List<Card> cards_17 = new ArrayList<>(Arrays.asList(card_A, card_6));
    List<Card> cards_BLACKJACK = new ArrayList<>(Arrays.asList(card_A, card_Q));
    List<List<Card>> initCards = new ArrayList<>(
            Arrays.asList(cards_20, cards_15, cards_BURST, cards_17, cards_BLACKJACK)
    );
    List<Name> names = Arrays.asList(
            new Name("pobi"), new Name("jason"), new Name("woni"), new Name("gugu"), new Name("jojo")
    );

    Players players;
    Dealer dealer_17;
    Dealer dealer_BURST;
    Result result_dealer_17;
    Result result_dealer_burst;

    @BeforeEach
    void setUp() {
        players = new Players(names, initCards);
        dealer_17 = new Dealer(List.of(card_A, card_6));
        dealer_BURST = new Dealer(List.of(card_K, card_Q, card_2));
        result_dealer_17 = new Result(players.getResultAtFinal(dealer_17));
        result_dealer_burst = new Result(players.getResultAtFinal(dealer_BURST));
    }

    @Test
    @DisplayName("딜러도 safe, 플레이어 safe, 플레이어 승")
    void getVersusOfPlayer_Pobi() {
        assertThat(result_dealer_17.getVersusOfPlayer(new Name("pobi")).getResult()).isEqualTo("승");
    }

    @Test
    @DisplayName("딜러도 safe, 플레이어 safe, 무승부")
    void getVersusOfPlayer_Gugu() {
        assertThat(result_dealer_17.getVersusOfPlayer(new Name("gugu")).getResult()).isEqualTo("무");
    }

    @Test
    @DisplayName("딜러도 safe, 플레이어 safe, 플레이어 패")
    void getVersusOfPlayer_Jason() {
        assertThat(result_dealer_17.getVersusOfPlayer(new Name("jason")).getResult()).isEqualTo("패");
    }

    @Test
    @DisplayName("딜러도 safe, 플레이어 burst, 플레이어 패")
    void getVersusOfPlayer_Woni() {
        assertThat(result_dealer_17.getVersusOfPlayer(new Name("woni")).getResult()).isEqualTo("패");
    }

    @Test
    @DisplayName("딜러도 bust, 플레이어 safe, 플레이어 승")
    void getVersusOfPlayer_bust_safe_win() {
        assertThat(result_dealer_burst.getVersusOfPlayer(new Name("pobi")).getResult()).isEqualTo("승");
    }

    @Test
    @DisplayName("딜러도 bust, 플레이어 burst, 플레이어 패")
    void getVersusOfPlayer_bust_bust_lose() {
        assertThat(result_dealer_burst.getVersusOfPlayer(new Name("woni")).getResult()).isEqualTo("패");
    }

    @Test
    @DisplayName("딜러가 21인데 플레이어가 블랙잭일 경우, 플레이어 승리")
    void getVersusOfPlayer_21_blackjack_win() {
        dealer_17.addCard(card_4);
        Result result = new Result(players.getResultAtFinal(dealer_17));
        assertThat(result.getVersusOfPlayer(new Name("jojo")).getResult()).isEqualTo("승");
    }

    @Test
    @DisplayName("딜러가 21인데 플레이어가 21일 경우, 무승부")
    void getVersusOfPlayer_21_21_draw() {
        dealer_17.addCard(card_4);
        players.addCardByName(new Name("jason"), card_6);
        Result result = new Result(players.getResultAtFinal(dealer_17));
        assertThat(result.getVersusOfPlayer(new Name("jason")).getResult()).isEqualTo("무");
    }

    @Test
    @DisplayName("딜러 결과 확인")
    void getDealerResult() {
        assertThat(Arrays.asList(
                result_dealer_17.getDealerWinCount(),
                result_dealer_17.getDealerDrawCount(),
                result_dealer_17.getDealerLoseCount())
        ).isEqualTo(Arrays.asList(2, 1, 2));
    }
}
