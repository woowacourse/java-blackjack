package participant;

public class Player extends Participant {

    private static final int BLACKJACK_NUMBER = 21;

    private final Wallet wallet;

    public Player(String nickname, int bettingMoney) {
        super(nickname);
        this.wallet = Wallet.of(bettingMoney);
    }

    @Override
    public boolean canReceiveCard() {
        return score() < BLACKJACK_NUMBER;
    }

    public void updateMoney(double rate) {
        wallet.updateMoney(rate);
    }

    public double calculateProfit() {
        return wallet.calculateProfit();
    }
}
