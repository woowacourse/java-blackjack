package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardsDto {

    private final List<CardInfo> cards;
    private final int totalScore;

    public CardsDto(List<Card> cards, int totalScore) {
        this.totalScore = totalScore;
        this.cards = cards.stream()
                .map(CardInfo::new)
                .collect(Collectors.toList());
    }

    public List<CardInfo> getCards() {
        return cards.stream()
                .filter(CardInfo::isOpen)
                .collect(Collectors.toList());
    }

    public int getTotalScore() {
        return totalScore;
    }
}
