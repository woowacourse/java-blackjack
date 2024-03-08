package view.dto;

import java.util.List;

import domain.Card;
import domain.CardNumber;
import domain.Cards;

public class CardsDto {

    private final List<Card> cards;
    private final int score;

    public CardsDto(final Cards cards, final int score) {
        this(cards.toList(), score);
    }

    public CardsDto(final List<Card> cards, final int score) {
        this.cards = cards;
        this.score = score;
    }

    public String parseCards() {
        return cards.stream()
                .map(this::parseCard)
                .reduce((card1, card2) -> card1 + ", " + card2)
                .orElse("");
    }

    private String parseCard(final Card card) {
        var shape = card.shape();
        ;
        CardNumber cardNumber = card.number();
        String number = String.valueOf(cardNumber.value());
        if (cardNumber == CardNumber.ACE) {
            number = "A";
        }
        if (cardNumber == CardNumber.KING) {
            number = "K";
        }
        if (cardNumber == CardNumber.QUEEN) {
            number = "Q";
        }
        if (cardNumber == CardNumber.JACK) {
            number = "J";
        }
        return number + shape.value();
    }

    public int getScore() {
        return score;
    }
}
