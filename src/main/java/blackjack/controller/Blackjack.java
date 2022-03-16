package blackjack.controller;

import blackjack.domain.bet.Profit;
import blackjack.domain.game.CardDeck;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Players;
import blackjack.domain.result.Result;
import blackjack.view.InputConverter;
import blackjack.view.OutputView;

public class Blackjack {

    public void run() {
        Dealer dealer = new Dealer();

        OutputView.printPlayerNameInstruction();
        Players players = InputConverter.createPlayers();
        Result result = new Result(players);

        Profit profit = new Profit(result);
        players.bet(profit, OutputView::printBetting, InputConverter::createBetting);

        drawCards(dealer, players, result);
        showProfit(dealer, players, result, profit);
    }

    private void drawCards(final Dealer dealer, final Players players, final Result result) {
        CardDeck cardDeck = new CardDeck();
        dealCard(dealer, players, cardDeck);

        if (result.isKeepPlaying(dealer)) {
            players.draw(cardDeck, InputConverter::isDrawing, OutputView::printCards);
            OutputView.printNewLine();
            dealer.draw(cardDeck, OutputView::printDrawDealer);
            OutputView.printNewLine();
        }
    }

    private void dealCard(final Dealer dealer, final Players players, final CardDeck cardDeck) {
        OutputView.printDealCards(dealer.getName(), players.getNames());
        dealer.dealCards(cardDeck.pickInit());
        players.deal(cardDeck);
        OutputView.printInitCards(dealer, players);
    }

    private void showProfit(final Dealer dealer, final Players players, final Result result, final Profit profit) {
        OutputView.printTotalScore(dealer, players);
        result.compete(dealer);
        profit.calculate();
        OutputView.printProfit(dealer, profit, players);
    }
}
