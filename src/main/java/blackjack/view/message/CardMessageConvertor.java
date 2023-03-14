package blackjack.view.message;

import blackjack.domain.card.Card;

public class CardMessageConvertor {

    public static String convert(Card card) {
        return CardNumberMessage.from(card.getNumber()).getMessage() +
                CardSuitMessage.from(card.getSuit()).getMessage();
    }
}
