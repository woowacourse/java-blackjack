package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Cards {

    private final Stack<Card> cards;

    public Cards(CardMachine cardMachine) {
        this.cards = makeCards();
        cardMachine.shuffleCards(cards);
    }

    public Card assignCard() {
        return cards.pop();
    }

    private Stack<Card> makeCards() {
        Stack<Card> cards = new Stack<>();
        List<Suit> suits = Arrays.stream(Suit.values()).collect(Collectors.toList());
        for (Suit suit : suits) {
            addRankForSuit(cards, suit);
        }
        return cards;
    }

    private void addRankForSuit(Stack<Card> cards, Suit suit) {
        List<Rank> ranks = Arrays.stream(Rank.values()).collect(Collectors.toList());
        for (Rank rank : ranks) {
            cards.add(new Card(suit, rank));
        }
    }
}
