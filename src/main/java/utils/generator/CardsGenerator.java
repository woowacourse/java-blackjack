package utils.generator;

import java.util.List;

import domain.Card;

public interface CardsGenerator {
    List<Card> generateShuffledCards();
}
