package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackjack.domain.CardConstant.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class ResultGameTest {
    private Dealer dealer;
    private Participants participants;
    private Map<Participant, WinTieLose> playersResult;


    @BeforeEach
    void setting() {
        dealer = Dealer.from(new ArrayList<>());
        participants = Participants.of(dealer, List.of("pobi", "crong", "dali"));
        playersResult = new HashMap<>();
    }

    @Test
    @DisplayName("생성자 테스트")
    void constructorTest() {
        assertThatNoException().isThrownBy(() -> ResultGame.from(playersResult, participants));
    }

    @Nested
    @DisplayName("플레이어가 버스트일 때")
    class PlayerBust {
        final Dealer dealer = Dealer.from(new ArrayList<>());
        final Participants participants = Participants.of(dealer, List.of("pobi"));
        final Map<Participant, WinTieLose> playersResult = new HashMap<>();
        Participant player;

        @BeforeEach
        void initialPlayerBust() {
            player = participants.getPlayers().get(0);

            player.drawCard(HEART_SEVEN);
            player.drawCard(HEART_EIGHT);
            player.drawCard(HEART_TEN);
        }

        @Test
        @DisplayName("딜러가 버스트일 때 TIE가 나온다.")
        void dealerBust() {
            dealer.drawCard(DIAMOND_SEVEN);
            dealer.drawCard(DIAMOND_EIGHT);
            dealer.drawCard(DIAMOND_TEN);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.TIE);
        }

        @Test
        @DisplayName("딜러가 버스트가 아닐 때 LOSE가 나온다.")
        void dealerNotBust() {
            dealer.drawCard(DIAMOND_THREE);
            dealer.drawCard(DIAMOND_EIGHT);
            dealer.drawCard(DIAMOND_TEN);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.LOSE);
        }
    }

    @Nested
    @DisplayName("플레이어가 블랙잭일 때")
    class PlayerNormal {
        final Dealer dealer = Dealer.from(new ArrayList<>());
        final Participants participants = Participants.of(dealer, List.of("pobi"));
        final Map<Participant, WinTieLose> playersResult = new HashMap<>();
        Participant player;

        @BeforeEach
        void initialPlayerBust() {
            player = participants.getPlayers().get(0);

            player.drawCard(HEART_THREE);
            player.drawCard(HEART_EIGHT);
            player.drawCard(HEART_TEN);
        }

        @Test
        @DisplayName("딜러가 플레이어보다 점수가 높을 때 WIN이 나온다.")
        void dealerGreaterThanPlayer() {
            dealer.drawCard(DIAMOND_TEN);
            dealer.drawCard(DIAMOND_EIGHT);
            dealer.drawCard(DIAMOND_SEVEN);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
        }

        @Test
        @DisplayName("딜러가 플레이어와 점수가 같을 때 TIE가 나온다.")
        void dealerEqualsPlayer() {
            dealer.drawCard(DIAMOND_TEN);
            dealer.drawCard(DIAMOND_EIGHT);
            dealer.drawCard(DIAMOND_THREE);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.TIE);
        }

        @Test
        @DisplayName("딜러가 블랙잭이 아닐 때 WIN이 나온다.")
        void dealerLessThanPlayer() {
            dealer.drawCard(DIAMOND_TEN);
            dealer.drawCard(DIAMOND_EIGHT);
            dealer.drawCard(DIAMOND_TWO);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
        }
    }

    @Nested
    @DisplayName("플레이어가 블랙잭이 아닐 때")
    class PlayerBlackjack {
        final Dealer dealer = Dealer.from(new ArrayList<>());
        final Participants participants = Participants.of(dealer, List.of("pobi"));
        final Map<Participant, WinTieLose> playersResult = new HashMap<>();
        Participant player;

        @BeforeEach
        void initialPlayerBust() {
            player = participants.getPlayers().get(0);

            player.drawCard(HEART_TEN);
            player.drawCard(HEART_EIGHT);
            player.drawCard(HEART_TWO);
        }

        @Test
        @DisplayName("딜러가 버스트일 때 WIN이 나온다.")
        void dealerBust() {
            dealer.drawCard(DIAMOND_TEN);
            dealer.drawCard(DIAMOND_EIGHT);
            dealer.drawCard(DIAMOND_SEVEN);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
        }

        @Test
        @DisplayName("딜러가 버스트가 아니고, 플레이어보다 점수가 높을 때 LOSE가 나온다.")
        void dealerNotBustAndGreaterThanPlayer() {
            dealer.drawCard(DIAMOND_TEN);
            dealer.drawCard(DIAMOND_EIGHT);
            dealer.drawCard(DIAMOND_THREE);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.LOSE);
        }

        @Test
        @DisplayName("딜러가 플레이어와 점수가 같을 때 TIE이 나온다.")
        void dealerEqualPlayer() {
            dealer.drawCard(DIAMOND_TEN);
            dealer.drawCard(DIAMOND_EIGHT);
            dealer.drawCard(DIAMOND_TWO);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.TIE);
        }

        @Test
        @DisplayName("딜러가 플레이어보다 점수가 낮을 때 WIN이 나온다.")
        void dealerLessThanPlayer() {
            dealer.drawCard(DIAMOND_TEN);
            dealer.drawCard(DIAMOND_SEVEN);
            dealer.drawCard(DIAMOND_TWO);

            final ResultGame resultGame = ResultGame.from(playersResult, participants);
            assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
        }
    }

    @Test
    @DisplayName("딜러의 승무패가 제대로 나오는지 테스트")
    void getDealerCountTest() {
        // given
        final Participant player = participants.getPlayers().get(0);

        // when
        dealer.drawCard(DIAMOND_EIGHT);
        player.drawCard(DIAMOND_NINE);
        final ResultGame resultGame = ResultGame.from(playersResult, participants);

        // then
        assertThat(resultGame.getDealerCount(WinTieLose.WIN)).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어의 결과를 출력하는 테스트")
    void getPlayerResultTest() {
        // given
        final Participant player = participants.getPlayers().get(0);

        // when
        dealer.drawCard(DIAMOND_EIGHT);
        player.drawCard(DIAMOND_NINE);
        final ResultGame resultGame = ResultGame.from(playersResult, participants);

        // then
        assertThat(resultGame.getPlayerResult(player)).isEqualTo(WinTieLose.WIN);
    }
}
