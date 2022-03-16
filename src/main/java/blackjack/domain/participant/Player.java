package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {
    private final BetMoney betMoney;

    public Player(String name, BetMoney betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    public void receiveCards(List<Card> cards) {
        cards.forEach(holdingCard::add);
    }

    @Override
    public boolean isFinished() {
        return holdingCard.isBust();
    }
}
