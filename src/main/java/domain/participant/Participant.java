package domain.participant;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;

public class Participant {
    Name name;
    List<Card> cards;

    public Participant(Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

}
