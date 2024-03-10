package domain;

import domain.card.Card;
import java.util.List;

public interface CardsGenerator {

    List<Card> generate();
}
