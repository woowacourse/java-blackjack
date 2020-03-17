package controller;

import domain.WinningResult;
import domain.answer.Answer;
import domain.answer.AnswerType;
import domain.card.CardCalculator;
import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.PlayersName;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private Cards cards;
    private PlayersName playersName;
    private Dealer dealer;
    private Players players;

    public BlackjackController() {
        cards = new Cards();
        playersName = new PlayersName(InputView.inputPlayerName());
        OutputView.printInitial(playersName.getPlayerName());
        ready();
    }

    public void run() {
        startGame();
        result();
    }

    private void ready() {
        dealer = new Dealer(cards.giveTwoCardStartGame());
        players = new Players(cards, playersName.getPlayerName());

        OutputView.printUserCard(dealer.getName(), dealer.cardToString());
        for (Player player : players.getPlayers()) {
            OutputView.printUserCard(player.getName(), player.cardToString());
        }
    }

    private void startGame() {
        for (Player player : players.getPlayers()) {
            playerTurn(player);
        }
        dealerTurn();
    }

    private void playerTurn(Player player) {
        OutputView.printNewLine();
        while (CardCalculator.isUnderBlackJack(player.getCard()) && getAnswer(player).isEqualsAnswer(AnswerType.YES)) {
            player.drawCard(cards.giveCard());
            OutputView.printUserCard(player.getName(), player.cardToString());
        }
        if (CardCalculator.isMoreThanBlackJack(player.getCard())) {
            OutputView.printUserCardsOverBlackJack(player.getName());
        }
    }

    private void dealerTurn() {
        if (dealer.isAdditionalCard(cards.giveCard())) {
            OutputView.printDealerAdditionalCard();
        }
    }

    private void result() {
        OutputView.printFinalResult(dealer.getName(), dealer.cardToString(), dealer.sumCardNumber());
        for (Player player : players.getPlayers()) {
            OutputView.printFinalResult(player.getName(), player.cardToString(), player.sumCardNumber());
        }

        WinningResult winningResult = new WinningResult(players, dealer);
        OutputView.printWinningResult(winningResult.generateWinningUserResult(players));
    }

    private static AnswerType getAnswer(Player player) {
        Answer answer = new Answer(InputView.inputAnswer(player.getName()));

        return AnswerType.AnswerValueOf(answer.getAnswer());
    }
}
