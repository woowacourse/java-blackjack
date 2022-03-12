package blackjack.dto;

import blackjack.trumpcard.Deck;
import java.util.List;
import java.util.stream.Collectors;

public class DeckDto {
    private final List<CardDto> cards;
    private final int totalScore;

    public DeckDto(Deck deck) {
        this.cards = createCardsDto(deck);
        this.totalScore = deck.sumScore();
    }

    private List<CardDto> createCardsDto(Deck deck) {
        return deck.getCards().stream()
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
