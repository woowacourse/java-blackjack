package blackjackgame.domain;

import blackjackgame.domain.card.Deck;
import blackjackgame.domain.gamers.CardHolder;
import blackjackgame.domain.gamers.Gamer;
import blackjackgame.domain.gamers.Gamers;
import blackjackgame.view.OutputView;
import java.util.List;

public class BlackjackGame {
    private final Gamer dealer;
    private final Gamers players;

    public BlackjackGame(Gamer dealer, Gamers players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initDealerAndPlayers(Deck deck) {
        dealer.cardHolderDrawNTimes(deck, 2);
        players.cardHoldersDrawNTimes(deck, 2);
    }

    public void dealerTryDraw(Deck deck) {
        try {
            dealer.cardHolderDrawNTimes(deck, 1);
            OutputView.printDealerAdditionalCardMessage();
        } catch (IllegalStateException e) {
            OutputView.printDealerNoAdditionalCardMessage();
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
