package util;

import static model.card.Rank.ACE;
import static model.card.Rank.JACK;
import static model.card.Rank.KING;
import static model.card.Rank.NINE;
import static model.card.Rank.QUEEN;
import static model.card.Suit.SPADE;

import model.card.Card;
import model.participant.Dealer;
import model.participant.Player;

public final class ParticipantProvider {
    private static final String PLAYER_NAME = "player";
    private static final int BETTING_AMOUNT = 1000;

    private ParticipantProvider() {
    }

    public static Player createPlayerWithBlackjack() {
        Player player = Player.of(PLAYER_NAME, BETTING_AMOUNT);
        player.receive(Card.of(SPADE, ACE));
        player.receive(Card.of(SPADE, JACK));

        return player;
    }

    public static Player createPlayerWithBust() {
        Player player = Player.of(PLAYER_NAME, BETTING_AMOUNT);
        player.receive(Card.of(SPADE, KING));
        player.receive(Card.of(SPADE, QUEEN));
        player.receive(Card.of(SPADE, JACK));

        return player;
    }

    public static Player createPlayerWithScore21() {
        Player player = Player.of(PLAYER_NAME, BETTING_AMOUNT);
        player.receive(Card.of(SPADE, ACE));
        player.receive(Card.of(SPADE, JACK));
        player.receive(Card.of(SPADE, QUEEN));

        return player;
    }

    public static Player createPlayerWithScore20() {
        Player player = Player.of(PLAYER_NAME, BETTING_AMOUNT);
        player.receive(Card.of(SPADE, JACK));
        player.receive(Card.of(SPADE, QUEEN));

        return player;
    }

    public static Player createPlayerWithScore19() {
        Player player = Player.of(PLAYER_NAME, BETTING_AMOUNT);
        player.receive(Card.of(SPADE, JACK));
        player.receive(Card.of(SPADE, NINE));

        return player;
    }

    public static Dealer createDealerWithBlackjack() {
        Dealer dealer = Dealer.create();
        dealer.receive(Card.of(SPADE, ACE));
        dealer.receive(Card.of(SPADE, JACK));

        return dealer;
    }

    public static Dealer createDealerWithBust() {
        Dealer dealer = Dealer.create();
        dealer.receive(Card.of(SPADE, JACK));
        dealer.receive(Card.of(SPADE, QUEEN));
        dealer.receive(Card.of(SPADE, KING));

        return dealer;
    }

    public static Dealer createDealerWithScore20() {
        Dealer dealer = Dealer.create();
        dealer.receive(Card.of(SPADE, JACK));
        dealer.receive(Card.of(SPADE, QUEEN));

        return dealer;
    }
}
