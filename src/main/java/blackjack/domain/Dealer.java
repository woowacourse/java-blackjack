package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private final Players players;
    private final Deck deck;
    private final List<Card> cards;
    private final ScoreCalculator scoreCalculator;

    public Dealer(Players players, Deck deck, ScoreCalculator scoreCalculator) {
        this.players = players;
        this.deck = deck;
        this.cards = new ArrayList<>();
        this.scoreCalculator = scoreCalculator;
    }

    public void shuffleDeck(CardsShuffler cardsShuffler) {
        deck.shuffleCards(cardsShuffler);
    }

    public void pickCards() {
        cards.addAll(List.of(deck.draw(), deck.draw()));
    }


    public void handOutCard() {
        players.sendAll((player) -> player.send(deck.draw(), deck.draw()));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void pickAdditionalCard() {
        int maxScore = scoreCalculator.calculateMaxScore(cards);
        while (maxScore <= 16) {
            cards.add(deck.draw());
            maxScore = scoreCalculator.calculateMaxScore(cards);
        }
    }
}
