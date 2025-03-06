package blackjack.fixture;

import java.util.Arrays;

import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;

public class CardsFixture {

    public static Cards cardsOf(CardNumber... cardNumbers) {
        Cards cards = new Cards();
        Arrays.stream(cardNumbers).forEach(cardNumber -> cards.add(CardFixture.cardOf(cardNumber)));
        return cards;
    }
}
