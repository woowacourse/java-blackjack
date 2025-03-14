package blackjack.model.blackjack_player.player;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.blackjack_player.player.betting.Betting;
import blackjack.model.card.BlackJackCards;

public final class Player {

    private static final int DRAWABLE_POINT = 21;

    private final String name;
    private final Betting betting;
    private final Hand hand;

    public Player(final String name, final int bettingMoney) {
        this.name = name;
        this.betting = Betting.bet(bettingMoney);
        this.hand = Hand.empty();
    }

    public boolean canReceiveMoreCard() {
        return getOptimalPoint() < DRAWABLE_POINT;
    }

    public int getBettingMoney() {
        return betting.getBettingMoney();
    }

    public void earnMoney(final int money) {
        betting.earn(money);
    }

    public void loseMoney() {
        betting.lose();
    }

    public int getProfit() {
        return betting.getBalance() - betting.getBettingMoney();
    }

    public int getOptimalPoint() {
        return hand.calculateOptimalPoint();
    }

    public int getMinimumPoint() {
        return hand.getMinimumPoint();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public void receiveCards(final BlackJackCards blackJackCards) {
        if (!canReceiveMoreCard()) {
            throw new IllegalStateException("카드를 더 받을 수 없습니다.");
        }
        this.hand.addCards(blackJackCards);
    }

    public String getName() {
        return name;
    }

    public BlackJackCards getCards() {
        return hand.getCards();
    }
}
