package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardsDto {

    private final List<CardDto> cards;

    private CardsDto(List<CardDto> cards) {
        this.cards = cards;
    }

    public static CardsDto from(Cards cards) {
        List<CardDto> cardDtos = cards.getCards()
            .stream()
            .map(card -> CardDto.from(card))
            .collect(Collectors.toList());
        return new CardsDto(cardDtos);
    }

    public List<CardDto> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
