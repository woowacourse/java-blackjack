package blackjack.controller;

import blackjack.domain.bet.Profit;
import blackjack.domain.game.CardDeck;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.domain.result.Result;
import blackjack.dto.GamerDto;
import blackjack.view.InputConverter;
import blackjack.view.OutputView;

public class Blackjack {

    public void run() {
        Dealer dealer = new Dealer();

        OutputView.printPlayerNameInstruction();
        Players players = InputConverter.createPlayers();
        Result result = new Result(players);

        Profit profit = new Profit();
        bet(players, profit);

        drawCards(dealer, players, result);
        showProfit(dealer, players, result, profit);
    }

    private void bet(final Players players, final Profit profit) {
        players.bet(profit, OutputView::printBetting, InputConverter::createBetting);
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

        openDealCard(dealer, players);
    }

    private void openDealCard(final Dealer dealer, final Players players) {
        OutputView.printCards(dealer.getName(), GamerDto.getPartOfCards(dealer));
        for (Player player : players.getPlayers()) {
            OutputView.printCards(player.getName(), GamerDto.getCards(player));
        }
        OutputView.printNewLine();
    }

    private void showProfit(final Dealer dealer, final Players players, final Result result, final Profit profit) {
        showTotalScore(dealer, players);
        result.compete(dealer);
        profit.calculate(result);

        OutputView.printProfitTitle();
        OutputView.printProfit(dealer.getName(), profit.dealerProfit());
        for (Player player : players.getPlayers()) {
            OutputView.printProfit(player.getName(), profit.getBetting(player));
        }
    }

    private void showTotalScore(final Dealer dealer, final Players players) {
        OutputView.printScore(dealer.getName(), GamerDto.getCards(dealer), dealer.sumOfCards());
        for (Player player : players.getPlayers()) {
            OutputView.printScore(player.getName(), GamerDto.getCards(player), player.sumOfCards());
        }
        OutputView.printNewLine();
    }
}
