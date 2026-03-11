package domain.card;

import domain.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(Supplier<Card> getCard) {
        cards.add(getCard.get());
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addAll(Function<Integer, List<Card>> getCardsFunc, int quantity) {
        cards.addAll(getCardsFunc.apply(quantity));
    }

    public Card peek() {
        return cards.getFirst();
    }

    public int size() {
        return cards.size();
    }


    public int getTotalSum() {
        int aceNum = getAceAmount();
        int sum = getSumWithoutAce();

        for (int i = aceNum; i > 0; i--) {
            sum += Rank.decideAceValue(sum, i);
        }
        return sum;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private int getAceAmount() {
        int aceAmount = 0;
        for (Card card : cards) {
            aceAmount += card.getOneIfAce();
        }
        return aceAmount;
    }

    private int getSumWithoutAce() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getRankValueIfNotAce();
        }
        return sum;
    }
}
