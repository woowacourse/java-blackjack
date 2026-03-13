package utils.generator;

import java.util.List;

import domain.Card;
import domain.Cards;

public interface CardsGenerator {
    List<Card> generateShuffledCards();
}
