package domain;

import java.util.List;

public interface CardProvider {

    List<Card> provideCards(int size);
}
