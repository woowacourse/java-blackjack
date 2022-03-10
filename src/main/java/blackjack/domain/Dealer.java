package blackjack.domain;

public class Dealer extends Person{

    private static final int CONDITION_HIT = 16;
    private final Cards cards;

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
        return Integer.compare(this.score(), player.score());
    }
}
