package blackjack.dto;

import blackjack.domain.Cards;
import java.util.List;
import java.util.stream.Collectors;

public class CardsDto {

    private final List<CardDto> value;
    private final int totalScore;

    public CardsDto(List<CardDto> cards, int totalScore) {
        this.value = cards;
        this.totalScore = totalScore;
    }

    public static CardsDto from(Cards cards) {
        List<CardDto> cardDtos = cards.getValue().stream()
                .map(CardDto::from)
                .collect(Collectors.toList());
        int totalScore = cards.calculateTotalScore();
        return new CardsDto(cardDtos, totalScore);
    }

    public List<CardDto> getValue() {
        return value;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
