package blackjack.domain;

import blackjack.domain.card.CardHand;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;
import blackjack.state.Blackjack;
import blackjack.state.Hit;
import blackjack.state.Stand;
import blackjack.state.Start;

public class TestUtil {

    public static Player createPlayerFromName(String name) {
        int defaultMoney = 10000;
        CardHand cardHand = new CardHand();
        ParticipantName playerName = new ParticipantName(name);

        return new Player(new Start(cardHand), playerName, new BettingMoney(defaultMoney));
    }

    public static Player createStartPlayerOf(String name, CardHand cardHand) {
        int defaultMoney = 10000;
        ParticipantName playerName = new ParticipantName(name);

        return new Player(new Start(cardHand), playerName, new BettingMoney(defaultMoney));
    }

    public static Player createStartPlayerOf(CardHand cardHand, int bet) {
        ParticipantName playerName = new ParticipantName("player");

        return new Player(new Start(cardHand), playerName, new BettingMoney(bet));
    }

    public static Player createStandPlayerOf(CardHand cardHand, int bet) {
        ParticipantName playerName = new ParticipantName("player");

        return new Player(new Stand(cardHand), playerName, new BettingMoney(bet));
    }

    public static Player createBlackjackPlayerWithBet(int bet) {
        ParticipantName playerName = new ParticipantName("player");

        return new Player(new Blackjack(new CardHand()), playerName, new BettingMoney(bet));
    }

    public static Player createStandPlayerOf(CardHand cardHand) {
        int defaultMoney = 10000;
        ParticipantName playerName = new ParticipantName("player");
        return new Player(new Stand(cardHand), playerName, new BettingMoney(defaultMoney));
    }

    public static Player createHitPlayerOf(String player, CardHand cardHand) {
        int defaultMoney = 10000;
        return new Player(new Hit(cardHand), new ParticipantName("player"), new BettingMoney(defaultMoney));
    }
}
