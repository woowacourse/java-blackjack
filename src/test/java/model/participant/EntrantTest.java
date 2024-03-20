package model.participant;

import java.util.List;
import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import org.junit.jupiter.api.BeforeEach;

class EntrantTest {
    Entrant entrant;

    @BeforeEach
    void setUp() {
        // Given
        Card card1 = Card.from(Number.ACE, Emblem.SPADE);
        Card card2 = Card.from(Number.TWO, Emblem.SPADE);
        Names names = new Names(List.of("프람"));
        entrant = generateEntrant(names, card1, card2);

        // When

        entrant.hitDealer(Card.from(Number.THREE, Emblem.SPADE));
        entrant.hitPlayer(Card.from(Number.THREE, Emblem.HEART));
    }

    private Entrant generateEntrant(Names names, Card card1, Card card2) {
        List<Player> players = names.getPlayerNames()
                .stream()
                .map(name -> new Player(name, 0, card1, card2))
                .toList();
        Dealer dealer = new Dealer(card1, card2);
        return new Entrant(dealer, players);
    }
}
