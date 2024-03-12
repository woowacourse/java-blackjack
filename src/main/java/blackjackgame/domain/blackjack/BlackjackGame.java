package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Deck;
import blackjackgame.view.OutputView;
import java.util.List;

public class BlackjackGame {
    private static final int EXECUTION_COUNT = 2;

    private final CardHolder cardHolderDealer;
    private final BetMaker betMakerDealer;
    private final CardHolders cardHolderPlayers;
    private final BetMakers betMakerPlayers;

    public BlackjackGame(CardHolder cardHolderDealer, BetMaker betMakerDealer,
                         CardHolders cardHolderPlayers, BetMakers betMakerPlayers) {
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
        return cardHolderDealer;
    }

    public List<CardHolder> getRawPlayers() {
        return cardHolderPlayers.getPlayers();
    }

    public String getRawDealerName() {
        return cardHolderDealer.getRawName();
    }

    public List<String> getRawPlayerNames() {
        return cardHolderPlayers.getRawPlayerNames();
    }
}
