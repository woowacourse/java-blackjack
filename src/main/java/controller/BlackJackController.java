package controller;

import domain.game.BlackJackGame;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.game.GameResult;
import domain.participant.Player;
import domain.participant.Players;
import dto.domain.PlayerNameAndBettingDto;
import java.util.ArrayList;
import java.util.List;
import util.NameParser;
import view.InputView;
import view.ResultView;

public class BlackJackController {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final List<String> participantNames = NameParser.makeNameList(inputView.getPlayerNames());
        final List<PlayerNameAndBettingDto> playersInfo = inputView.getPlayerBetting(participantNames);
        final BlackJackGame game = BlackJackGame.startGame(playersInfo);
        final Players players = game.players();
        final Dealer dealer = game.dealer();
        final Cards cards = game.cards();

        resultView.printGameStartMessage(players, dealer);
        drawPlayersTurn(players, cards);
        drawDealerTurn(dealer, cards);
        printResult(game);
    }

    private void drawPlayersTurn(Players players, Cards cards) {
        players.forEachPlayer(player -> drawPlayerTurn(player, cards));
    }

    private void drawPlayerTurn(Player player, Cards cards) {
        while (player.canHit()) {
            final boolean hit = inputView.askHitOrStand(player);
            if (!hit) {
                break;
            }
            hit(player, cards);
        }
    }

    private void hit(Player player, Cards cards) {
        player.hit(cards);
        resultView.printPlayerCards(player.getName(), resultView.joinCardNames(player.getCardList()));
        printPlayerBustIfNeeded(player);
    }

    private void printPlayerBustIfNeeded(Player player) {
        if (player.checkBust()) {
            resultView.printPlayerBust(player.getName());
        }
    }

    private void drawDealerTurn(Dealer dealer, Cards cards) {
        resultView.printLineBreak();
        while (dealer.shouldDrawCard()) {
            dealer.drawCard(cards);
            resultView.printDealerDrawMessage();
        }
        resultView.printLineBreak();
    }

    private void printResult(BlackJackGame game) {
        final Players players = game.players();
        final Dealer dealer = game.dealer();
        printGameResult(players, dealer);
        printWinnerResult(game);
    }

    private void printGameResult(Players players, Dealer dealer) {
        resultView.printResult(players, dealer);
        if (dealer.checkBust()) {
            resultView.printDealerBust();
        }
    }

    private void printWinnerResult(BlackJackGame game) {
        final GameResult gameResult = game.calculateResult();
        resultView.printWinner(game.players(), gameResult);
    }
}
