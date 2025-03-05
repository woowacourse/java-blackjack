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

    public void prepareBlackjack(CardsShuffler cardsShuffler) {
        shuffleDeck(cardsShuffler);
        pickCards();
        handOutCard();
    }

    private void shuffleDeck(CardsShuffler cardsShuffler) {
        deck.shuffleCards(cardsShuffler);
    }

    private void pickCards() {
        cards.addAll(List.of(deck.draw(), deck.draw()));
    }


    private void handOutCard() {
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

    public void sendCardToPlayer(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException("해당 플레이어는 존재하지 않습니다.");
        }
        player.send(deck.draw());
    }
}
