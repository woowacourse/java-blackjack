package model.game;

import java.util.stream.IntStream;
import model.card.Card;
import model.card.CardDeck;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import model.result.GameResult;

public class BlackjackGame {

    private static final int DEQUE_COUNT = 4;
    private static final int INITIAL_CARD_COUNT = 2;

    private final CardDeck cardDeck;
    private final Dealer dealer;

    public BlackjackGame() {
        cardDeck = CardDeck.createShuffledDeck(DEQUE_COUNT);
        dealer = new Dealer();
    }

    public void dealInitialCards(Players players) {
        dealCardsToDealer();
        dealCardsToPlayers(players);
    }

    private void dealCardsToDealer() {
        IntStream.range(0, INITIAL_CARD_COUNT)
            .forEach(count -> dealer.hitCard(cardDeck.drawCard()));
    }

    private void dealCardsToPlayers(Players players) {
        IntStream.range(0, players.count())
            .forEach(order -> dealCardsToPlayer(players, order));
    }

    private void dealCardsToPlayer(Players players, int order) {
        IntStream.range(0, INITIAL_CARD_COUNT)
            .forEach(count -> players.hitCard(order, cardDeck.drawCard()));
    }

    public void dealCard(Player player) {
        Card card = cardDeck.drawCard();
        player.hitCard(card);
    }

    public boolean dealerHitTurn() {
        if (dealer.isPossibleHit()) {
            Card card = cardDeck.drawCard();
            dealer.hitCard(card);
            return true;
        }
        return false;
    }

    public GameResult finish(Players players) {
        return GameResult.of(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
