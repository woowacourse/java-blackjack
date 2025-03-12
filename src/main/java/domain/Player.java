package domain;

import static domain.GameResultStatus.DRAW;
import static domain.GameResultStatus.LOSE;
import static domain.GameResultStatus.WIN;

import java.util.Objects;

public class Player extends Participant {

    private final String name;

    public Player(String name, Cards cards) {
        super(cards);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean hasBustCards() {
        return cards.isBust();
    }

    public boolean hasNotBustCards() {
        return !cards.isBust();
    }

    public GameResultStatus calculateResultStatus(Cards dealerCards) {
        if (cards.isBust()) {
            return LOSE;
        }
        if (dealerCards.isBust()) {
            return WIN;
        }
        return compareCardsSum(cards, dealerCards);
    }

    private GameResultStatus compareCardsSum(Cards playerCards, Cards dealerCards) {
        if (playerCards.isLargerThan(dealerCards)) {
            return WIN;
        }
        if (dealerCards.isLargerThan(playerCards)) {
            return LOSE;
        }
        return DRAW;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
