package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardMachine {

    private final ArrayDeque<Card> cards;

    public CardMachine() {
        List<Card> tmp = new ArrayList<>();
        for (CardType type : CardType.values()) {
            for (CardNumber number : CardNumber.values()) {
                tmp.add(new Card(type, number));
            }
        }
        Collections.shuffle(tmp);
        this.cards = new ArrayDeque<>(tmp);
    }

    public Card pickRandomCard() {
        return cards.pop();
    }
}
