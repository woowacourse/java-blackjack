package view.dto.card;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.Cards;

import java.util.List;
import java.util.stream.Collectors;

import static view.ResultView.SPACING;

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
                    .collect(Collectors.joining(SPACING));
    }

    private String parseCard(final Card card) {
        var shape = card.shape();
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
