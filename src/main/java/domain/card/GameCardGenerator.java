package domain.card;

import java.util.ArrayList;
import java.util.List;

public class GameCardGenerator implements CardGenerator{

    @Override
    public List<Card> generate() {
        List<Card> blackjackGameCards = new ArrayList<>();
        for (CardDenomination emblem : CardDenomination.values()) {
            for (CardEmblem denomination : CardEmblem.values()) {
                Card card = Card.of(emblem, denomination);
                blackjackGameCards.add(card);
            }
        }
        // TODO 셔플 추가
        return blackjackGameCards;
    }

}
