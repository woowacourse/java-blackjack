package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

import java.util.List;
import java.util.stream.Collectors;

public class CardsDto {
    private final List<CardDto> cards;
    private final int score;

    private CardsDto(List<Card> cards, int score) {
        this.cards = cards.stream()
                .map(CardDto::new)
                .collect(Collectors.toList());
        this.score = score;
    }

    public static CardsDto from(Cards cards) {
        return new CardsDto(cards.getValues(), cards.sumScore());
    }

    public List<String> getCards() {
        return cards.stream()
                .map(CardDto::getCard)
                .collect(Collectors.toList());
    }

    public int getScore() {
        return score;
    }
}
