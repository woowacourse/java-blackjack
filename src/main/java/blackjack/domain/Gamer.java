package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Gamer {

    public static final int NUM_INIT_CARD = 2;
    private static final String SPACE = " ";
    private static final String ERROR_NAME_SPACE = "이름에 공백이 포함됩니다.";
    private static final int ACE_BONUS = 10;
    private static final int AVAILABLE_ACE_BONUS = 11;
    public static final int HIGHEST_POINT = 21;
    private static final String COMMA = ", ";
    private final String name;
    protected final List<Card> cards = new ArrayList<>();

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

    public int calcPoint() {
        Integer cardValue = cards.stream()
            .map(Card::givePoint)
            .reduce(0, Integer::sum);
        if (findAce(cards) && cardValue + ACE_BONUS <= HIGHEST_POINT) {
            return cardValue + ACE_BONUS;
        }
        return cardValue;
    }

    public int calculateMaximumPoint() {
        int point = cards.stream()
            .map(Card::givePoint)
            .reduce(0, Integer::sum);

        if (point <= AVAILABLE_ACE_BONUS && findAce(cards)) {
            point += ACE_BONUS;
        }
        return point;
    }

    private boolean findAce(List<Card> cards) {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean canReceiveCard();

    public abstract Boolean continueDraw(Deck deck);

    public String getName() {
        return name;
    }

    public String getCards() {
        return cards.stream()
            .map(Card::getPatternAndNumber)
            .collect(Collectors.joining(COMMA));
    }

    public String getDealerCards() {
        List<Card> dealerCards = new ArrayList<>();
        for (int i = 0; i < cards.size() - 1; i++) {
            dealerCards.add(cards.get(i));
        }
        return dealerCards.stream()
            .map(Card::getPatternAndNumber)
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
