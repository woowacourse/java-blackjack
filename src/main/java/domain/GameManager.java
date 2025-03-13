package domain;

import domain.card.CardDeck;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;

public class GameManager {

    public static final int INITIAL_CARDS = 2;
    private static final int DEALER_MIN_SCORE = 16;
    public static final int BLACKJACK_SCORE = 21;

    private final CardDeck cardDeck;
    private final Participants participants;

    private GameManager(CardDeck cardDeck, Participants participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static GameManager of(CardDeck cardDeck, Participants participants) {
        return new GameManager(cardDeck, participants);
    }

    public void distributeCards() {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            participants.passCardToPlayers(cardDeck);
            participants.passCardToDealer(cardDeck.popCard());
        }
    }

    public void passCardToPlayer(String name) {
        participants.passCardToPlayer(name, cardDeck.popCard());
    }

    public boolean passCardToDealer() {
        if (participants.getScoreOfDealer() > DEALER_MIN_SCORE) {
            return false;
        }
        participants.passCardToDealer(cardDeck.popCard());
        return true;
    }

    public int getScoreOf(String name) {
        return participants.getScoreOfPlayer(name);
    }

    public List<String> getPlayersName() {
        return participants.getPlayersName();
    }

    public Player getPlayerByName(String name) {
        return participants.getPlayerByName(name);
    }
}
