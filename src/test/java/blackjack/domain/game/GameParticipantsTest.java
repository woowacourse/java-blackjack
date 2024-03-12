package blackjack.domain.game;

import blackjack.domain.game.Deck;
import blackjack.domain.game.GameParticipants;
import blackjack.domain.gameresult.Batting;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameParticipantsTest {
    @DisplayName("게임 참여자들은 초기패로 2장의 카드를 받는다")
    @Test
    void should_getTwoCards_To_InitialHands() {
        Map<Name, Batting> playerNamesAndBattings = new HashMap<>();
        playerNamesAndBattings.put(new Name("pobi"), Batting.from(1.0));
        playerNamesAndBattings.put(new Name("coli"), Batting.from(1.0));
        GameParticipants gameParticipants = GameParticipants.of(playerNamesAndBattings);

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
