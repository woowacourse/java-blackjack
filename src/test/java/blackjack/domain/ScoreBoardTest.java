package blackjack.domain;

import static blackjack.domain.MatchResult.DRAW;
import static blackjack.domain.MatchResult.LOSE;
import static blackjack.domain.MatchResult.WIN;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {

    private Dealer createDealerWithDenominations(Denomination... denominations) {
        Dealer dealer = new Dealer();
        for (Denomination denomination : denominations) {
            dealer.receiveCard(new Card(denomination, HEART));
        }
        return dealer;
    }

    private Player createPlayerWithDenominations(String playerName, Denomination... denominations) {
        Player player = new Player(playerName);
        for (Denomination denomination : denominations) {
            player.receiveCard(new Card(denomination, CLOVER));
        }
        return player;
    }

    @Test
    @DisplayName("딜러의 점수가 플레이어보다 높을 때 딜러의 승이다")
    void dealer_win_when_dealer_score_high() {
        // given
        Dealer dealer = createDealerWithDenominations(QUEEN, TEN);
        Player player = createPlayerWithDenominations("user A", JACK, NINE);

        // when
        ScoreBoard scoreBoard = new ScoreBoard(dealer, List.of(player));
        int dealerMatchResultScore = scoreBoard.findDealerMatchScore(WIN);
        MatchResult playerMatchResult = scoreBoard.getPlayersMatchResult().get(player);

        // then
        assertThat(dealerMatchResultScore).isEqualTo(1);
        assertThat(playerMatchResult).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 높을 때 플레이어의 승이다")
    void player_win_when_player_score_high() {
        // given
        Dealer dealer = createDealerWithDenominations(JACK, NINE);
        Player player = createPlayerWithDenominations("user A", QUEEN, TEN);

        // when
        ScoreBoard scoreBoard = new ScoreBoard(dealer, List.of(player));
        int dealerMatchResultScore = scoreBoard.findDealerMatchScore(LOSE);
        MatchResult playerMatchResult = scoreBoard.getPlayersMatchResult().get(player);

        // then
        assertThat(dealerMatchResultScore).isEqualTo(1);
        assertThat(playerMatchResult).isEqualTo(WIN);
    }

    @Test
    @DisplayName("딜러가 버스트이고 플레이어는 아닐 때 플레이어의 승이다")
    void player_win_when_dealer_bust_player_not_bust() {
        // given
        Dealer dealer = createDealerWithDenominations(QUEEN, TEN, TWO);
        Player player = createPlayerWithDenominations("user a", JACK, NINE);

        // when
        ScoreBoard scoreBoard = new ScoreBoard(dealer, List.of(player));
        int dealerMatchResultScore = scoreBoard.findDealerMatchScore(LOSE);
        MatchResult playerMatchResult = scoreBoard.getPlayersMatchResult().get(player);

        // then
        assertAll(
                () -> assertThat(dealerMatchResultScore).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(WIN)
        );
    }

    @Test
    @DisplayName("플레이어가 버스트이고 딜러는 아닐 때 딜러의 승이다")
    void dealer_win_when_player_bust_dealer_not_bust() {
        // given
        Dealer dealer = createDealerWithDenominations(JACK, NINE);
        Player player = createPlayerWithDenominations("user b", QUEEN, TEN, TWO);

        // when
        ScoreBoard scoreBoard = new ScoreBoard(dealer, List.of(player));
        int dealerMatchScore = scoreBoard.findDealerMatchScore(WIN);
        MatchResult playerMatchResult = player.getResult();

        // then
        assertAll(
                () -> assertThat(dealerMatchScore).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(MatchResult.LOSE)
        );
    }

    @Test
    @DisplayName("플레이어와 딜러 동점일 때 무승부이다")
    void player_and_dealer_same_score_is_draw() {
        // given
        Dealer dealer = createDealerWithDenominations(FIVE, SEVEN);
        Player player = createPlayerWithDenominations("user a", JACK, TWO);

        // when
        ScoreBoard scoreBoard = new ScoreBoard(dealer, List.of(player));
        int dealerMatchScore = scoreBoard.findDealerMatchScore(DRAW);
        MatchResult playerMatchResult = scoreBoard.getPlayersMatchResult().get(player);

        // then
        assertAll(
                () -> assertThat(dealerMatchScore).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(MatchResult.DRAW)
        );
    }

    @Test
    @DisplayName("플레이어와 딜러 둘 다 버스트라일 때 무승부이다")
    void player_and_dealer_all_bust() {
        // given
        Dealer dealer = createDealerWithDenominations(FIVE, SEVEN, NINE, TWO);
        Player player = createPlayerWithDenominations("user a", JACK, TWO, QUEEN);

        // when
        ScoreBoard scoreBoard = new ScoreBoard(dealer, List.of(player));
        int dealerMatchScore = scoreBoard.findDealerMatchScore(DRAW);
        MatchResult playerMatchResult = scoreBoard.getPlayersMatchResult().get(player);

        // then
        assertAll(
                () -> assertThat(dealerMatchScore).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(DRAW)
        );
    }

    @Test
    @DisplayName("플레이어 한명이 승이고 다른 두 명이 패이면 딜러는 2승 1패이다")
    void player_win_other_lose_dealer_1_win_1_lose() {
        // given
        Dealer dealer = createDealerWithDenominations(THREE, SEVEN); // 10
        Player playerA = createPlayerWithDenominations("user a", TWO, FOUR); // 6
        Player playerB = createPlayerWithDenominations("user b", FIVE, EIGHT); // 13
        Player playerC = createPlayerWithDenominations("user c", THREE, SIX); // 9

        // when
        ScoreBoard scoreBoard = new ScoreBoard(dealer, List.of(playerA, playerB, playerC));
        Map<Player, MatchResult> playersMatchResult = scoreBoard.getPlayersMatchResult();

        // then
        assertAll(
                () -> assertThat(dealer.getMatchResultScore(WIN)).isEqualTo(2),
                () -> assertThat(dealer.getMatchResultScore(LOSE)).isEqualTo(1),

                () -> assertThat(playersMatchResult.get(playerA)).isEqualTo(LOSE),
                () -> assertThat(playersMatchResult.get(playerB)).isEqualTo(WIN),
                () -> assertThat(playersMatchResult.get(playerC)).isEqualTo(LOSE)
        );
    }

}
