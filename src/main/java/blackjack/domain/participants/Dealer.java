package blackjack.domain.participants;

import blackjack.domain.Victory;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.CardsShuffler;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ScoreCalculator;
import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final Players players;
    private final Deck deck;
    private final Cards cards;

    public Dealer(Players players, Deck deck, ScoreCalculator scoreCalculator) {
        this(players, deck, new Cards(new ArrayList<>(), scoreCalculator));
    }

    public Dealer(Players players, Deck deck, Cards cards) {
        this.players = players;
        this.deck = deck;
        this.cards = cards;
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
        cards.take(deck.draw(), deck.draw());
    }

    private void handOutCard() {
        players.sendAll((player) -> player.send(deck.draw(), deck.draw()));
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public void pickAdditionalCard() {
        while (calculateMaxScore() <= 16) {
            cards.take(deck.draw());
        }
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
        return cards.isBlackjack();
    }

    public Victory createVictory() {
        return Victory.create(this, players);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
