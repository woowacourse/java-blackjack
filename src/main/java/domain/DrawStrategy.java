package domain;

import domain.vo.Card;

@FunctionalInterface
public interface DrawStrategy {
    Card draw();
}
