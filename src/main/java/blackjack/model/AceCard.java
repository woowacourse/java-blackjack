package blackjack.model;

public class AceCard extends Card {

    public AceCard(CardShape shape) {
        super(shape, 1);
    }


    @Override
    public int getPoint() {
        return 1;
    }
}
