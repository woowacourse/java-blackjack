package model.game;

import static model.card.CardNumber.JACK;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import model.card.Card;
import model.participant.Player;
import model.participant.Players;
import model.result.ParticipantCard;
import model.result.ParticipantCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("게임 시작 시 딜러와 플레이어에게 카드 2장 지급")
    @Test
    void dealInitialCards() {
        Players players = Players.from(List.of("조조", "릴리"));
        BlackjackGame blackjackGame = new BlackjackGame();
        ParticipantCards participantCards = blackjackGame.dealInitialCards(players);

        assertAll(
            () -> assertThat(participantCards.playerNames()).contains("조조", "릴리"),
            () -> assertThat(participantCards.dealerName()).isEqualTo("딜러"),
            () -> assertThat(participantCards.getDealerCard().getCards()).hasSize(1),
            () -> participantCards.getPlayerCards()
                .forEach(participantCard -> assertThat(participantCard.getCards()).hasSize(2))
        );
    }

    @DisplayName("플레이어에게 추가 카드 지급")
    @Test
    void dealPlayerCard() {
        Player player = Player.of("jojo", List.of(new Card(JACK, HEART), new Card(TWO, CLOVER)));
        BlackjackGame blackjackGame = new BlackjackGame();
        ParticipantCard playerCard = blackjackGame.dealCard(player);
        assertAll(
            () -> assertThat(playerCard.getName()).isEqualTo("jojo"),
            () -> assertThat(playerCard.getCards()).hasSize(3)
        );
    }
}
