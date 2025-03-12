package blackjack.gamer;

public class Player extends Gamer {

    private final String nickName;

    public Player(final String nickName) {
        this.nickName = nickName;
    }

    public void bet(final String bettingAmount) {
        bettingMachine.bet(bettingAmount);
    }

    @Override
    public String getNickName() {
        return nickName;
    }
}
