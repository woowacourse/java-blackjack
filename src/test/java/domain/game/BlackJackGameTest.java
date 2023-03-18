package domain.game;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.people.Player;

class BlackJackGameTest {

    private BlackJackGame blackJackGame;

    @BeforeEach
    void setUp() {
        blackJackGame = BlackJackGame.from(List.of("leo", "reo", "reoleo"), (order -> order));
    }

    @Test
    @DisplayName("딜러와 플레이어들에게 카드를 두장씩 나눠준다.")
    void dealTest() {
        blackJackGame.dealCardsToParticipants();
        assertAll(
            () -> assertThat(blackJackGame.getDealer().fetchHand().size()).isEqualTo(2),
            () -> {
                for (Player player : blackJackGame.fetchPlayers()) {
                    assertThat(player.fetchHand().size()).isEqualTo(2);
                }
            });
    }

    @Test
    @DisplayName("플레이어들의 이름을 반환한다.")
    void fetchParticipantNamesTest() {
        assertThat(blackJackGame.fetchPlayers().stream().map(Player::fetchPlayerName)).containsExactly("leo", "reo",
            "reoleo");
    }

    @Test
    @DisplayName("딜러의 이름을 반환한다.")
    void fetchDealerNamesTest() {
        assertThat(blackJackGame.getDealer().getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("참가자들에게 2장을 나누어준다.")
    void fetchParticipantsInitHandsPlayerTest() {
        blackJackGame.dealCardsToParticipants();
        for (Player player : blackJackGame.fetchPlayers()) {
            assertThat(player.fetchHand()).hasSize(2);
        }
    }

    @Test
    @DisplayName("hit요청이 있을 시 hit을 한다.")
    void hitOrStay() {
        blackJackGame.dealCardsToParticipants();
        blackJackGame.hitOrStay("y", blackJackGame.getPlayers().getPlayers().get(0));
        assertAll(
            () -> assertThat(blackJackGame.getPlayers().getPlayers().get(0).fetchHand()).hasSize(3),
            () -> assertThat(blackJackGame.getPlayers().getPlayers().get(1).fetchHand()).hasSize(2));
    }

    @Test
    @DisplayName("딜러가 hit을 한다.")
    void dealerHit() {
        blackJackGame.dealCardsToParticipants();
        blackJackGame.dealerHit();
        assertThat(blackJackGame.getDealer().fetchHand()).hasSize(3);
    }

    @Test
    @DisplayName("참가자들의 점수를 반환한다.")
    void fetchParticipantScores() {
        blackJackGame.dealCardsToParticipants();
        assertAll(
            () -> assertThat(blackJackGame.getDealer().fetchHandValue()).isEqualTo(21),
            () -> assertThat(blackJackGame.fetchPlayers().get(0).fetchHandValue()).isEqualTo(19),
            () -> assertThat(blackJackGame.fetchPlayers().get(1).fetchHandValue()).isEqualTo(18),
            () -> assertThat(blackJackGame.fetchPlayers().get(2).fetchHandValue()).isEqualTo(17));
    }

    @Test
    @DisplayName("딜러의 수익을 계산한다.")
    void calculateDealerProfitTest() {
        blackJackGame.fetchPlayers().get(0).assignBetAmount(1000);
        blackJackGame.fetchPlayers().get(1).assignBetAmount(2000);
        blackJackGame.fetchPlayers().get(2).assignBetAmount(3000);
        blackJackGame.dealCardsToParticipants();
        assertThat(blackJackGame.calculateDealerProfit()).isEqualTo(6000);
    }

    @Test
    @DisplayName("참가자들의 수익을 계산한다.")
    void calculatePlayerProfitTest() {
        blackJackGame.fetchPlayers().get(0).assignBetAmount(1000);
        blackJackGame.fetchPlayers().get(1).assignBetAmount(2000);
        blackJackGame.fetchPlayers().get(2).assignBetAmount(3000);
        blackJackGame.dealCardsToParticipants();
        assertAll(
            () -> assertThat(blackJackGame.calculatePlayerProfit().get(blackJackGame.fetchPlayers().get(0))).isEqualTo(
                -1000),
            () -> assertThat(blackJackGame.calculatePlayerProfit().get(blackJackGame.fetchPlayers().get(1))).isEqualTo(
                -2000),
            () -> assertThat(blackJackGame.calculatePlayerProfit().get(blackJackGame.fetchPlayers().get(2))).isEqualTo(
                -3000)
        );
    }
}
