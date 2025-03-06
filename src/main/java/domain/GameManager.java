package domain;

import domain.card.CardDeck;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.Collections;
import java.util.List;

public class GameManager {

    private static final int INITIAL_CARDS = 2;
    public static final int DEALER_MIN_SCORE = 16;

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
            participants.receiveCards(cardDeck);
        }
    }

    public void passCardToPlayer(String name) {
        Player player = participants.findByName(name);
        player.receive(cardDeck.popCard());
    }

    public boolean passCardToDealer() {
        Participant dealer = participants.getDealer();
        if (dealer.getScore() > DEALER_MIN_SCORE) {
            return false;
        }
        dealer.receive(cardDeck.popCard());
        return true;
    }

    public int getScoreOf(String name) {
        Player player = participants.findByName(name);
        return player.getScore();
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(participants.getPlayersName());
    }
}
