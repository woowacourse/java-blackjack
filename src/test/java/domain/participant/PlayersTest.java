package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import domain.result.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.result.Versus;

public class PlayersTest {

    Card card_A = new Card(Rank.RANK_A, Suit.CLOVER);
    Card card_2 = new Card(Rank.RANK_2, Suit.CLOVER);
    Card card_Q = new Card(Rank.RANK_Q, Suit.CLOVER);
    Card card_K = new Card(Rank.RANK_K, Suit.CLOVER);
    Card card_6 = new Card(Rank.RANK_6, Suit.CLOVER);
    List<Card> cards_BLACKJACK = new ArrayList<>(Arrays.asList(card_A, card_Q));
    List<Card> cards_BUST = new ArrayList<>(Arrays.asList(card_K, card_Q, card_2));
    Dealer dealer_17 = new Dealer(List.of(card_A, card_6));
    Dealer dealer_BLACKJACK = new Dealer(List.of(card_A, card_Q));

    Player player_BLACKJACK;
    Player player_BUST;
    Players players;

    @BeforeEach
    void setUp() {
        player_BLACKJACK = new Player(new Name("player_BLACKJACK"), cards_BLACKJACK);
        player_BUST = new Player(new Name("player_BUST"), cards_BUST);
        players = new Players(List.of(player_BLACKJACK, player_BUST));
    }

    @Test
    @DisplayName("이름으로 플레이어 손패 반환")
    void showHandByName() {
        Name name = new Name("player_BLACKJACK");
        assertThat(players.showHandByName(name)).isEqualTo("A♣️, Q♣️");
    }

    @Test
    @DisplayName("이름으로 플레이어 카드 추가")
    void addCardByName() {
        Name name = new Name("player_BLACKJACK");
        players.addCardByName(name, new Card(Rank.RANK_A, Suit.DIAMOND));
        assertThat(players.showHandByName(name)).isEqualTo("A♣️, Q♣️, A♦️");
    }

    @Test
    @DisplayName("이름으로 플레이어가 Bust 인지 판별")
    void isBustByName() {
        Name name = new Name("player_BUST");
        assertThat(players.isBustByName(name)).isTrue();
    }

    @Test
    @DisplayName("모든 플레이어가 Bust 인지 판별")
    void isBustAllBust() {
        assertThat(players.isNotAllBust()).isTrue();
    }

    @Test
    @DisplayName("이름으로 플레이어가 블랙잭 인지 판별")
    void isBlackJackByName() {
        Name name = new Name("player_BLACKJACK");
        assertThat(players.isUpperBoundScoreByName(name)).isTrue();
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우 결과 반환")
    void getResultAtDealerBlackJack() {
        Result resultMap = Result.generateResultAtDealerBlackJack(dealer_BLACKJACK, players);
        assertThat(resultMap.getVersusOfPlayer(new Name("player_BLACKJACK"))).isEqualTo(Versus.DRAW);
        assertThat(resultMap.getVersusOfPlayer(new Name("player_BUST"))).isEqualTo(Versus.LOSE);
    }

    @Test
    @DisplayName("최종 게임 결과 반환")
    void getResultAtFinal() {
        Result resultMap = Result.generateResultAtFinal(dealer_17, players);
        assertThat(resultMap.getVersusOfPlayer(new Name("player_BLACKJACK"))).isEqualTo(Versus.WIN);
        assertThat(resultMap.getVersusOfPlayer(new Name("player_BUST"))).isEqualTo(Versus.LOSE);
    }
}
