package domain.participant;

import domain.amount.EarnAmount;
import domain.GameResult;
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

    public Map<GameResult, Integer> getDealerResult(final Players players) {
        Map<GameResult, Integer> dealerResult = new EnumMap<>(GameResult.class);

        for (GameResult value : players.getPlayersResult(this).values()) {
            GameResult reversed = value.reverse();
            dealerResult.put(reversed, dealerResult.getOrDefault(reversed, 0) + 1);
        }
        return dealerResult;
    }

    public EarnAmount calculateRevenue(final Map<Player, EarnAmount> finalResult) {
        long dealerAmount = finalResult.values()
                .stream()
                .map(EarnAmount::getValue)
                .mapToLong(Long::longValue)
                .sum();
        return new EarnAmount(-dealerAmount);
    }

    @Override
    public boolean canDeal() {
        return handsSum() <= THRESHOLD;
    }
}
