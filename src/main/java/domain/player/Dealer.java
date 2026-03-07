package domain.player;

public class Dealer extends Participant {

    public Dealer(String name) {
        super(name);
    }

    public boolean isTotalScore16OrLess() {
        return getTotalScore() <= 16;
    }

    public String getFirstCardStatus() {
        return this.cardStatus.getFirstCardInfo();
    }
}
