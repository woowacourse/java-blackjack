package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Set;

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

    public CardHand getInitialDeal() {
        final CardHand cardHand;
        Card firstCard = deck.random(new RandomNumberGenerator());
        Card secondCard = deck.random(new RandomNumberGenerator());
        cardHand = new CardHand(Set.of(firstCard, secondCard));
        return cardHand;
    }

    public boolean doesDealerNeedCard() {
        return cardHand.doesDealerNeedCard();
    }

    public void recordGameResult(GameResult result) {
        gameResult.put(result, gameResult.get(result) + 1);
    }

    public int getGameResultCount(GameResult result) {
        return gameResult.get(result);
    }

    public Card pickCard() {
        return deck.random(new RandomNumberGenerator());
    }
}
