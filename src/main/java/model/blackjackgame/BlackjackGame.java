package model.blackjackgame;

import java.util.List;
import model.card.Card;
import model.card.Hand;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;

public class BlackjackGame {

    private static final int INITIAL_CARD_COUNT = 2;
    private static final int DEALER_COUNT = 1;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initCards(Hand cards) {
        List<Card> cardsElement = cards.getCards();
        dealer.hitCards(cardsElement.subList(0, INITIAL_CARD_COUNT));
        players.hitCards(cardsElement.subList(INITIAL_CARD_COUNT, cardsElement.size()));
    }

    public void playerHit(Player player, Card card) {
        player.hitCard(card);
    }

    public boolean isDealerPossibleHit() {
        return dealer.isPossibleHit();
    }

    public void dealerHit(Card card) {
        dealer.hitCard(card);
    }

    public int determineInitCardCount() {
        return (players.count() + DEALER_COUNT) * INITIAL_CARD_COUNT;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayersGroup() {
        return players.getGroup();
    }

    public Players getPlayers() {
        return players;
    }
}
