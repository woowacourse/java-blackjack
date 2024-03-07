package machine;

import domain.game.BlackjackGame;
import domain.participant.Dealer;
import domain.participant.Name;
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
        return new BlackjackGame(players, new Dealer(), new RandomCardGenerator());
    }

    private void playPlayerTurns(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            playPlayerTurn(game, player);
        }
    }

    private void playPlayerTurn(BlackjackGame game, Player player) {
        while (player.isReceivable()) {
            String yesOrNo = inputView.readYesOrNo(player);
            if (yesOrNo.equals("y")) {
                game.giveOneCard(player);
            }
            outputView.printParticipantCards(player);
            if (yesOrNo.equals("n")) {
                break;
            }
            if (player.isBusted()) {
                outputView.printBustMessage(player);
            }
        }
    }

    private void playDealerTurn(BlackjackGame game) {
        Dealer dealer = game.getDealer();
        if (dealer.isReceivable()) {
            game.giveOneCard(dealer);
            // print
            playDealerTurn(game);
        }
    }

    private void printResult(BlackjackGame game) {
        outputView.printAllParticipantsCardsWithScore(game);
    }
}
