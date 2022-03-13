package model.cardGettable;

import java.util.List;
import java.util.stream.Collectors;
import model.card.Card;
import model.card.Cards;

public class FirstCardsGettable implements CardsGettable {

    @Override
    public List<Card> getCards(Cards cards) {
        return cards.getFirstCard()
                .stream()
                .collect(Collectors.toList());
    }
}
