package blackjack.dto;

import blackjack.domain.card.Cards;

public class PlayerCardsDto {
    private final String name;
    private final Cards cards;
    public PlayerCardsDto(String name, Cards cards){
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
