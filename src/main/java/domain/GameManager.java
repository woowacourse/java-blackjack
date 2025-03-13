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
        participants.distributeCards(cardDeck);
    }

    public void passCardToPlayer(String name) {
        participants.passCardToPlayer(name, cardDeck.popCard());
    }

    public boolean passCardToDealer() {
        if (participants.getScoreOfDealer() > DEALER_MIN_SCORE) { // TODO: 판단 로직의 위치에 대한 고민 필요
            return false;
        }
        participants.passCardToDealer(cardDeck.popCard());
        return true;
    }

    public int getScoreOf(String name) {
        return participants.getScoreOf(name);
    }

    public List<String> getPlayersName() {
        return participants.getPlayersName();
    }

    public Player getPlayerByName(String name) {
        return participants.getPlayerByName(name);
    }
}
