package blackjackgame.domain;

import blackjackgame.domain.blackjack.PlayerRandomCardDrawStrategy;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.gamers.CardHolder;
import blackjackgame.domain.gamers.Gamer;
import blackjackgame.domain.gamers.Gamers;
import blackjackgame.view.OutputView;
import java.util.List;

public class BlackjackGame {
    private static final int EXECUTION_COUNT = 2;

    private final Gamer dealer;
    private final Gamers players;

    public BlackjackGame(Gamer dealer, Gamers players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initDealerAndPlayers(Deck deck) {
        dealerDraw(deck);
        players.cardHoldersDrawNTimes(deck, EXECUTION_COUNT);
    }

    private void dealerDraw(Deck deck) {
        dealer.cardHolderDraw(deck, EXECUTION_COUNT);
    }

    private void playerDraw(Deck deck, CardHolder player) {
        player.draw(deck, new PlayerRandomCardDrawStrategy(player), EXECUTION_COUNT);
    }

    public boolean isPlayerDead(CardHolder player) {
        return player.isDead();
    }

    public void dealerTryDraw(Deck deck) {
        try {
            dealerDraw(deck);
            OutputView.printDealerAdditionalCardMessage();
        } catch (IllegalStateException e) {
            OutputView.printDealerNoAdditionalCardMessage();
        }
    }

    public void playerTryDraw(Deck deck, CardHolder player) {
        try {
            playerDraw(deck, player);
        } catch (IllegalStateException e) {
            OutputView.printPlayerSummationOverDeadPoint();
        }
    }

    public CardHolder getCardHolderDealer() {
        return dealer.getCardHolder();
    }

    public List<CardHolder> getRawPlayers() {
        return players.getCardHolders();
    }

    public String getRawDealerName() {
        return dealer.getRawGamerName();
    }

    public List<String> getRawPlayerNames() {
        return players.getRawGamerNames();
    }
}
