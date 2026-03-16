package domain.fixture;

import domain.*;
import domain.shuffle.NoShuffleStrategy;

import java.util.List;

public class TestFixture {
    public static Player player() {
        List<Card> cards = List.of(
                Card.of(Rank.NINE, Suit.DIAMOND),
                Card.of(Rank.NINE, Suit.CLOVER)
        );
        return Player.of(Cards.of(cards), "포비");
    }

    public static Dealer dealer() {
        Deck deck = Deck.of(new NoShuffleStrategy());
        return Dealer.of(deck.drawInitialHand());
    }
}
