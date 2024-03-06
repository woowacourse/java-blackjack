package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BlackjackService {
    public static final int INITIAL_CARD_COUNT = 2;
    private final Participants participants;
    private final Deck deck;

    public BlackjackService(Player dealer, List<Player> players) {
        this.participants = new Participants(dealer, players);
        this.deck = new Deck();
        Arrays.stream(Rank.values())
                .forEach((rank) -> {
                    Arrays.stream(Shape.values())
                            .forEach((shape) -> deck.addCard(new Card(shape, rank)));
                });
    }

    public int countPlayers() {
        return participants.countPlayers();
    }

    public boolean isPlayerNotOver(int playerIndex) {
        return participants.isPlayerNotOver(playerIndex);
    }

    public void initialDistribute() {
        List<Deck> initialCards = new ArrayList<>();
        for (int participantsCount = 0; participantsCount < participants.count(); participantsCount++) {
            initialCards.add(makeInitialDeck());
        }
        participants.receiveInitialCards(initialCards);
    }

    private Deck makeInitialDeck() {
        Deck tempDeck = new Deck();
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            tempDeck.addCard(this.deck.pickRandomCard());
        }
        return tempDeck;
    }

    public Deck getDeck() {
        return deck;
    }

    public void addCardToPlayer(int playerIndex) {
        participants.receiveCard(deck.pickRandomCard(), playerIndex);
    }

    public void addCardToDealer() {
        participants.receiveDealerCard(deck.pickRandomCard());
    }

    public Name getPlayerName(int playerIndex) {
        return participants.getPlayerName(playerIndex);
    }


    public Map<Player, Boolean> calculateVictory() {
        return participants.calculateVictory();
    }

    public Player getPlayer(int playerIndex) {
        return participants.getPlayer(playerIndex);
    }

    public boolean IsDealerNotOver() {
        return participants.isDealerNotOver();
    }


    public Player getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
