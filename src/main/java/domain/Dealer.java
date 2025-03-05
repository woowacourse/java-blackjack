package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

public class Dealer {
    private final CardHand cardHand; // TODO 공통 로직으로 이동
    private final EnumMap<GameResult, Integer> gameResult = new EnumMap<>(GameResult.class);
    private final Deck deck;

    {
        Arrays.stream(GameResult.values())
                .forEach(result -> gameResult.put(result, 0));
    }

    public Dealer() {
        deck = new Deck();
        cardHand = getInitialDeal();
    }

    public Dealer(CardHand cardHand) { // TODO 리팩터링
        this.cardHand = cardHand;
        this.deck = new Deck();
    }

    public CardHand getInitialDeal() {
        final CardHand cardHand;
        Card firstCard = deck.random(new RandomNumberGenerator());
        Card secondCard = deck.random(new RandomNumberGenerator());
        cardHand = new CardHand(Set.of(firstCard, secondCard));
        return cardHand;
    }

    public int calculateScore() {
        return cardHand.calculateScore();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackJack() {
        return cardHand.isBlackJack();
    }

    public boolean isHit() {
        return cardHand.isDealerHit();
    }

    public void recordGameResult(GameResult result) {
        gameResult.put(result, gameResult.get(result) + 1);
    }

    public int getGameResultCount(GameResult result) {
        return gameResult.get(result);
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public Card pickCard() {
        return deck.random(new RandomNumberGenerator());
    }
}
