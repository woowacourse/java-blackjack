package model.result;

import java.util.List;

public record CardsDto(CardDto dealerCard, List<CardDto> playerCards) {

    public String dealerName() {
        return dealerCard.name();
    }

    public List<String> playerNames() {
        return playerCards.stream()
            .map(CardDto::name)
            .toList();
    }
}
