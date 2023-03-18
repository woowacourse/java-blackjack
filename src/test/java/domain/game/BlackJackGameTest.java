package domain.game;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
            () -> assertThat(blackJackGame.fetchPlayerHand(blackJackGame.fetchDealer()).size()).isEqualTo(2),
            () -> assertThat(blackJackGame.fetchPlayerHand("leo").size()).isEqualTo(2),
            () -> assertThat(blackJackGame.fetchPlayerHand("reo").size()).isEqualTo(2),
            () -> assertThat(blackJackGame.fetchPlayerHand("reoleo").size()).isEqualTo(2));
    }

    @Test
    @DisplayName("참가자들의 이름을 반환한다.")
    void fetchParticipantNamesTest() {
        assertThat(blackJackGame.fetchParticipantNames()).containsExactly("딜러", "leo", "reo", "reoleo");
    }

    @Test
    @DisplayName("플레이어들의 이름을 반환한다.")
    void fetchPlayerNamesTest() {
        assertThat(blackJackGame.fetchPlayerNames()).containsExactly("leo", "reo", "reoleo");
    }

    @Test
    @DisplayName("딜러의 이름을 반환한다.")
    void fetchDealerNamesTest() {
        assertThat(blackJackGame.fetchDealer()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("참가자들의 첫 패를 반환한다. - 플레이어의 경우 2장을 반환한다.")
    void fetchParticipantsInitHandsPlayerTest() {
        blackJackGame.dealCardsToParticipants();
        assertAll(
            () -> assertThat(blackJackGame.fetchPlayerInitHand("leo")).hasSize(2),
            () -> assertThat(blackJackGame.fetchPlayerInitHand("reo")).hasSize(2),
            () -> assertThat(blackJackGame.fetchPlayerInitHand("reoleo")).hasSize(2));
    }

    @Test
    @DisplayName("참가자들의 첫 패를 반환한다. - 딜러의 경우 1장을 반환한다.")
    void fetchParticipantsInitHandsDealerTest() {
        blackJackGame.dealCardsToParticipants();
        assertThat(blackJackGame.fetchPlayerInitHand("딜러")).hasSize(1);
    }

    @Test
    @DisplayName("hit요청이 있을 시 hit을 한다.")
    void hitOrStay() {
        blackJackGame.dealCardsToParticipants();
        blackJackGame.hitOrStay("y", "reo");
        assertAll(
            () -> assertThat(blackJackGame.fetchPlayerHand("reo")).hasSize(3),
            () -> assertThat(blackJackGame.fetchPlayerHand("leo")).hasSize(2));
    }

    @Test
    @DisplayName("딜러가 hit을 한다.")
    void dealerHit() {
        blackJackGame.dealCardsToParticipants();
        blackJackGame.dealerHit();
        assertThat(blackJackGame.fetchPlayerHand("딜러")).hasSize(3);
    }

    @Test
    @DisplayName("참가자들의 점수를 반환한다.")
    void fetchParticipantScores() {
        blackJackGame.dealCardsToParticipants();
        assertAll(
            () -> assertThat(blackJackGame.fetchParticipantScore("딜러")).isEqualTo(21),
            () -> assertThat(blackJackGame.fetchParticipantScore("leo")).isEqualTo(19),
            () -> assertThat(blackJackGame.fetchParticipantScore("reo")).isEqualTo(18),
            () -> assertThat(blackJackGame.fetchParticipantScore("reoleo")).isEqualTo(17));
    }
}
