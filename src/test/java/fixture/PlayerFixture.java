package fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.hand.Hand;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;

public class PlayerFixture {

    public static Player createPobi() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.TWO, CardSuit.HEART));
        cards.add(new Card(CardRank.EIGHT, CardSuit.SPADE));
        cards.add(new Card(CardRank.ACE, CardSuit.CLUB));
        Hand hand = new Hand(cards);
        return new Player(new PlayerName("pobi"), hand);
    }

    public static Player createJason() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.SEVEN, CardSuit.CLUB));
        cards.add(new Card(CardRank.KING, CardSuit.SPADE));
        Hand hand = new Hand(cards);
        return new Player(new PlayerName("jason"), hand);
    }
}
