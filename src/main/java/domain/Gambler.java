package domain;

import java.util.List;

public interface Gambler {

    void pickCard();

    void initialPick();

    String getName();

    List<Card> getCards();

    int getScore();

    boolean isBustedGambler();
}
