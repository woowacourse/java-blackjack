package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;
    private final CardPickMachine cardPickMachine;

    public Cards(CardPickMachine cardPickMachine) {
        this.cards = makeCards();
        this.cardPickMachine = cardPickMachine;
    }

    public Card assignCard() {
        return cards.get(cardPickMachine.assignIndex());
    }

    private List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        List<Suit> suits = Arrays.stream(Suit.values()).collect(Collectors.toList());
        for (Suit suit : suits) {
            addRankForSuit(cards, suit);
        }
        return cards;
    }

    private void addRankForSuit(List<Card> cards, Suit suit) {
        List<Rank> ranks = Arrays.stream(Rank.values()).collect(Collectors.toList());
        for (Rank rank : ranks) {
            cards.add(new Card(suit, rank));
        }
    }
}
