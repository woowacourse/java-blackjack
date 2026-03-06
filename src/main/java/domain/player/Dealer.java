package domain.player;

public class Dealer extends Player {

    public Dealer(String name) {
        super(name);
    }

    public boolean isTotalValue16OrLess() {
        return getTotalValue() <= 16;
    }

    public String getFirstCardStatus() {
        return this.cardStatus.getFirstCardInfo();
    }
}
