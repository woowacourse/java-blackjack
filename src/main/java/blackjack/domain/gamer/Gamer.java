package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static blackjack.domain.result.BlackJackResult.MAX_CARD_VALUE;

public abstract class Gamer {
    public static final int INIT_DISTRIBUTION_COUNT = 2;

    private final Name name;
    private final List<Card> cards;

    public Gamer(String name) {
        this.name = new Name(name);
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getCardsNumberSum() {
        int sum = getSumExceptAce();
        List<Card> aces = getAces();
        return getPlusAceForNotBust(sum, aces);
    }

    private int getSumExceptAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getValue)
                .sum();
    }

    private List<Card> getAces() {
        return cards.stream()
                .filter(Card::isAce)
                .collect(Collectors.toList());
    }

    private int getPlusAceForNotBust(int sum, List<Card> aces) {
        for (Card ace : aces) {
            sum += ace.getAceValue(sum);
        }
        return sum;
    }

    public String getName() {
        return name.getValue();
    }

    public int getCardsSize() {
        return cards.size();
    }

    public boolean isBlackJack() {
        return getCardsNumberSum() == MAX_CARD_VALUE && getCardsSize() == INIT_DISTRIBUTION_COUNT;
    }

    abstract boolean canDraw();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return name.equals(gamer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
