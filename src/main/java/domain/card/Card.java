package domain.card;

import java.util.List;

public record Card(Pattern pattern, CardNumber cardNumber) {

    public String formatSingleCard() {
        return formatCardNumber() + this.pattern().getPattern();
    }

    public String formatCardNumber() {
        List<CardNumber> honorCards = CardNumber.getHonorCard();
        if (honorCards.contains(this.cardNumber())) {
            return this.cardNumber().name().substring(0, 1);
        }
        return Integer.toString(this.cardNumber().getNumber());
    }
}
