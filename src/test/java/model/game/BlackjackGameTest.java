package model.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import model.participant.Players;
import model.result.ParticipantCards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 2장 지급")
    @Test
    void createWithInitialCards() {
        Players players = Players.from(List.of("조조", "릴리"));
        BlackjackGame blackjackGame = new BlackjackGame();
        ParticipantCards participantCards = blackjackGame.dealInitialCards(players);

        Assertions.assertAll(
            () -> assertThat(participantCards.playerNames()).contains("조조", "릴리"),
            () -> assertThat(participantCards.dealerName()).isEqualTo("딜러"),
            () -> assertThat(participantCards.getDealerCard().getCards()).hasSize(1),
            () -> participantCards.getPlayerCards()
                .forEach(participantCard -> assertThat(participantCard.getCards()).hasSize(2))
        );
    }
}
