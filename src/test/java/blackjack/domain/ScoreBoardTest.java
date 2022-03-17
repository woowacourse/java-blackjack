package blackjack.domain;

import static blackjack.constant.MatchResult.BLACKJACK;
import static blackjack.constant.MatchResult.DRAW;
import static blackjack.constant.MatchResult.LOSE;
import static blackjack.constant.MatchResult.WIN;
import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.utils.ParticipantsCreationUtils.createDealerWithDenominations;
import static blackjack.utils.ParticipantsCreationUtils.createPlayerWithDenominations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.constant.MatchResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreBoardTest {

    @Test
    @DisplayName("딜러의 점수가 플레이어보다 높을 때 딜러의 승이다")
    void dealer_win_when_dealer_score_high() {
        // given
        Dealer dealer = createDealerWithDenominations(QUEEN, TEN);
        Player player = createPlayerWithDenominations("user A", JACK, NINE);

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        int dealerMatchResultScore = scoreBoard.findDealerMatchScore(WIN);
        MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(player.getName());

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
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        int dealerMatchResultScore = scoreBoard.findDealerMatchScore(LOSE);
        MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(player.getName());

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
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        int dealerMatchResultScore = scoreBoard.findDealerMatchScore(LOSE);
        MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(player.getName());

        // then
        assertAll(
                () -> assertThat(dealerMatchResultScore).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(WIN));
    }

    @Test
    @DisplayName("플레이어가 버스트이고 딜러는 아닐 때 딜러의 승이다")
    void dealer_win_when_player_bust_dealer_not_bust() {
        // given
        Dealer dealer = createDealerWithDenominations(JACK, NINE);
        Player player = createPlayerWithDenominations("user b", QUEEN, TEN, TWO);

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        int dealerMatchScore = scoreBoard.findDealerMatchScore(WIN);
        MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(player.getName());

        // then
        assertAll(
                () -> assertThat(dealerMatchScore).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(MatchResult.LOSE));
    }

    @Test
    @DisplayName("플레이어와 딜러 동점일 때 무승부이다")
    void player_and_dealer_same_score_is_draw() {
        // given
        Dealer dealer = createDealerWithDenominations(FIVE, SEVEN);
        Player player = createPlayerWithDenominations("user a", JACK, TWO);

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        int dealerMatchScore = scoreBoard.findDealerMatchScore(DRAW);
        MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(player.getName());

        // then
        assertAll(
                () -> assertThat(dealerMatchScore).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(MatchResult.DRAW));
    }

    @Test
    @DisplayName("플레이어와 딜러 둘 다 버스트일 때 무승부이다")
    void player_and_dealer_all_bust() {
        // given
        Dealer dealer = createDealerWithDenominations(FIVE, SEVEN, NINE, TWO); // 23
        Player player = createPlayerWithDenominations("user a", JACK, TWO, QUEEN); // 22

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        int dealerMatchScore = scoreBoard.findDealerMatchScore(WIN);
        MatchResult playerMatchResult = scoreBoard.findPlayerMatchResult(player.getName());

        // then
        assertAll(
                () -> assertThat(dealerMatchScore).isEqualTo(1),
                () -> assertThat(playerMatchResult).isEqualTo(LOSE)
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
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(playerA, playerB, playerC));
        EnumMap<MatchResult, Integer> dealerMatchResults = scoreBoard.getDealerMatchResults();
        Map<String, MatchResult> playersMatchResult = scoreBoard.getPlayersMatchResult();

        // then
        assertAll(
                () -> assertThat(dealerMatchResults.get(WIN)).isEqualTo(2),
                () -> assertThat(dealerMatchResults.get(LOSE)).isEqualTo(1),

                () -> assertThat(playersMatchResult.get(playerA.getName())).isEqualTo(LOSE),
                () -> assertThat(playersMatchResult.get(playerB.getName())).isEqualTo(WIN),
                () -> assertThat(playersMatchResult.get(playerC.getName())).isEqualTo(LOSE));
    }

    @Test
    @DisplayName("플레이어 한명이 승이고 나머지가 패, 무이면 딜러는 1승 1패 1무 이다")
    void player_win_other_lose_and_draw_dealer_1_win_1_lose_1_draw() {
        // given
        Dealer dealer = createDealerWithDenominations(ACE, SEVEN); // 18
        Player playerA = createPlayerWithDenominations("user a", NINE, JACK); // 19
        Player playerB = createPlayerWithDenominations("user b", FIVE, EIGHT); // 13
        Player playerC = createPlayerWithDenominations("user c", QUEEN, SIX, TWO); // 18

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(playerA, playerB, playerC));
        EnumMap<MatchResult, Integer> dealerMatchResults = scoreBoard.getDealerMatchResults();
        Map<String, MatchResult> playersMatchResult = scoreBoard.getPlayersMatchResult();

        // then
        assertAll(
                () -> assertThat(dealerMatchResults.get(WIN)).isEqualTo(1),
                () -> assertThat(dealerMatchResults.get(LOSE)).isEqualTo(1),
                () -> assertThat(dealerMatchResults.get(DRAW)).isEqualTo(1),

                () -> assertThat(playersMatchResult.get(playerA.getName())).isEqualTo(WIN),
                () -> assertThat(playersMatchResult.get(playerB.getName())).isEqualTo(LOSE),
                () -> assertThat(playersMatchResult.get(playerC.getName())).isEqualTo(DRAW));
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러는 아닐때, 플레이어의 상태는 블랙잭이다")
    void player_blackjack_when_player_blackjack_and_dealer_is_not() {
        // given
        Player player = createPlayerWithDenominations("user a", ACE, QUEEN);
        Dealer dealer = createDealerWithDenominations(JACK, TWO);

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        MatchResult playerMatchResult = scoreBoard.getPlayersMatchResult().get(player.getName());

        // then
        assertThat(playerMatchResult).isEqualTo(BLACKJACK);
    }

    @Test
    @DisplayName("플레이어의 점수가 21이지만 카드가 3개 이상일때 플레이어의 상태는 블랙잭이 아닌 WIN 이다")
    void player_not_blackjack_but_win_when_score_21_but_card_num_3() {
        // given
        Player player = createPlayerWithDenominations("user a", SIX, SEVEN, EIGHT);
        Dealer dealer = createDealerWithDenominations(JACK, TWO);

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        MatchResult playerMatchResult = scoreBoard.getPlayersMatchResult().get(player.getName());

        // then
        assertThat(playerMatchResult).isEqualTo(WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭일 때 플레이어의 상태는 DRAW 이다")
    void player_win_when_player_and_dealer_all_blackjack() {
        // given
        Player player = createPlayerWithDenominations("user a", ACE, KING);
        Dealer dealer = createDealerWithDenominations(ACE, QUEEN);

        // when
        ScoreBoard scoreBoard = ScoreBoard.of(dealer, List.of(player));
        MatchResult playerMatchResult = scoreBoard.getPlayersMatchResult().get(player.getName());

        // then
        assertThat(playerMatchResult).isEqualTo(DRAW);
    }
}
