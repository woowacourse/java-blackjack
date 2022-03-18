package blackjack.controller;

import blackjack.domain.game.Deck;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.dto.GamerDto;
import blackjack.view.InputConverter;
import blackjack.view.OutputView;

public class BlackjackGame {

    public void run() {
        Dealer dealer = new Dealer();

        OutputView.printPlayerNameInstruction();
        Players players = InputConverter.createPlayers();

        bet(players);

        drawCards(dealer, players);
        showProfit(dealer, players);
    }

    private void bet(final Players players) {
        players.bet(OutputView::printBetting, InputConverter::createBetting);
    }

    private void drawCards(final Dealer dealer, final Players players) {
        Deck deck = new Deck();
        dealCard(dealer, players, deck);

        if (players.isKeepPlaying(dealer)) {
            players.draw(deck, InputConverter::isDrawing, OutputView::printCards);
            OutputView.printNewLine();
            dealer.draw(deck, OutputView::printDrawDealer);
            OutputView.printNewLine();
        }
    }

    private void dealCard(final Dealer dealer, final Players players, final Deck deck) {
        OutputView.printDealCards(dealer.getName(), players.getNames());
        dealer.deal(deck.pickInit());
        players.deal(deck);

        openDealCard(dealer, players);
    }

    private void openDealCard(final Dealer dealer, final Players players) {
        OutputView.printCards(dealer.getName(), GamerDto.getPartOfCards(dealer));
        for (Player player : players.getPlayers()) {
            OutputView.printCards(player.getName(), GamerDto.getCards(player));
        }
        OutputView.printNewLine();
    }

    private void showProfit(final Dealer dealer, final Players players) {
        showTotalScore(dealer, players);

        OutputView.printProfitTitle();
        players.compareCards(dealer);
        OutputView.printProfit(dealer.getName(), (int) dealer.profit(players.totalProfit()));
        for (Player player : players.getPlayers()) {
            OutputView.printProfit(player.getName(), (int) player.profit());
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
