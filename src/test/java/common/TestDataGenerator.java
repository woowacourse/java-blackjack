package common;

import domain.blackjack.BlackjackGame;
import domain.participant.Dealer;
import domain.card.DeckFactory;
import domain.participant.Player;
import domain.participant.ParticipantName;
import domain.participant.PlayerNames;
import domain.participant.Players;
import java.util.List;

public class TestDataGenerator {

    /**
     * pobi와 crong, royce가 참가한 BlackjackGame을 반환합니다.
     *
     * @return BlackjackGame
     */
    public static BlackjackGame getShuffledBlackjackGame() {
        PlayerNames playerNames = PlayerNames.from(List.of("pobi", "crong", "royce"));
        Players players = Players.from(playerNames);

        return BlackjackGame.from(players, DeckFactory.getShuffledDeck());
    }

    public static BlackjackGame getShuffledBlackjackGame(Players players) {
        return BlackjackGame.from(players, DeckFactory.getShuffledDeck());
    }

    public static Player getPlayerWithName(String name) {
        return Player.from(new ParticipantName(name));
    }

    public static Dealer getDealer() {
        return new Dealer();
    }
}
