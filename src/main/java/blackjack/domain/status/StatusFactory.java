package blackjack.domain.status;

import blackjack.domain.card.Card;

import java.util.Arrays;

public class StatusFactory {
    public static Status draw(Card... cards) {
        double countAce = Arrays.stream(cards).filter(Card::isAce).count();
        int sum = Arrays.stream(cards).mapToInt(Card::getValue).sum();
        for (int count = 0; count < countAce; count++) {
            if (sum > 21) {
                sum -= 10;
            }
        }
        if (sum == 21)
            return new Blackjack(cards);
        if (sum < 21)
            return new Hit(cards);
        return new Bust();
    }
}
