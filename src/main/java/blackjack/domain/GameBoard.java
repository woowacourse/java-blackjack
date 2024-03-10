package blackjack.domain;

import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameBoard {

    public static final int INITIAL_CARD_COUNT = 2;

    private final Participants participants;
    private final Deck deck;

    public GameBoard(Dealer dealer, Players players) {
        this.participants = new Participants(dealer, players);
        this.deck = new Deck();
        addAllCard();
    }

    private void addAllCard() {
        Arrays.stream(Rank.values())
                .forEach(this::addAllShape);
    }

    private void addAllShape(Rank rank) {
        Arrays.stream(Shape.values())
                .forEach((shape) -> deck.addCard(new Card(shape, rank)));
    }

    public int countPlayers() {
        return participants.countPlayers();
    }

    public boolean isDealerNotOver() {
        return participants.isDealerNotOver();
    }

    public void distributeInitialHands() {
        List<Hands> initialDecks = makeInitialHands();
        participants.receiveInitialHands(initialDecks);
    }

    private List<Hands> makeInitialHands() {
        List<Hands> initialDecks = new ArrayList<>();
        for (int participantsCount = 0; participantsCount < participants.count(); participantsCount++) {
            initialDecks.add(makeHands());
        }
        return initialDecks;
    }

    private Hands makeHands() {
        Hands hands = new Hands(new ArrayList<>());
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            hands.addCard(deck.pickRandomCard());
        }
        return hands;
    }

    public void addCardToDealer() {
        participants.receiveDealerCard(deck.pickRandomCard());
    }

    public void addCardToPlayer(Player player) {
        player.receiveCard(deck.pickRandomCard());
    }

    public Map<Player, Boolean> calculateWinOrLose() {
        return participants.calculateWinOrLose();
    }

    public Deck getDeck() {
        return deck;
    }

    public Players getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
