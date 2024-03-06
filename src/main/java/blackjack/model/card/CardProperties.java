package blackjack.model.card;

import java.util.Objects;

public class CardProperties {

    private final CardPattern cardPattern;
    private final CardNumber cardNumber;

    public CardProperties(CardPattern cardPattern, CardNumber cardNumber) {
        this.cardPattern = cardPattern;
        this.cardNumber = cardNumber;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardPattern getCardPattern() {
        return cardPattern;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CardProperties that = (CardProperties) object;
        return cardPattern == that.cardPattern && cardNumber == that.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardPattern, cardNumber);
    }
}
