package blackjack.domain.card;

public class CardDeckIndex {

    private int index;

    public CardDeckIndex() {
        index = 0;
    }

    public int getAndIncrease() {
        return index++;
    }
}
