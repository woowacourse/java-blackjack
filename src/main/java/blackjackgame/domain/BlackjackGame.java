package blackjackgame.domain;

import blackjackgame.domain.blackjack.GameProfit;
import blackjackgame.domain.card.Deck;
import blackjackgame.domain.gamers.CardHolder;
import blackjackgame.domain.gamers.Gamer;
import blackjackgame.domain.gamers.Gamers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackGame {
    private final static int EXECUTION_COUNT = 2;

    private final Gamer dealer;
    private final Gamers players;

    public BlackjackGame(Gamer dealer, Gamers players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initDealerAndPlayers(Deck deck) {
        dealer.cardHolderDraw(deck, EXECUTION_COUNT);
        players.cardHoldersDraw(deck, EXECUTION_COUNT);
    }

    public void dealerTryDraw(Deck deck) {
        dealer.cardHolderDraw(deck);
    }

    public GameProfit getGameProfit() {
        double dealerProfit = 0.0;
        List<Double> playersProfit = new ArrayList<>(Collections.nCopies(players.getSize(), 0.0));

        return calculateGameProfit(dealerProfit, playersProfit);
    }

    private GameProfit calculateGameProfit(double dealerProfit, List<Double> playersProfit) {
        List<Double> gameProfits = players.getGameProfits(dealer);

        for (int i = 0; i < gameProfits.size(); i++) {
            Double gameProfit = gameProfits.get(i);
            playersProfit.set(i, gameProfit + playersProfit.get(i));
            dealerProfit -= gameProfit;
        }

        return new GameProfit(dealerProfit, playersProfit);
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
