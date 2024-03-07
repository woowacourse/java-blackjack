package domain;

import domain.deck.Card;
import domain.deck.Deck;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.participants.Name;
import domain.participants.Participants;
import domain.participants.Player;
import domain.participants.Players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameBoard {
    public static final int INITIAL_CARD_COUNT = 2;
    private final Participants participants;
    private final Deck allCardDeck;

    public GameBoard(Player dealer, Players players) {
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

    public void initialDistribute() {
        List<Deck> initialCards = new ArrayList<>();
        for (int participantsCount = 0; participantsCount < participants.count(); participantsCount++) {
            initialCards.add(makeInitialDeck());
        }
        participants.receiveInitialDecks(initialCards);
    }

    private Deck makeInitialDeck() {
        Deck tempDeck = new Deck();
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            tempDeck.addCard(this.allCardDeck.pickRandomCard());
        }
        return tempDeck;
    }

    public void addCardToPlayer(int playerIndex) {
        participants.receivePlayerCard(allCardDeck.pickRandomCard(), playerIndex);
    }

    public void addCardToDealer() {
        participants.receiveDealerCard(allCardDeck.pickRandomCard());
    }

    public Map<Player, Boolean> calculateVictory() {
        return participants.calculateResult();
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

    public Player getDealer() {
        return participants.getDealer();
    }
}
