package model.deck;

import java.util.List;
import model.card.Card;

public interface DeckCreateStrategy {

    List<Card> createAllCards();
}
