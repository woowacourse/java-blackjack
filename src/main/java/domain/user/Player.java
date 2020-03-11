package domain.user;

import java.util.stream.Collectors;

import domain.YesOrNo;
import domain.deck.Card;
import domain.deck.Deck;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public String getFirstDrawResult() {
        return name + "카드: "
                + cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public void additionalDraw(Deck deck, YesOrNo answer) {
        if(answer.isYes()) {
            optionalDraw(deck);
        }
    }
}
