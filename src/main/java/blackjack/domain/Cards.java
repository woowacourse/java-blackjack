package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;
    private final CardPickMachine cardPickMachine;

    public Cards(CardPickMachine cardPickMachine) {
        this.cards = new ArrayList<>();
        makeCards();
        this.cardPickMachine = cardPickMachine;
    }

    public Card assignCard() {
        return cards.get(cardPickMachine.assignIndex());
    }

    private void makeCards() {
        List<Suit> suits = Arrays.stream(Suit.values())
                .collect(Collectors.toList());
        List<Rank> ranks = Arrays.stream(Rank.values())
                .collect(Collectors.toList());
        for (Suit suit : suits) {
            addRankForSuit(ranks, suit);
        }
    }

    private void addRankForSuit(List<Rank> ranks, Suit suit) {
        for (Rank rank : ranks) {
            cards.add(new Card(suit, rank));
        }
    }
}
