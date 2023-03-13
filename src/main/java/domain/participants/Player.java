package domain.participants;

import domain.BettingMoney;
import controller.Command;

public class Player extends Participant {
    private static final int BLACK_JACK = 21;

    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney money) {
        super(new Name(name));
        this.bettingMoney = money;
    }

    public int getMoney() {
        return bettingMoney.getBettingMoney();
    }

    public boolean isOverPlayerBlackJack() {
        return getCardsSum() >= BLACK_JACK;
    }

    public boolean canDrawCard(Command command) {
        return !isOverPlayerBlackJack() && isCommandYes(command);
    }

    public boolean isCommandYes(Command command) {
        return command.equals(Command.YES);
    }

}

