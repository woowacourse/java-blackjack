package blackjack.domain;

public class Dealer extends Participant {

    private final String name = "딜러";

    public boolean checkHitRule() {
        return super.getScore() <= 16;
    }

    public String getName() {
        return name;
    }

    public Card getFirstCard() {
        return super.getCards().get(0);
    }

}
