package domain;

import java.util.Arrays;
import java.util.EnumMap;

public class Dealer extends GameParticipant {
    private final EnumMap<GameResult, Integer> gameResult = new EnumMap<>(GameResult.class);
    private final Deck deck;

    {
        Arrays.stream(GameResult.values())
                .forEach(result -> gameResult.put(result, 0));
    }

    public Dealer(Deck deck, CardHand cardHand) {
        super("딜러", cardHand);
        this.deck = deck;
    }

    public Card pickCard() {
        return deck.drawCard();
    }

    public void recordGameResult(GameResult result) {
        gameResult.put(result, gameResult.get(result) + 1);
    }

    public boolean doesNeedCard() {
        return cardHand.doesDealerNeedCard();
    }

    public CardHand pickInitialDeal() {
        return deck.getInitialDeal();
    }

    public int getGameResultCount(GameResult result) {
        return gameResult.get(result);
    }
}
