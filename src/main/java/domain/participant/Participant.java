package domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String getName() {
        return name.getRawName();
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
