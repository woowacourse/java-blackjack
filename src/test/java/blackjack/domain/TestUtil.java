package blackjack.domain;

import blackjack.domain.card.CardHand;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;

public class TestUtil {

    public static Player createPlayerFromName(String name) {
        int defaultMoney = 10000;
        CardHand cardHand = new CardHand();
        ParticipantName playerName = new ParticipantName(name);

        return new Player(playerName, cardHand, new BettingMoney(defaultMoney));
    }

    public static Player createPlayerOf(String name, CardHand cardHand) {
        int defaultMoney = 10000;
        ParticipantName playerName = new ParticipantName(name);

        return new Player(playerName, cardHand, new BettingMoney(defaultMoney));
    }

    public static Player createPlayerOf(CardHand cardHand, int bet) {
        ParticipantName playerName = new ParticipantName("player");

        return new Player(playerName, cardHand, new BettingMoney(bet));
    }

}
