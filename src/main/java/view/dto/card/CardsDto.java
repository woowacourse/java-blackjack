package view.dto.card;

import domain.card.Cards;
import domain.card.Hand;

import java.util.List;

public record CardsDto(List<CardDto> cards, int score) {

    public CardsDto(final Cards cards, final int score) {
        this(convertToCardDtos(cards), score);
    }

    public CardsDto(final Hand hand, final int score){
        this(convertToCardDtos(hand.getCards()), score);
    }

    private static List<CardDto> convertToCardDtos(final Cards cards) {
        return cards.toList()
                    .stream()
                    .map(CardDto::new)
                    .toList();
    }
}
