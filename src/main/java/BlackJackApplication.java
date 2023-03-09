import domain.BlackJackGame;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.ResultCalculator;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {
    public static void main(String[] args) {
        Players players = generatePlayers();
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        startPhase(players, dealer, blackJackGame);
        endPhase(players, dealer);
    }

    private static void startPhase(Players players, Dealer dealer, BlackJackGame blackJackGame) {
        initSetting(blackJackGame, players, dealer);
        OutputView.printParticipantResult(dealer.getName(), dealer.getCardNames());
        OutputView.printInitPlayerCards(players);
    }

    private static void endPhase(Players players, Dealer dealer) {
        askEachPlayers(players);
        dealerExecute(dealer);
        printFinalGameStatus(players, dealer);
        printFinalFightResult(players, dealer);
    }

    private static Players generatePlayers() {
        try {
            return new Players(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return generatePlayers();
        }
    }

    private static void initSetting(BlackJackGame blackJackGame, Players players, Dealer dealer) {
        blackJackGame.initSettingCards();
        OutputView.printInitMessage(players.getPlayerNames());
    }


    private static void askEachPlayers(Players players) {
        System.out.println();
        for (Player player : players.getPlayers()) {
            askPlayerDistribute(player);
        }
    }

    private static void dealerExecute(Dealer dealer) {
        while (dealer.isDrawable()) {
            System.out.println();
            OutputView.printDealerReceivedMessage();
            dealer.getHandCards().takeCard(Deck.generateCard());
        }
    }

    private static void askPlayerDistribute(Player player) {
        try {
            checkAdditionalDistribute(player);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            askPlayerDistribute(player);
        }
    }

    private static void checkAdditionalDistribute(Player player) {
        do {
            OutputView.printInputReceiveYesOrNotMessage(player.getName());
            OutputView.printParticipantResult(player.getName(), player.getCardNames());
        } while (player.isDrawable() && isReceivable(player));
    }

    private static boolean isReceivable(Player player) {
        if (InputView.inputReceiveOrNot()) {
            player.takeCard(1);
            return true;
        }
        return false;
    }

    private static void printFinalGameStatus(Players players, Dealer dealer) {
        System.out.println();
        OutputView.printParticipantFinalResult(dealer.getName(), dealer.getCardNames(), dealer.getScoreSum());
        printFinalPlayerResults(players);
    }

    private static void printFinalPlayerResults(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printParticipantFinalResult(player.getName(), player.getCardNames(), player.getScoreSum());
        }
    }

    private static void printFinalFightResult(Players players, Dealer dealer) {
        ResultCalculator resultCalculator = new ResultCalculator(players, dealer);
        resultCalculator.executeGame(players, dealer);
        OutputView.printFinalFightResult(resultCalculator);
    }

}
