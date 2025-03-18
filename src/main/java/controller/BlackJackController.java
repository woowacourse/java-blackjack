package controller;

import domain.BlackJackGame;
import domain.GameResult;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
            outputView.printParticipantsHand(game);

            drawCards(game);
            drawDealerCards(game);

            showGameResult(game);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private BlackJackGame startGame() {
        Dealer dealer = Dealer.init(new RandomCardsGenerator());
        List<String> playerNames = inputView.readPlayerNames();
        List<Money> monies = readBettingAmounts(playerNames);

        return BlackJackGame.init(dealer, playerNames, monies);
    }

    private List<Money> readBettingAmounts(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> Money.of(inputView.readBettingAmount(name)))
                .collect(Collectors.toList());
    }

    private void drawCards(BlackJackGame game) {
        game.determineNextTurnPlayer();
        while (game.isInProgress()) {
            processTurn(game);
        }
    }

    private void processTurn(BlackJackGame game) {
        Player player = game.getThisTurnPlayer();
        if (inputView.readPlayerHit(player).isYes()) {
            game.drawCardForCurrentPlayer();
            outputView.printPlayerCards(player);
            game.determineNextTurnPlayer();
            return;
        }
        outputView.printPlayerCards(player);
        game.skipThisTurn();
    }

    private void drawDealerCards(BlackJackGame game) {
        int count = game.calculateDealerDrawCount();
        outputView.printDealerDrawCount(count);
    }

    private void showGameResult(BlackJackGame game) {
        showFinalCards(game);
        Money money = game.calculateDealerRevenue();
        Map<Player, Money> revenueResult = game.calculateRevenue();
        outputView.printRevenue(money, revenueResult);
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
