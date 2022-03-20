package domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import domain.participant.Player;
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

public class ResultsTest {

    Card card_A = new Card(Rank.RANK_A, Suit.CLOVER);
    Card card_2 = new Card(Rank.RANK_2, Suit.CLOVER);
    Card card_4 = new Card(Rank.RANK_4, Suit.CLOVER);
    Card card_Q = new Card(Rank.RANK_Q, Suit.CLOVER);
    Card card_K = new Card(Rank.RANK_K, Suit.CLOVER);
    Card card_6 = new Card(Rank.RANK_6, Suit.CLOVER);
    Card card_9 = new Card(Rank.RANK_9, Suit.CLOVER);
    List<Card> cards_20 = new ArrayList<>(Arrays.asList(card_A, card_9));
    List<Card> cards_15 = new ArrayList<>(Arrays.asList(card_9, card_6));
    List<Card> cards_BUST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
    List<Card> cards_17 = new ArrayList<>(Arrays.asList(card_A, card_6));
    List<Card> cards_BLACKJACK = new ArrayList<>(Arrays.asList(card_A, card_Q));

    Player player_20;
    Player player_15;
    Player player_BUST;
    Player player_17;
    Player player_BLACKJACK;
    Players players;

    Dealer dealer_17;
    Dealer dealer_BUST;

    Results result_dealer_17;
    Results result_dealer_bust;

    @BeforeEach
    void setUp() {
        player_20 = new Player(new Name("player_20"), cards_20);
        player_15 = new Player(new Name("player_15"), cards_15);
        player_BUST = new Player(new Name("player_BUST"), cards_BUST);
        player_17 = new Player(new Name("player_17"), cards_17);
        player_BLACKJACK = new Player(new Name("player_BLACKJACK"), cards_BLACKJACK);
        players = new Players(List.of(player_20, player_15, player_BUST, player_17, player_BLACKJACK));

        dealer_17 = new Dealer(cards_17);
        dealer_BUST = new Dealer(cards_BUST);

        result_dealer_17 = Results.generateResultAtFinal(dealer_17, players);
        result_dealer_bust = Results.generateResultAtFinal(dealer_BUST, players);
    }


    @Test
    @DisplayName("딜러도 safe, 플레이어 safe, 플레이어 승")
    void getVersusOfPlayer_player_20() {
        assertThat(result_dealer_17.getVersusOfPlayer(new Name("player_20")).getResult()).isEqualTo("승");
    }

    @Test
    @DisplayName("딜러도 safe, 플레이어 safe, 무승부")
    void getVersusOfPlayer_player_17() {
        assertThat(result_dealer_17.getVersusOfPlayer(new Name("player_17")).getResult()).isEqualTo("무");
    }

    @Test
    @DisplayName("딜러도 safe, 플레이어 safe, 플레이어 패")
    void getVersusOfPlayer_player_15() {
        assertThat(result_dealer_17.getVersusOfPlayer(new Name("player_15")).getResult()).isEqualTo("패");
    }

    @Test
    @DisplayName("딜러도 safe, 플레이어 burst, 플레이어 패")
    void getVersusOfPlayer_player_BUST() {
        assertThat(result_dealer_17.getVersusOfPlayer(new Name("player_BUST")).getResult()).isEqualTo("패");
    }

    @Test
    @DisplayName("딜러도 bust, 플레이어 safe, 플레이어 승")
    void getVersusOfPlayer_bust_safe_win() {
        assertThat(result_dealer_bust.getVersusOfPlayer(new Name("player_20")).getResult()).isEqualTo("승");
    }

    @Test
    @DisplayName("딜러도 bust, 플레이어 bust, 플레이어 패")
    void getVersusOfPlayer_bust_bust_lose() {
        assertThat(result_dealer_bust.getVersusOfPlayer(new Name("player_BUST")).getResult()).isEqualTo("패");
    }

    @Test
    @DisplayName("딜러가 21인데 플레이어가 블랙잭일 경우, 플레이어 승리")
    void getVersusOfPlayer_21_blackjack_win() {
        dealer_17.addCard(card_4);
        Results result = Results.generateResultAtFinal(dealer_17, players);
        assertThat(result.getVersusOfPlayer(new Name("player_BLACKJACK")).getResult()).isEqualTo("승");
    }

    @Test
    @DisplayName("딜러가 21인데 플레이어가 21일 경우, 무승부")
    void getVersusOfPlayer_21_21_draw() {
        dealer_17.addCard(card_4);
        players.addCardByName(new Name("player_15"), card_6);
        Results result = Results.generateResultAtFinal(dealer_17, players);
        assertThat(result.getVersusOfPlayer(new Name("player_15")).getResult()).isEqualTo("무");
    }

    @Test
    @DisplayName("딜러 결과 확인")
    void getDealerResult() {
        assertThat(Arrays.asList(
                result_dealer_17.countDealerWin(),
                result_dealer_17.countDealerDraw(),
                result_dealer_17.countDealerLose())
        ).isEqualTo(Arrays.asList(2, 1, 2));
    }
}
