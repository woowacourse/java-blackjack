package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore().isDealerReceivable();
    }

    public boolean isWinner(Player player) {
        return calculateBestScore().isBiggerThan(player.calculateBestScore()) && !isBusted();
    }

    public boolean hasSameScore(Player player) {
        return calculateBestScore().equals(player.calculateBestScore());
    }
}
