package domain;

public class GameManager {

    private static final int INITIAL_CARDS = 2;

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
}
