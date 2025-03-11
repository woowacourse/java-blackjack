package controller;

import domain.BlackJackGame;
import domain.GameResult;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            BlackJackGame game = startGame();
            printInitialParticipantsHand(game);

            drawCards(game);
            drawDealerCards(game);

            showGameResult(game);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private BlackJackGame startGame() {
        Dealer dealer = Dealer.init(new RandomCardsGenerator());
        Players players = createPlayers();
        return BlackJackGame.init(dealer, players);
    }

    private Players createPlayers() {
        List<Player> players = inputView.readPlayerNames()
                .stream()
                .map(Player::init).toList();
        return new Players(players);
    }

    private void printInitialParticipantsHand(BlackJackGame game) {
        outputView.printParticipantsHand(game);
    }

    private void drawCards(BlackJackGame game) {
        game.determineNextTurnPlayer();
        while (game.isInProgress()) {
            if (inputView.readYesOrNo(game.getThisTurnPlayer()).isNo()) {
                game.skipThisTurn();
                continue;
            }
            game.drawCardForCurrentPlayer();
            outputView.printPlayerCards(game.getThisTurnPlayer());
            game.determineNextTurnPlayer();
        }
    }

    private void drawDealerCards(BlackJackGame game) {
        game.setupDealerCards();
        outputView.printDealerDrawCount(game.calculateDealerDrawCount());
    }

    private void showGameResult(BlackJackGame game) {
        showFinalCards(game);
        showPlayerResult(game);
    }

    private void showFinalCards(BlackJackGame game) {
        outputView.printDealerCardsAndResult(game.getDealer());
        outputView.printPlayersCardAndSum(game.getPlayers());
    }

    private void showPlayerResult(BlackJackGame game) {
        Map<GameResult, Integer> dealerResult = game.calculateDealerWinningCount();
        Map<Player, GameResult> playerResult = game.calculateGameResult();
        outputView.printResult(dealerResult, playerResult);
    }
}
