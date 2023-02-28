package domain;

import java.util.List;

@FunctionalInterface
public interface Player {
    List<Card> displayCards();
}
