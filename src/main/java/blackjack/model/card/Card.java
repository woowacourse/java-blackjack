package blackjack.model.card;

import java.util.List;

public record Card(CardType cardType, CardNumber cardNumber) {

    public List<Integer> getPoints() {
        return cardNumber.getPoints();
    }

}
