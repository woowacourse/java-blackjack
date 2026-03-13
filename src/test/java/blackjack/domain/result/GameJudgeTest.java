package blackjack.domain.result;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameJudgeTest {
    private List<Card> blackjackCards;
    private List<Card> bustCards;
    private List<Card> lowCards;
    private List<Card> highCards;
    private GameJudge gameJudge;

    @BeforeEach
    void setup() {
        gameJudge = new GameJudge();
        blackjackCards = List.of(new Card(Suit.CLOVER, Rank.ACE), new Card(Suit.CLOVER, Rank.JACK));
        bustCards = List.of(new Card(Suit.CLOVER, Rank.KING), new Card(Suit.CLOVER, Rank.JACK),
                new Card(Suit.CLOVER, Rank.TWO));
        lowCards = List.of(new Card(Suit.CLOVER, Rank.JACK), new Card(Suit.CLOVER, Rank.KING));
        highCards = List.of(new Card(Suit.CLOVER, Rank.JACK), new Card(Suit.CLOVER, Rank.TEN),
                new Card(Suit.HEART, Rank.ACE));
    }


    private Player createPlayer(List<Card> cards) {
        Player player = new Player("pobi", 1000);
        for (Card card : cards) {
            player.addCard(card);
        }
        return player;
    }

    private Dealer createDealer(List<Card> cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.addCard(card);
        }
        return dealer;
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 플레이어 블랙잭 승리")
    void test_player_win_when_player_is_blackjack() {

        assertThat(gameJudge.judge(createPlayer(blackjackCards), createDealer(highCards))).isEqualTo(
                GameOutcome.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부")
    void test_draw_when_both_are_blackjack() {

        assertThat(gameJudge.judge(createPlayer(blackjackCards), createDealer(blackjackCards))).isEqualTo(
                GameOutcome.DRAW);
    }

    @Test
    @DisplayName("딜러만 블랙잭이면 플레이어 패배")
    void test_player_lose_when_dealer_is_blackjack() {

        assertThat(gameJudge.judge(createPlayer(highCards), createDealer(blackjackCards))).isEqualTo(
                GameOutcome.LOSE);
    }


    @Test
    @DisplayName("플레이어가 버스트면 플레이어 패배")
    void test_player_lose_when_player_is_bust() {

        assertThat(gameJudge.judge(createPlayer(bustCards), createDealer(highCards))).isEqualTo(GameOutcome.LOSE);
    }

    @Test
    @DisplayName("딜러만 버스트면 플레이어 승리")
    void test_player_win_when_dealer_is_bust() {

        assertThat(gameJudge.judge(createPlayer(highCards), createDealer(bustCards))).isEqualTo(GameOutcome.WIN);
    }

    @Test
    @DisplayName("블랙잭과 버스트 경우 외에 점수가 더 높으면 승리")
    void test_win_when_player_score_is_higher() {

        assertThat(gameJudge.judge(createPlayer(highCards), createDealer(lowCards))).isEqualTo(GameOutcome.WIN);
    }


    @Test
    @DisplayName("블랙잭과 버스트 경우 외에 점수가 같으면 무승부")
    void test_draw_when_scores_are_equal() {

        assertThat(gameJudge.judge(createPlayer(highCards), createDealer(highCards))).isEqualTo(GameOutcome.DRAW);
    }

}
