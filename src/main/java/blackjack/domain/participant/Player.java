package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player extends Participant {

    private static final int HIT_STANDARD = 21;
    private static final int FIRST_OPEN_CARD_SIZE = 2;

    private final BetMoney betMoney;

    public Player(Name name, Cards cards, BetMoney betMoney) {
        super(name, cards);
        this.betMoney = betMoney;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }

    @Override
    public boolean isHittable() {
        return cards.isLessScoreThan(HIT_STANDARD);
    }

    @Override
    public List<Card> showFirstCards() {
        return cards.getFrontCards(FIRST_OPEN_CARD_SIZE);
    }
}
