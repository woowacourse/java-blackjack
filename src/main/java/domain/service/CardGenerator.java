package domain.service;

import domain.model.Card;

@FunctionalInterface
public interface CardGenerator {

    Card generate();
}
