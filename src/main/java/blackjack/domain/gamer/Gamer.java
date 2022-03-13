package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Gamer {
    public static final int MAX_CARD_VALUE = 21;

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
        return getSumNotToBurst(sum, aces);
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

    private int getSumNotToBurst(int sum, List<Card> aces) {
        for (Card ace : aces) {
            sum += selectAceValue(sum, ace);
        }
        return sum;
    }

    private int selectAceValue(int sum, Card ace) {
        if (ace.isAce() && ace.getValue() + sum > MAX_CARD_VALUE) {
            return CardNumber.LOWER_ACE_VALUE;
        }
        return ace.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public int getCardsSize() {
        return cards.size();
    }

    abstract boolean canDraw();
}
