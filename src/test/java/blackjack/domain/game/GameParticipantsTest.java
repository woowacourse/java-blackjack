package blackjack.domain.game;

import blackjack.domain.gameresult.Batting;
import blackjack.domain.hands.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameParticipantsTest {
    @DisplayName("게임 참여자들은 초기패로 2장의 카드를 받는다")
    @Test
    void should_getTwoCards_To_InitialHands() {
        List<Player> testPlayers = new ArrayList<>();
        Players players = new Players(List.of("pobi", "coli"));
        GameParticipants gameParticipants = GameParticipants.of(players);

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
