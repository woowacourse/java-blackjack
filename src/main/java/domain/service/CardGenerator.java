package domain.service;

import domain.model.Card;
import java.util.List;

public interface CardGenerator {

    Card generate();

    List<Card> generate(int size);
}
