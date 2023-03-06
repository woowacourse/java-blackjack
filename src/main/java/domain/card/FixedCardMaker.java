package domain.card;

public class FixedCardMaker implements CardMaker {
    private final String cardName;

    public FixedCardMaker(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public String makeCard() {
        return cardName;
    }
}
