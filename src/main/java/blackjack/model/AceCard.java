package blackjack.model;

public class AceCard extends Card {

    public AceCard(CardShape shape) {
        super(shape);
    }


    @Override
    public int getPoint() {
        return 1;
    }
}
