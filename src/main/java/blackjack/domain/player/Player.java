package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Player extends Participant {
    private static final int MAX_SCORE_TO_RECEIVE = 21;

    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(String name, String bettingMoney, List<Card> cards) {
        super(cards);
        this.name = new Name(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    @Override
    public boolean isAbleToReceive() {
        return hand.calculateScore().isNotOver(MAX_SCORE_TO_RECEIVE);
    }

    public String getName() {
        return name.getName();
    }
}
