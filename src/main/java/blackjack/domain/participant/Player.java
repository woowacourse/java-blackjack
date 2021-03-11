package blackjack.domain.participant;

import static blackjack.domain.card.Cards.BLACKJACK_SCORE;

public class Player extends Participant {
    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public boolean isNotBust() {
        return cards.calculateScore() <= BLACKJACK_SCORE;
    }

    @Override
    public String getName() {
        return this.name.getName();
    }

    @Override
    public String showCardsAtFirst() {
        return getCards();
    }
}
