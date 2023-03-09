package blackjack.view.dto;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardsDto {

    private final List<CardInfo> cards;

    public CardsDto(List<Card> cards){
        this.cards = cards.stream().map(CardInfo::new).collect(Collectors.toList());
    }
    public List<CardInfo> getCards() {
        return cards;
    }
}
