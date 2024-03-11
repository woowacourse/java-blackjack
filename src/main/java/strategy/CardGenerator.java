package strategy;

import domain.card.Card;

import java.util.List;

public interface CardGenerator {

    List<Card> generate();
}
