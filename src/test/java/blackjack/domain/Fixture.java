package blackjack.domain;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import blackjack.domain.participant.human.Player;
import blackjack.domain.participant.human.name.Name;

public class Fixture {
    public final Player POBI = new Player(new Name("pobi")).initBetting(10000);
    public final Player JASON = new Player(new Name("jason"));
    public final Player HUNCH = new Player(new Name("hunch"));

    public final Card ACE = Card.of(Denomination.ACE, Suit.CLOVER);
    public final Card NINE = Card.of(Denomination.NINE, Suit.CLOVER);
    public final Card TEN = Card.of(Denomination.TEN, Suit.CLOVER);
    public final Card EIGHT = Card.of(Denomination.EIGHT, Suit.CLOVER);
    public final Card TWO =  Card.of(Denomination.TWO, Suit.CLOVER);

}
