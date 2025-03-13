package strategy;

import game.Card;
import game.Cards;
import constant.TrumpEmblem;
import constant.TrumpNumber;

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
