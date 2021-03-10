package blackjack.domain.participant;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public String getName() {
        return this.name.getValue();
    }

    public void initPlayerHand(Dealer dealer, int receiveCount) {
        for (int i = 0; i < receiveCount; i++) {
            receiveCard(dealer.drawCard());
        }
    }

    @Override
    public boolean isOverLimitScore() {
        return getTotalScore().isBust();
    }
}
