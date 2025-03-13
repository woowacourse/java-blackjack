package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;

public class GameManager {

    private final CardPack cardPack;
    private final Players players;

    public GameManager(final CardPack cardPack, final List<Gambler> gamblers) {
        this.cardPack = cardPack;

        Dealer dealer = initDealer();
        initGambler(gamblers);
        this.players = new Players(dealer, gamblers);
    }

    public void addCardForGambler(final Gambler gambler) {
        List<Card> cards = cardPack.getDealByCount(1);
        players.addCardForGambler(gambler, cards);
    }

    public boolean isPlayerBust(final Player player) {
        return player.isBust();
    }

    public boolean isDealerHitThenAddCard() {
        if (players.isDealerHit()) {
            List<Card> cards = cardPack.getDealByCount(1);
            players.dealAddCardForDealer(cards);
            return true;
        }
        return false;
    }

    public GameResult getGameResult() {
        return players.getGameResult();
    }

    private Dealer initDealer() {
        List<Card> cards = cardPack.getDealByCount(2);

        Dealer dealer = new Dealer();
        dealer.addCards(cards);
        return dealer;
    }

    private void initGambler(final List<Gambler> gamblers) {
        for (Gambler player : gamblers) {
            List<Card> cards = cardPack.getDealByCount(2);
            player.addCards(cards);
        }
    }

    public Players getPlayers() {
        return players;
    }
}
