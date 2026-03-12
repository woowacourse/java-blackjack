package domain;

import java.util.Deque;

public interface CardCreationStrategy {
    Deque<Card> create();
}
