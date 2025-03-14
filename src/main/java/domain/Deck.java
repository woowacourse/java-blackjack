package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                createDeckCards(suit, denomination);
            }
        }
    }

    private void createDeckCards(Suit suit, Denomination denomination) {
        if (denomination.equals(Denomination.ACE)) {
            deck.add(new Ace(suit));
            return;
        }
        deck.add(new Card(denomination, suit));
    }

    public void distributeCards(Participants participants) {
        shuffle();
        drawTwoCardFromDeck(participants.getDealer());
        for (Player player : participants.getPlayers().getPlayers()) {
            drawTwoCardFromDeck(player);
        }
    }

    private void drawTwoCardFromDeck(Player player) {
        player.addCard(draw());
        player.addCard(draw());
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card draw() {
        return deck.removeFirst();
    }

    public List<Card> getDeck() {
        return Collections.unmodifiableList(deck);
    }
}
