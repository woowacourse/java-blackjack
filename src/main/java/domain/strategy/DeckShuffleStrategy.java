package domain.strategy;

import domain.Card;
import domain.Cards;
import domain.constant.TrumpEmblem;
import domain.constant.TrumpNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeckShuffleStrategy implements DeckSettingStrategy {
    @Override
    public Cards initialize() {
        List<Card> cards = Arrays.stream(TrumpNumber.values())
                .flatMap(number -> Arrays.stream(TrumpEmblem.values())
                        .map(emblem -> new Card(number, emblem)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Cards(cards);
    }
}
