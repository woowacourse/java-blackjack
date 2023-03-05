package domain.game;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Deck;
import domain.people.Dealer;
import domain.people.Participant;
import domain.people.Player;

class BlackJackGameTest {

    private BlackJackGame blackJackGame;

    @BeforeEach
    void setUp() {
        blackJackGame = BlackJackGame.from(List.of("leo", "reo", "reoleo"), Deck.from((orderedDeck) -> orderedDeck));
    }

    @Test
    @DisplayName("참가자들의 handValue를 계산한다.")
    void getScores() {
        blackJackGame.dealCardsToParticipants();

        Map<Participant, String> scores = blackJackGame.getParticipantScores();

        assertAll(
            () -> assertThat(scores.get(new Dealer())).isEqualTo("21"),
            () -> assertThat(scores.get(new Player("leo"))).isEqualTo("19"),
            () -> assertThat(scores.get(new Player("reo"))).isEqualTo("18"),
            () -> assertThat(scores.get(new Player("reoleo"))).isEqualTo("17"));
    }

    @Test
    @DisplayName("플레이어의 게임 결과를 반환한다.")
    void getPlayerResult() {
        blackJackGame.dealCardsToParticipants();
        Map<String, Result> playerResults = blackJackGame.calculatePlayerResults();
        assertAll(
            () -> {
                for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
                    assertThat(playerResult.getValue()).isEqualTo(Result.LOSE);
                }
            },
            () -> assertThat(playerResults.size()).isEqualTo(3));
    }
}
