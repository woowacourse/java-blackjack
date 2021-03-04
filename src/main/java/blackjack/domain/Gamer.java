package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Gamer {

    private static final String SPACE = " ";
    private static final String ERROR_NAME_SPACE = "이름에 공백이 포함됩니다.";
    private static final int ACE_BONUS = 10;
    private static final int AVAILABLE_ACE_BONUS = 11;
    private static final String COMMA = ", ";
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    protected Gamer(String name) {
        validateSpace(name);
        this.name = name;
    }

    private static void validateSpace(String name) {
        if (name.contains(SPACE)) {
            throw new IllegalArgumentException(ERROR_NAME_SPACE);
        }
    }

    protected void receiveCard(Card card) {
        cards.add(card);
    }

    protected int calculateJudgingPoint() {
        return cards.stream().map(Card::givePoint).reduce(0, Integer::sum);
    }

    public int calculateMaximumPoint() {
        int point = 0;
        boolean havingAce = false;
        for (Card card : cards) {
            point += card.givePoint();
            if (card.isAce()) {
                havingAce = true;
            }
        }
        if (point <= AVAILABLE_ACE_BONUS && havingAce) {
            point += ACE_BONUS;
        }
        return point;
    }

    public abstract boolean canReceiveCard();

    public abstract Boolean continueDraw(Deck deck);

    public String getName() {
        return name;
    }

    public String getCards() {
        return cards.stream().map(Card::getPatternAndNumber).collect(Collectors.joining(COMMA));
    }

    public String getDealerCards() {
        List<Card> dealerCards = new ArrayList<>();
        for (int i = 0; i < cards.size() - 1; i++) {
            dealerCards.add(cards.get(i));
        }
        return dealerCards.stream().map(Card::getPatternAndNumber)
            .collect(Collectors.joining(COMMA));
    }

    public abstract String getInfo();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Gamer gamer = (Gamer) o;
        return Objects.equals(name, gamer.name) && Objects.equals(cards, gamer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

}
