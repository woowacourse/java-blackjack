package domain.user;

import domain.card.Hand;

public final class Player extends User {
    public Player(UserInformation userInformation, Hand hand) {
        super.userInformation = userInformation;
        super.hand = hand;
    }

    public static Player of(String nameValue, int bettingMoneyValue) {
        return new Player(
                new UserInformation(new PlayerName(nameValue), new BettingMoney(bettingMoneyValue)),
                new Hand()
        );
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }

    public int getBettingMoneyValue() {
        return this.userInformation.getBettingMoneyValue();
    }
}
