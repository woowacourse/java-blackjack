package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Dealer {

    private final Players players;
    private final Deck deck;
    private final List<Card> cards;
    private final ScoreCalculator scoreCalculator;

    public Dealer(Players players, Deck deck, ScoreCalculator scoreCalculator) {
        this(players, deck, new ArrayList<>(), scoreCalculator);
    }

    public Dealer(Players players, Deck deck, List<Card> cards, ScoreCalculator scoreCalculator) {
        this.players = players;
        this.deck = deck;
        this.cards = cards;
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

    public int calculateMaxScore() {
        return scoreCalculator.calculateMaxScore(cards);
    }

    public int pickAdditionalCard() {
        int maxScore = scoreCalculator.calculateMaxScore(cards);
        int additionalCardsNumber = 0;
        while (maxScore <= 16) {
            additionalCardsNumber++;
            cards.add(deck.draw());
            maxScore = scoreCalculator.calculateMaxScore(cards);
        }
        return additionalCardsNumber;
    }

    public void sendCardToPlayer(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException("해당 플레이어는 존재하지 않습니다.");
        }
        if (!player.canSend()) {
            throw new IllegalArgumentException("한 플레이어가 가질 수 있는 카드 합의 최대는 21입니다.");
        }
        player.send(deck.draw());
    }

    public boolean isBlackjack() {
        if (cards.size() != 2) {
            return false;
        }
        Set<Rank> ranks = cards.stream()
                .map(Card::getRank)
                .collect(Collectors.toSet());
        if (!ranks.contains(Rank.ACE)) {
            return false;
        }

        return ranks.contains(Rank.KING) ||
                ranks.contains(Rank.QUEEN) ||
                ranks.contains(Rank.JACK) ||
                ranks.contains(Rank.TEN);
    }

    public Victory createVictory() {
        return Victory.create(this, players);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
