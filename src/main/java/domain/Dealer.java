package domain;

import domain.player.Name;
import java.util.List;

public class Dealer extends Participant {
    private final static String DEFAULT_NAME = "딜러";

    public Dealer() {
        super(new Name(DEFAULT_NAME));
    }

    public CardDto startingHandInfo() {
        List<Card> card = List.of(handInfo().cards().getFirst());
        return new CardDto(card);
    }
}
