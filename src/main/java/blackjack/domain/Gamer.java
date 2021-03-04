package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static blackjack.domain.Player.THRESHOLD_OF_BURST;

public abstract class Gamer {
    public static final String COMMA_DELIMITER = ",";
    private static final int COUNT_OF_DEALER_OPENING_CARDS = 1;
    private static final int MAXIMUM_TO_ACE_IS_ELEVEN = 11;
    private static final int MAKING_ACE_ELEVEN = 10;
    private static final String COMMA_DELIMITER_TO_PRINT = ", ";
    private static final String ERROR_MESSAGE_WITH_SPACE = "이름에 공백이 포함됩니다.";
    private static final String SPACE = " ";

    private final String name;
    private final List<Card> cards = new ArrayList<>();//TODO: cards 분리

    protected Gamer(String name) {
        validateSpace(name);
        this.name = name;
    }

    private void validateSpace(String name) {
        if (name.contains(SPACE)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_WITH_SPACE);
        }
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    protected int calculateJudgingPoint() {
        int point = 0;
        for (Card card : cards) {
            point = card.addPoint(point);
        }
        return point;
    }

    public int calculateMaximumPoint() {
        int point = 0;
        boolean havingAce = false;
        for (Card card : cards) {
            point = card.addPoint(point);
            if (card.isAce()) {
                havingAce = true;
            }
        }
        if (point <= MAXIMUM_TO_ACE_IS_ELEVEN && havingAce) {
            point += MAKING_ACE_ELEVEN;
        }
        return point;
    }

    public void receiveOneCard() {
        receiveCard(Deck.dealCard());
    }

    public boolean isBurst() {
        return calculateMaximumPoint() > THRESHOLD_OF_BURST;
    }

    public abstract boolean canReceiveCard();

    public abstract Boolean continueDraw(String draw);

    public String getName() {
        return name;
    }

    public String getCards() {
        return cards.stream()
                .map(Card::getPatternAndNumber)
                .collect(Collectors.joining(COMMA_DELIMITER_TO_PRINT));
    }

    public String getDealerCards() {
        return cards.stream()
                .limit(COUNT_OF_DEALER_OPENING_CARDS)
                .map(Card::getPatternAndNumber)
                .collect(Collectors.joining(COMMA_DELIMITER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return Objects.equals(name, gamer.name) && Objects.equals(cards, gamer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
