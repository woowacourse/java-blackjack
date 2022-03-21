package blackjack.utils;

import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.LinkedList;
import java.util.List;

public class ParticipantsCreationUtils {

    private ParticipantsCreationUtils() {
    }

    public static Dealer createDealerWithDenominations(Denomination... denominations) {
        Dealer dealer = new Dealer();
        for (Denomination denomination : denominations) {
            dealer.receiveCard(new Card(denomination, HEART));
        }
        return dealer;
    }

    public static Player createPlayerWithDenominations(String name, Denomination... denominations) {
        Player player = new Player(name);
        for (Denomination denomination : denominations) {
            player.receiveCard(new Card(denomination, CLOVER));
        }
        return player;
    }


    public static PlayerBuilder playerBuilder() {
        return new PlayerBuilder();
    }

    public static class PlayerBuilder {

        private String name = "default empty name";
        private Denomination[] denominations;
        private List<Card> cards = new LinkedList<>();
        private int bettingAmount;

        public PlayerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PlayerBuilder denominations(Denomination... denominations) {
            for (Denomination denomination : denominations) {
                cards.add(new Card(denomination, SPADE));
            }
            return this;
        }

        public PlayerBuilder bettingAmount(int bettingAmount) {
            this.bettingAmount = bettingAmount;
            return this;
        }

        public Player build() {
            Player player = new Player(name);
            for (Card card : cards) {
                player.receiveCard(card);
            }
            player.placeBet(bettingAmount);
            return player;
        }
    }
}
