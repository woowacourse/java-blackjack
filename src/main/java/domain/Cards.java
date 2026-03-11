package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;
    private int changeAvailableAceCount;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        changeAvailableAceCount = 0;
    }

    public static Cards createCards() {
        List<Card> cards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(shape, number));
            }
        }
        return new Cards(cards);
    }

    public static void shuffleCards(Cards beforeShuffle) {
        Collections.shuffle(beforeShuffle.cards);
    }

    public static Integer getCardsSize(Cards targetCards) {
        return targetCards.cards.size();
    }

    public List<Card> getCards() {
        return cards.stream()
                .toList();
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .toList();
    }

    public Card removeFirst() {
        return cards.removeFirst();
    }

    public int calculateScore() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getScore();
        }

        int availableAceCount = changeAvailableAceCount;
        while (availableAceCount > 0 && sum > 21) {
            sum -= 10;
            availableAceCount -= 1;
        }

        return sum;
    }

    public void addCard(Card card) {
        if (card.isAce()) {
            changeAvailableAceCount += 1;
        }
        cards.add(card);
    }
}
