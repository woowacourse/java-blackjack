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
            Dealer dealer = Dealer.init(new RandomCardsGenerator());
            Players players = createPlayers();
            BlackJackGame game = BlackJackGame.init(dealer, players);
            printInitialParticipantsHand(game);

            askNewCardToAllPlayers(game);
            setupDealerCards(game);

            showGameResult(game);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e);
        }
    }

    private Players createPlayers() {
        List<String> names = inputView.readPlayerNames();
        List<Player> players = names.stream().map(Player::init).toList();
        return new Players(players);
    }

    private void printInitialParticipantsHand(BlackJackGame game) {
        outputView.printParticipantsHand(game);
    }

    private void askNewCardToAllPlayers(BlackJackGame game) {
        Players players = game.getPlayers();
        for (Player player : players.getPlayers()) {
            askNewCardToPlayer(game.getDealer(), player);
        }
    }

    private void askNewCardToPlayer(Dealer dealer, Player player) {
        while (!player.isBurst() && !player.isBlackJack() && inputView.readYesOrNo(player).isYes()) {
            dealer.giveCards(player, 1);
            outputView.printPlayerCards(player);
        }
    }

    private void setupDealerCards(BlackJackGame game) {
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
