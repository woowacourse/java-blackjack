package controller;

import domain.WinningResult;
import domain.answer.AnswerType;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.PlayersName;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private CardDeck cardDeck;
    private PlayersName playersName;
    private Dealer dealer;
    private Players players;

    public BlackjackController() {
        cardDeck = new CardDeck();
        playersName = new PlayersName(InputView.inputPlayerName());
        OutputView.printInitial(playersName.getPlayerName());
        ready();
    }

    public void run() {
        startGame();
        result();
    }

    private void ready() {
        dealer = new Dealer(cardDeck.giveTwoCardStartGame());
        players = new Players(cardDeck, playersName.getPlayerName());

        OutputView.printCardReport(dealer.getName(), dealer.cardToString());
        for (Player player : players.getPlayers()) {
            OutputView.printCardReport(player.getName(), player.cardToString());
        }
    }

    private void startGame() {
        for (Player player : players.getPlayers()) {
            playerTurn(player);
        }
        dealerTurn();
    }

    private void dealerTurn() {
        if (dealer.isAdditionalCard(cardDeck.giveCard())) {
            OutputView.printDealerAdditionalCard();
        }
    }

    private void playerTurn(Player player) {
        if(player.isBlackJack()){
            OutputView.printBlackJack(player.getName());
            return;
        }

        OutputView.printNewLine();
        while (player.isUnderWinningCount() && isYesGetAnswer(player)) {
            player.drawCard(cardDeck.giveCard());
            OutputView.printCardReport(player.getName(), player.cardToString());
        }
        if (player.isMoreThanWinningCount() ) {
            OutputView.printUserCardsOverWinningNumber(player.getName());
        }
    }

    private void result() {
        OutputView.printResult(dealer.getName(), dealer.cardToString(), dealer.sumCardDeck());
        for (Player player : players.getPlayers()) {
            OutputView.printResult(player.getName(), player.cardToString(), player.sumCardDeck());
        }

        WinningResult winningResult = new WinningResult(players, dealer);
        OutputView.printWinning(winningResult.getWinningUserResult());
    }

    private static boolean isYesGetAnswer(Player player) {
         AnswerType answerType = AnswerType.answerValueOf(InputView.inputAnswer(player.getName()));
         return answerType.isYes();
    }
}
