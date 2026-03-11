package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private final PlayerName name;
    private final List<Card> cards;

    public Participant(String name) {
        this.name = new PlayerName(name);
        this.cards = new ArrayList<>();
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public int calculateCardsValue() {
        int sum = cards.stream()
                .mapToInt(Card::getCardValue)
                .sum();

        return applyBestAceValue(sum);
    }

    public boolean winsAgainst(Participant other) {
        if (other.isBurst()) {
            return true;
        }
        if (this.isBurst()) {
            return false;
        }

        return other.calculateCardsValue() < this.calculateCardsValue();
    }

    public boolean isBurst() {
        return calculateCardsValue() > 21;
    }

    public boolean isBlackjack() {
        return calculateCardsValue() == 21;
    }

    public String getFirstCardName() {
        return cards.getFirst().getName();
    }

    public String getName() {
        return name.name();
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<String> getCardsName() {
        return cards.stream()
                .map(Card::getName)
                .toList();
    }

    public abstract boolean canDraw();

    private int applyBestAceValue(int sum) {
        if (hasAce() && (sum + 10) <= 21) {
            return sum + 10;
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }


}
