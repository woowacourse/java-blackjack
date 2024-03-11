package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameParticipantsTest {
    @DisplayName(" 게임 참여자들은 초기패로 2장의 카드를 받는다")
    @Test
    void should_getTwoCards_To_InitialHands() {
        GameParticipants gameParticipants = GameParticipants.of(List.of("pobi", "coli"));

        gameParticipants.handOutInitialCards(Deck.createShuffledDeck());

        Players gamePlayers = gameParticipants.getPlayers();
        assertAll(
                () -> assertThat(gameParticipants.getDealer()
                        .getHandsCards()).hasSize(2),
                () -> assertThat(gamePlayers.getPlayers())
                        .allMatch(player -> player.getHandsCards().size() == 2)
        );
    }
}
