package domain.participant;

import domain.Answer;
import domain.Result;
import domain.card.Card;
import domain.card.CardDeck;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    public static final int INIT_HANDS_SIZE = 2;
    public static final int THRESHOLD = 16;
    public static final int DECK_SIZE = 6;
    public static final Name DEALER_NAME = new Name("딜러");

    private final CardDeck cardDeck;

    public Dealer() {
        super(DEALER_NAME, Hands.createEmptyHands());
        this.cardDeck = new CardDeck(generate());
    }

    public Dealer(final Hands hands) {
        super(DEALER_NAME, hands);
        this.cardDeck = new CardDeck(generate());
    }

    private static ArrayDeque<Card> generate() {
        final List<Card> deck = new ArrayList<>();
        for (int i = 0; i < DECK_SIZE; i++) {
            deck.addAll(Card.values());
        }
        Collections.shuffle(deck);
        return new ArrayDeque<>(deck);
    }

    public void initHands(final Players players) {
        for (int i = 0; i < INIT_HANDS_SIZE; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
            super.add(cardDeck.pop());
        }
    }

    public void deal(final Player player, final Answer answer) {
        if (answer.isHit()) {
            player.add(cardDeck.pop());
        }
    }

    public void deal() {
        while (canDeal()) {
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

    @Override
    public boolean canDeal() {
        return handsSum() <= THRESHOLD;
    }
}
