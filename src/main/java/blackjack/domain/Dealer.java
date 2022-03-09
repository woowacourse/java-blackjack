package blackjack.domain;

public class Dealer extends Person{

    private static final int CONDITION_HIT = 16;
    private Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public Card handOutCard(NumberGenerator numberGenerator) {
        return cards.pickCard(numberGenerator);
    }

    public boolean isHit() {
        return (score() <= CONDITION_HIT);
    }

    public int isWin(Player player) {
        if (this.score() > player.score()) {
            return 1;
        }
        if (this.score() < player.score()) {
            return -1;
        }
        return 0;
    }
}
