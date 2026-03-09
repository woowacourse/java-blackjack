package blackjack.service;

import blackjack.domain.Card;
import blackjack.domain.Rank;
import blackjack.domain.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCardPicker {
    private final Random random;
    private final List<Card> notDrawnCards;

    public RandomCardPicker(Random random) {
        this.random = random;
        this.notDrawnCards = initializeNotDrawnCards();
    }

    public Card drawCard() {
        int randomCardIndex = random.nextInt(notDrawnCards.size());
        return notDrawnCards.remove(randomCardIndex);
    }

    private List<Card> initializeNotDrawnCards() {
        /*List<String> ranks = List.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
        List<String> shapes = List.of("하트", "스페이드", "다이아몬드", "클로버");

        List<Card> notDrawnCards = new ArrayList<>();
        for (String rank : ranks) {
            for (String shape : shapes) {
                notDrawnCards.add(new Card(rank, shape));
            }
        }
        return notDrawnCards;*/

        List<Card> cards = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (Shape shape : Shape.values()) {
                cards.add(new Card(rank, shape));
            }
        }
        return cards;
    }


}
