package blackjack.controller;

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

        drawCards(dealer, players, result);
        showWinner(dealer, players, result);
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

    private void showWinner(final Dealer dealer, final Players players, final Result result) {
        OutputView.printTotalScore(dealer, players);
        result.compete(dealer);
        OutputView.printResult(dealer, result, players);
    }
}
