package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.view.DenominationView;
import blackjack.view.SuitView;

public class CardDto {

    private final String denomination;
    private final String suit;

    private CardDto(String denomination, String suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static CardDto from(Card card) {
        String denomination = DenominationView.getName(card.getDenomination());
        String suit = SuitView.getName(card.getSuit());
        return new CardDto(denomination, suit);
    }

    public String getDenomination() {
        return denomination;
    }

    public String getSuit() {
        return suit;
    }
}
