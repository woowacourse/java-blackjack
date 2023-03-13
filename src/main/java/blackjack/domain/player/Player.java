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

    public double calculateProfit(Dealer dealer) {
        return playerStatus.calculateProfit(dealer.isBlackJack(), dealer.hand.calculateScore(),
                hand.calculateScore(), bettingMoney);
    }

    public String getName() {
        return name.getName();
    }
}
