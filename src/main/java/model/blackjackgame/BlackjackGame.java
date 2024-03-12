package model.blackjackgame;

import java.util.List;
import model.card.Card;
import model.dealer.Dealer;
import model.player.Player;
import model.player.Players;
import model.result.GameResult;

public class BlackjackGame {

    private static final int INITIAL_CARD_COUNT = 2;
    private static final int DEALER_COUNT = 1;

    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public int determineInitCardCount() {
        return (players.count() + DEALER_COUNT) * INITIAL_CARD_COUNT;
    }

    public void initHand(List<Card> cards) {
        dealer.hitCards(cards.subList(0, INITIAL_CARD_COUNT));
        players.hitCards(cards.subList(INITIAL_CARD_COUNT, cards.size()));
    }

    public boolean isDealerPossibleHit() {
        return dealer.isPossibleHit();
    }

    public void dealerHit(Card card) {
        dealer.hitCard(card);
    }

    public GameResult finishGame() {
        return GameResult.of(dealer, players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayerGroup() {
        return players.getGroup();
    }

    public Players getPlayers() {
        return players;
    }
}
