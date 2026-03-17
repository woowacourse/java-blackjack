package domain.participant;


import domain.hand.Hand;

public class Player extends Participant {
    private final PlayerInfo playerInfo;

    private Player(PlayerInfo playerInfo, Hand hand) {
        super(hand);
        this.playerInfo = playerInfo;
    }

    public static Player of(PlayerInfo playerInfo, Hand hand) {
        return new Player(playerInfo, hand);
    }

    public Name getName() {
        return playerInfo.name();
    }

    public String getNameValue() {
        return playerInfo.name().getName();
    }

    public BettingMoney getBettingMoney() {
        return playerInfo.bettingMoney();
    }
}
