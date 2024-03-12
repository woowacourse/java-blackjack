package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Deck;
import blackjackgame.view.OutputView;
import java.util.List;

public class BlackjackGame {
    private static final int EXECUTION_COUNT = 2;

//    private final CardHolderGamer cardHolderDealer;
    private final CardHolderGamer dealer;
    private final BetMakerGamer betMakerDealer;
//    private final List<CardHolderGamer> cardHolderPlayers;
    private final Gamers players;
    private final List<BetMakerGamer> betMakerPlayers;

    public BlackjackGame(CardHolderGamer cardHolderDealer, BetMakerGamer betMakerDealer,
                         List<CardHolderGamer> cardHolderPlayers, List<BetMakerGamer> betMakerPlayers) {
        this.dealer = cardHolderDealer;
        this.betMakerDealer = betMakerDealer;
        this.players = new Gamers(cardHolderPlayers);
        this.betMakerPlayers = betMakerPlayers;
    }

    public void initDealerAndPlayers(Deck deck) {
        dealerDraw(deck);
        players.drawNTimes(deck, EXECUTION_COUNT);
    }

    private void dealerDraw(Deck deck) {
        dealer.draw(deck, new DealerRandomCardDrawStrategy(dealer), EXECUTION_COUNT);
    }

    private void playerDraw(Deck deck, CardHolderGamer player) {
        player.draw(deck, new PlayerRandomCardDrawStrategy(player), EXECUTION_COUNT);
    }

    public boolean isPlayerDead(CardHolderGamer player) {
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

    public void playerTryDraw(Deck deck, CardHolderGamer player) {
        try {
            playerDraw(deck, player);
        } catch (IllegalStateException e) {
            OutputView.printPlayerSummationOverDeadPoint();
        }
    }

    public CardHolderGamer getDealer() {
        return dealer;
    }

    public List<CardHolderGamer> getRawPlayers() {
        return players.getPlayers();
    }

    public String getRawDealerName() {
        return dealer.getRawName();
    }

    public List<String> getRawPlayerNames() {
        return players.getRawPlayerNames();
    }
}
