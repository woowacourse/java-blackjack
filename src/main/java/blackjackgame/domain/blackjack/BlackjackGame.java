package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Deck;
import blackjackgame.view.OutputView;
import java.util.List;

public class BlackjackGame {
    private static final int EXECUTION_COUNT = 2;

    private final CardHolderGamer cardHolderDealer;
    private final BetMakerGamer betMakerDealer;
    private final CardHolderGamers cardHolderPlayers;
    private final BetMakerGamers betMakerPlayers;

    public BlackjackGame(CardHolderGamer cardHolderDealer, BetMakerGamer betMakerDealer,
                         CardHolderGamers cardHolderPlayers, BetMakerGamers betMakerPlayers) {
        this.cardHolderDealer = cardHolderDealer;
        this.betMakerDealer = betMakerDealer;
        this.cardHolderPlayers = cardHolderPlayers;
        this.betMakerPlayers = betMakerPlayers;
    }

    public void initDealerAndPlayers(Deck deck) {
        dealerDraw(deck);
        cardHolderPlayers.drawNTimes(deck, EXECUTION_COUNT);
    }

    private void dealerDraw(Deck deck) {
        cardHolderDealer.draw(deck, new DealerRandomCardDrawStrategy(cardHolderDealer), EXECUTION_COUNT);
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

    public CardHolderGamer getCardHolderDealer() {
        return cardHolderDealer;
    }

    public List<CardHolderGamer> getRawPlayers() {
        return cardHolderPlayers.getPlayers();
    }

    public String getRawDealerName() {
        return cardHolderDealer.getRawName();
    }

    public List<String> getRawPlayerNames() {
        return cardHolderPlayers.getRawPlayerNames();
    }
}
