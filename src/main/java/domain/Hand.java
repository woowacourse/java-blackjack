package domain;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    void drawCard() {
        cards.add(createCard());
    }

    int scoreSum() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    List<String> cardInfos() {
        return cards.stream().map(Card::info).collect(Collectors.toList());
    }

    private Card createCard() {
        return new Card(
                CardRank.values()[new Random().nextInt(CardRank.values().length)],
                CardMark.values()[new Random().nextInt(CardMark.values().length)]
        );
    }

    List<Card> cards() {
        return List.copyOf(cards);
    }
}
