package blackjack.domain;

public class CardDeckIndex {

    private int index;

    public CardDeckIndex() {
        index = 0;
    }

    public int getAndIncrease() {
        return index++;
    }
}
