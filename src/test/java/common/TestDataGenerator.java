package common;

import domain.blackjack.BlackjackGame;
import domain.card.DeckFactory;
import domain.money.BetAmount;
import domain.participant.Dealer;
import domain.participant.ParticipantName;
import domain.participant.Player;
import domain.participant.Players;

public class TestDataGenerator {

    /**
     * pobi와 crong, royce가 참가한 BlackjackGame을 반환합니다.
     *
     * @return BlackjackGame
     */
    public static BlackjackGame getShuffledBlackjackGame() {
        Players players = Players.of(
                Player.of(new ParticipantName("pobi"), BetAmount.from(1000)),
                Player.of(new ParticipantName("crong"), BetAmount.from(1000)),
                Player.of(new ParticipantName("royce"), BetAmount.from(1000))
        );

        return BlackjackGame.from(players, DeckFactory.getShuffledDeck());
    }

    public static BlackjackGame getShuffledBlackjackGame(Players players) {
        return BlackjackGame.from(players, DeckFactory.getShuffledDeck());
    }

    public static Player getPlayerWithName(String name) {
        return Player.of(new ParticipantName(name), BetAmount.from(1000));
    }

    public static Player getPlayerWithNameAndBetAmount(String name, int betAmount) {
        return Player.of(new ParticipantName(name), BetAmount.from(betAmount));
    }

    public static Dealer getDealer() {
        return new Dealer();
    }
}
