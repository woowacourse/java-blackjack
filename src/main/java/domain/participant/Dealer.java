package domain.participant;

import domain.Answer;
import domain.CardDeck;
import domain.Hands;
import domain.result.Result;
import java.util.EnumMap;
import java.util.Map;

public class Dealer extends Participant {

    public static final int INIT_HANDS_SIZE = 2;
    public static final int MIN_HANDS_SUM = 16;
    public static final String NAME = "딜러";

    private final CardDeck cardDeck;

    public Dealer(final CardDeck cardDeck) {
        super(new Name(NAME), Hands.createEmptyHands());
        this.cardDeck = cardDeck;
    }

    public Dealer(final CardDeck cardDeck, final Hands hands) {
        super(new Name(NAME), hands);
        this.cardDeck = cardDeck;
    }

    public void initHands(final Players players) {
        for (int i = 0; i < INIT_HANDS_SIZE; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
            super.add(cardDeck.pop());
        }
    }

    public void deal(final Player player, final Answer answer) {
        if (Answer.HIT.equals(answer)) {
            player.add(cardDeck.pop());
        }
    }

    public void deal() {
        while (handsSum() <= MIN_HANDS_SUM) {
            super.add(cardDeck.pop());
        }
    }

    public int countAddedHands() {
        return handsSize() - INIT_HANDS_SIZE;
    }

    public Map<Result, Integer> getDealerResult(final Players players) {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);

        for (Result value : players.getPlayersResult(this).values()) {
            Result reversed = value.reverse();
            dealerResult.put(reversed, dealerResult.getOrDefault(reversed, 0) + 1);
        }
        return dealerResult;
    }
}
