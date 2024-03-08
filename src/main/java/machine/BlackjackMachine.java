package machine;

import domain.game.BlackjackGame;
import domain.game.Result;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import strategy.RandomCardGenerator;
import view.InputView;
import view.OutputView;

public class BlackjackMachine {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackMachine(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame game = initializeGame();
        distributeStartingCards(game);
        playPlayerTurns(game);
        playDealerTurn(game);
        printCardsAndScores(game);
        printResult(game);
    }

    private void distributeStartingCards(BlackjackGame game) {
        game.distributeStartingCards();
        outputView.printDistributionMessage(game);
        outputView.printAllParticipantsCards(game);
    }

    private BlackjackGame initializeGame() {
        List<String> names = inputView.readNames();
        List<Player> players = names.stream()
            .map(name -> new Player(new Name(name)))
            .toList();
        return new BlackjackGame(Participants.of(players), new RandomCardGenerator());
    }

    private void playPlayerTurns(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            playPlayerTurn(game, player);
        }
    }

    private void playPlayerTurn(BlackjackGame game, Player player) {
        while (player.isReceivable() && isRequestedYes(player)) {
            game.giveOneCard(player);
            outputView.printParticipantCards(player);
        }
        if (player.isBusted()) {
            outputView.printBustMessage(player);
            return;
        }
        outputView.printParticipantCards(player);
    }

    private boolean isRequestedYes(Player player) {
        return inputView.readYesOrNo(player).equals("y");
    }

    private void playDealerTurn(BlackjackGame game) {
        Dealer dealer = game.getDealer();
        if (dealer.isReceivable()) {
            game.giveOneCard(dealer);
            outputView.printDealerDrawMessage();
            playDealerTurn(game);
        }
    }

    private void printCardsAndScores(BlackjackGame game) {
        outputView.printAllParticipantsCardsWithScore(game);
    }

    private void printResult(BlackjackGame game) {
        Result result = Result.of(game.getPlayers(), game.getDealer());
        outputView.printResult(game, result);
    }
}
