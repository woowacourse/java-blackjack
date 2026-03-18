package dto;

import domain.card.Card;
import domain.participant.Name;
import java.util.List;

public record PlayerCardWithScoreDto(String name, List<CardDto> cards, int score) {

    public static PlayerCardWithScoreDto from(Name name, List<Card> cards, int score) {
        return new PlayerCardWithScoreDto(
                name.name(),
                cards.stream()
                        .map(CardDto::from)
                        .toList(),
                score
        );
    }
}
