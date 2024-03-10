package blackjack.domain;

import blackjack.domain.participants.Name;
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
    private final Deck allCardDeck;

    public GameBoard(Dealer dealer, Players players) {
        this.participants = new Participants(dealer, players);
        this.allCardDeck = new Deck();
        addAllCard();
    }

    private void addAllCard() {
        Arrays.stream(Rank.values())
                .forEach(this::addAllShape);
    }

    private void addAllShape(Rank rank) {
        Arrays.stream(Shape.values())
                .forEach((shape) -> allCardDeck.addCard(new Card(shape, rank)));
    }

    public int countPlayers() {
        return participants.countPlayers();
    }

    public boolean isPlayerNotOver(int playerIndex) {
        return participants.isPlayerNotOver(playerIndex);
    }

    public boolean isDealerNotOver() {
        return participants.isDealerNotOver();
    }

    public void distributeInitialHands() {
        List<Hands> initialDecks = makeInitialDecks();
        participants.receiveInitialHands(initialDecks);
    }

    private List<Hands> makeInitialDecks() {
        List<Hands> initialDecks = new ArrayList<>();
        for (int participantsCount = 0; participantsCount < participants.count(); participantsCount++) {
            initialDecks.add(makeInitialDeck());
        }
        return initialDecks;
    }

    private Hands makeInitialDeck() {
        Hands hands = new Hands(new ArrayList<>());
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            hands.addCard(this.allCardDeck.pickRandomCard());
        }
        return hands;
    }

    public void addCardToPlayer(int playerIndex) {
        participants.receivePlayerCard(allCardDeck.pickRandomCard(), playerIndex);
    }

    public void addCardToDealer() {
        participants.receiveDealerCard(allCardDeck.pickRandomCard());
    }

    public Map<Player, Boolean> calculateWinOrLose() {
        return participants.calculateWinOrLose();
    }

    public Deck getAllCardDeck() {
        return allCardDeck;
    }

    public Name getPlayerName(int playerIndex) {
        return participants.getOnePlayerName(playerIndex);
    }

    public Players getPlayers() {
        return participants.getPlayers();
    }

    public Player getPlayer(int playerIndex) {
        return participants.getOnePlayer(playerIndex);
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
