package blackjack.dto;

import blackjack.domain.card.Card;

public class CardInfo {
    private final String shape;
    private final String letter;
    private final boolean isOpen;

    public CardInfo(Card card) {
        this.shape = OutputShape.match(card.getShape());
        this.letter = OutputLetter.match(card.getLetter());
        this.isOpen = card.isOpen();
    }

    public String getShape() {
        return shape;
    }

    public String getLetter() {
        return letter;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
