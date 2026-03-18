package dto;

import domain.card.Card;
import domain.participant.Name;
import java.util.List;

public record PlayerCardDto(String name, List<CardDto> cards) {

    public static PlayerCardDto from(Name name, List<Card> cards) {
        return new PlayerCardDto(
                name.name(),
                cards.stream()
                        .map(CardDto::from)
                        .toList());
    }
}
