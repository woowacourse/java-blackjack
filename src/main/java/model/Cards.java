package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Cards createShuffledDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(Cards::combinate)
                .collect(Collectors.toCollection(ArrayList::new));
        cardsShuffle(cards);
        return new Cards(cards);
    }

    public static Cards createEmpty() {
        return new Cards(new ArrayList<>());
    }

    private static Stream<Card> combinate(CardShape shape) {
        return Arrays.stream(CardValue.values())
                .map(value -> new Card(shape, value));
    }

    private static void cardsShuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public Card pickCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 존재하지 않습니다.");
        }
        return cards.removeLast();
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return List.copyOf(this.cards);
    }
}
