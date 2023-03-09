package domain.game;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.people.Dealer;
import domain.people.Participant;
import domain.people.Player;

class BlackJackGameTest {

    private BlackJackGame blackJackGame;

    @BeforeEach
    void setUp() {
        blackJackGame = BlackJackGame.from(List.of("leo", "reo", "reoleo"), (order -> order));
    }

    @Test
    @DisplayName("참가자들의 handValue를 계산한다.")
    void getScores() {
        blackJackGame.dealCardsToParticipants();

        Map<String, String> scores = blackJackGame.fetchParticipantScores();

        assertAll(
            () -> assertThat(scores.get(new Dealer().getName())).isEqualTo("21"),
            () -> assertThat(scores.get("leo")).isEqualTo("19"),
            () -> assertThat(scores.get("reo")).isEqualTo("18"),
            () -> assertThat(scores.get("reoleo")).isEqualTo("17"));
    }

    @Test
    @DisplayName("플레이어의 게임 결과를 반환한다.")
    void getPlayerResult() {
        blackJackGame.dealCardsToParticipants();
        Map<String, String> playerResults = blackJackGame.calculatePlayerResults();
        assertAll(
            () -> {
                for (Map.Entry<String, String> playerResult : playerResults.entrySet()) {
                    assertThat(playerResult.getValue()).isEqualTo(Result.LOSE.getResult());
                }
            },
            () -> assertThat(playerResults.size()).isEqualTo(3));
    }
}
