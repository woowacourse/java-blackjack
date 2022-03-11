package blackjack.controller;

import static blackjack.domain.MatchResult.*;
import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.MatchResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackControllerTest extends BlackJackController {

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
        Player player = createPlayerWithDenominations("User A", JACK, NINE);

        // when
        decideGameScore(dealer, List.of(player));
        Integer dealerMatchResultScore = dealer.getMatchResultScore(WIN);
        MatchResult playerMatchResult = player.getResult();

        // then
        assertThat(dealerMatchResultScore).isEqualTo(1);
        assertThat(playerMatchResult).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어의 점수가 딜러보다 높을 때 플레이어의 승이다")
    void player_win_when_player_score_high() {
        // given
        Dealer dealer = createDealerWithDenominations(JACK, NINE);
        Player player = createPlayerWithDenominations("user A", QUEEN, TEN);

        // when
        decideGameScore(dealer, List.of(player));
        Integer dealerMatchResultScore = dealer.getMatchResultScore(LOSE);
        MatchResult playerMatchResult = player.getResult();

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
        decideGameScore(dealer, List.of(player));
        Integer dealerMatchResultScore = dealer.getMatchResultScore(LOSE);
        MatchResult playerMatchResult = player.getResult();

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
        decideGameScore(dealer, List.of(player));
        EnumMap<MatchResult, Integer> dealerResultScores = dealer.getMatchResultScores();
        MatchResult playerMatchResult = player.getResult();

        // then
        assertAll(
                () -> assertThat(dealerResultScores.get(WIN)).isEqualTo(1),
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
        decideGameScore(dealer, List.of(player));
        Integer dealerMatchResultScore = dealer.getMatchResultScore(DRAW);
        MatchResult playerMatchResult = player.getResult();

        // then
        assertAll(
                () -> assertThat(dealerMatchResultScore).isEqualTo(1),
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
        decideGameScore(dealer, List.of(player));
        Integer dealerMatchResultCount = dealer.getMatchResultScores().get(MatchResult.DRAW);
        MatchResult playerMatchResult = player.getResult();

        // then
        assertAll(
                () -> assertThat(dealerMatchResultCount).isEqualTo(1),
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
        decideGameScore(dealer, List.of(playerA, playerB, playerC));

        // then
        assertAll(
                () -> assertThat(dealer.getMatchResultScore(WIN)).isEqualTo(2),
                () -> assertThat(dealer.getMatchResultScore(LOSE)).isEqualTo(1),

                () -> assertThat(playerA.getResult()).isEqualTo(LOSE),
                () -> assertThat(playerB.getResult()).isEqualTo(WIN),
                () -> assertThat(playerC.getResult()).isEqualTo(LOSE)
        );
    }
}
