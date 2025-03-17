package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager(final CardPack cardPack, final Players players) {
        this.cardPack = cardPack;
        this.players = players;
        players.distributeInitialCards(() -> cardPack.getDealByCount(2));
    }

    public void addCardForGambler(final Gambler gambler) {
        Cards cards = cardPack.getDealByCount(1);
        players.addCardForGambler(gambler, cards);
    }

    public boolean isPlayerBust(final Player player) {
        return player.isBust();
    }

    public boolean isDealerHitThenAddCard() {
        if (players.isDealerHit()) {
            Cards cards = cardPack.getDealByCount(1);
            players.addCardForDealer(cards);
            return true;
        }
        return false;
    }

    public GameResult getGameResult() {
        return players.createGameResult();
    }

    public Players getPlayers() {
        return players;
    }
}
