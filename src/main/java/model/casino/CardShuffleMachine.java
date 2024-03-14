package model.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;
import model.card.Card;
import model.card.Emblem;
import model.card.Number;

public interface CardShuffleMachine {

    default ArrayList<Card> generateAllCards() {
        return Arrays.stream(Emblem.values())
                .flatMap(emblem -> Arrays.stream(Number.values())
                        .map(number -> Card.from(number, emblem)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    Queue<Card> shuffleCardDeck();
}
