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
        if (dealer.isAdditionalCard(cards.giveCard())) {
            OutputView.printDealerAdditionalCard();
        }
    }

    private void playerTurn(Player player) {
        OutputView.printNewLine();
        while (CardCalculator.isUnderBlackJack(player.getCard()) && getAnswer(player).isEqualsAnswer(AnswerType.YES)) {
            player.drawCard(cards.giveCard());
            OutputView.printCardReport(player.getName(), player.cardToString());
        }
        if (CardCalculator.isMoreThanBlackJack(player.getCard())) {
            OutputView.printUserCardsOverBlackJack(player.getName());
        }
    }

    private void result() {
        OutputView.printResult(dealer.getName(), dealer.cardToString(), CardCalculator.sumCardDeck(dealer.getCard()));
        for (Player player : players.getPlayers()) {
            OutputView.printResult(
                    player.getName(), player.cardToString(), CardCalculator.sumCardDeck(dealer.getCard()));
        }

        WinningResult winningResult = new WinningResult(players, dealer);
        OutputView.printWinning(winningResult.generateWinningUserResult(players));
    }

    private static AnswerType getAnswer(Player player) {
        Answer answer = new Answer(InputView.inputAnswer(player.getName()));

        return AnswerType.AnswerValueOf(answer.getAnswer());
    }
}
