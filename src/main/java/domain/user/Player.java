package domain.user;

import domain.card.Hand;

public final class Player extends User {
    public Player(UserInformation userInformation, Hand hand) {
        super.userInformation = userInformation;
        super.hand = hand;
    }

    public static Player of(String nameValue, int bettingAmountValue) {
        return new Player(
                UserInformation.from(new PlayerName(nameValue), bettingAmountValue),
                new Hand()
        );
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }
}
