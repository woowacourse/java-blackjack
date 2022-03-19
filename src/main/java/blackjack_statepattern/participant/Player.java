package blackjack_statepattern.participant;

public final class Player extends Participant {
    private final int betMoney;

    public Player(String name, int betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    public int getBetMoney() {
        return betMoney;
    }

}
