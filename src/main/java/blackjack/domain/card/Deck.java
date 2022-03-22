package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Deck {

    private final Stack<Card> deck;

    public Deck(CardMachine cardMachine) {
        this.deck = makeCards();
        cardMachine.shuffleCards(deck);
    }

    public Card assignCard() {
        return deck.pop();
    }

    private Stack<Card> makeCards() {
        Stack<Card> deck = new Stack<>();
        List<Suit> suits = Arrays.stream(Suit.values()).collect(Collectors.toList());
        for (Suit suit : suits) {
            addRankForSuit(deck, suit);
        }
        return deck;
    }

    private void addRankForSuit(Stack<Card> deck, Suit suit) {
        List<Rank> ranks = Arrays.stream(Rank.values()).collect(Collectors.toList());
        for (Rank rank : ranks) {
            deck.add(new Card(suit, rank));
        }
    }
}
