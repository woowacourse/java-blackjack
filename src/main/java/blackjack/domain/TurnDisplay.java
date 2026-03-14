package blackjack.domain;

import java.util.List;

@FunctionalInterface
public interface TurnDisplay {
    void show(String name, List<String> cardNames);
}
