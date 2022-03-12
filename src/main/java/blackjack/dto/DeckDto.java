package blackjack.dto;

import blackjack.model.card.Cards;
import java.util.List;
import java.util.stream.Collectors;

public class DeckDto {
    private final List<CardDto> cards;
    private final int totalScore;

    public DeckDto(Cards cards) {
        this.cards = createCardsDto(cards);
        this.totalScore = cards.sumScore();
    }

    private List<CardDto> createCardsDto(Cards cards) {
        return cards.getCards().stream()
                .map(CardDto::new)
                .collect(Collectors.toList());
    }

    public List<CardDto> getCardsDto() {
        return cards;
    }

    public CardDto getCardDto() {
        return cards.get(0);
    }

    public int getCardCount() {
        return cards.size();
    }

    public int getTotalScore(){
        return totalScore;
    }
}
