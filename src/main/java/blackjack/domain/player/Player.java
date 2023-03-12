package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Player extends Participant {
    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(String name, String bettingMoney, List<Card> cards) {
        super(cards);
        this.name = new Name(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    @Override
    public boolean isAbleToReceive() {
        return playerStatus.isRunning();
    }

    @Override
    public void hit(Card card) {
        playerStatus = playerStatus.draw(card);
    }

    public String getName() {
        return name.getName();
    }
}
