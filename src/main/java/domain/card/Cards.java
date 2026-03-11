package domain.card;

import domain.Rank;
import domain.Score;

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


    public Score getTotalSum() {
        int aceNum = getAceAmount();
        Score sum = getSumWithoutAce();

        System.out.println("aceNum = " + aceNum);
        System.out.println("sum = " + sum.getValue());
        for (int i = aceNum; i > 0; i--) {
            sum = sum.add(Rank.decideAceValue(sum, i));
        }
        return sum;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private int getAceAmount() {
        return (int) cards.stream()
                .filter(card -> card.isAce())
                .count();
    }

    private Score getSumWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .map(card -> card.getRank().getScore())
                .reduce(new Score(0), Score::add);
    }
}
