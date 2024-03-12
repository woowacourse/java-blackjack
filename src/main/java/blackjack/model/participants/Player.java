package blackjack.model.participants;

import blackjack.view.PlayerResultStatus;

public class Player extends Participant {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean checkCanGetMoreCard() {
        return !cards.isBusted();
    }

    public PlayerResultStatus determineWinner(Dealer dealer) {
        return dealer.getResultStatus(cards);
    }

    public String getName() {
        return name;
    }
}
