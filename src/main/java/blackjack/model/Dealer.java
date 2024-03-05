package blackjack.model;

public class Dealer extends Player {
    public Dealer() {
        super("딜러");
    }

    public boolean canReceive() {
        if (cards.stream()
                .mapToInt(card -> card.getScore())
                .sum() <= 16) {
            return true;
        }
        return false;
    }
}
