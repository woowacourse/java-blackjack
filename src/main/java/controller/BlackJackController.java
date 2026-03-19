package controller;

import controller.mapper.ResultViewMapper;
import domain.game.BlackJackGame;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.game.GameResult;
import domain.participant.Player;
import domain.participant.Players;
import dto.domain.PlayerNameAndBettingDto;
import java.util.List;
import view.InputView;
import view.ResultView;

public class BlackJackController {
    private final InputView inputView;
    private final ResultView resultView;
    private final ResultViewMapper resultViewMapper;

    public BlackJackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.resultViewMapper = new ResultViewMapper();
    }

    public void run() {
        final List<String> participantNames = inputView.getPlayerNames();
        final List<PlayerNameAndBettingDto> playersInfo = inputView.getPlayerBetting(participantNames);
        final BlackJackGame game = BlackJackGame.startGame(playersInfo);
        final Players players = game.players();
        final Dealer dealer = game.dealer();
        final Cards cards = game.cards();

        resultView.printGameStartMessage(resultViewMapper.toStartBlackJackDto(players, dealer));
        drawPlayersTurn(players, cards);
        drawDealerTurn(dealer, cards);
        printResult(game);
    }

    private void drawPlayersTurn(Players players, Cards cards) {
        players.forEachPlayer(player -> drawPlayerTurn(player, cards));
    }

    private void drawPlayerTurn(Player player, Cards cards) {
        while (player.canHit() && inputView.askHitOrStand(player)) {
            hit(player, cards);
        }
    }

    private void hit(Player player, Cards cards) {
        player.drawCard(cards);
        resultView.printPlayerCards(player.getName(), resultView.joinCardNames(player.getCards()));
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
        final GameResult gameResult = game.calculateResult();
        printGameResult(players, dealer);
        resultView.printWinner(resultViewMapper.toParticipantStatsDto(players, gameResult));
        resultView.printFinalProfit(resultViewMapper.toParticipantProfitDto(players));
    }

    private void printGameResult(Players players, Dealer dealer) {
        resultView.printResult(resultViewMapper.toResultDto(players, dealer));
        if (dealer.checkBust()) {
            resultView.printDealerBust();
        }
    }
}
