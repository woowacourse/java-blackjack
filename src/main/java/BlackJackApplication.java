import domain.BlackJackGame;
import domain.participant.Dealer;
import domain.participant.Players;
import domain.result.ResultCalculator;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {
    public static void main(String[] args) {
        Players players = generatePlayers();
        Dealer dealer = new Dealer();
        initSetting(players, dealer);
        run(players, dealer);
    }

    private static Players generatePlayers() {
        try {
            return new Players(initPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return generatePlayers();
        }
    }

    private static List<String> initPlayerNames() {
        return InputView.inputPlayerNames();
    }

    private static void run(Players players, Dealer dealer) {
        players.askEachPlayers();
        dealerDistributeOrNot(dealer);
        printFinalGameStatus(players, dealer);
        printFinalFightResult(players, dealer);
    }

    private static void initSetting(Players players, Dealer dealer) {
        initSettingCards(players, dealer);
        OutputView.printInitMessage(players.getPlayerNames());

        printInitMemberCards(players, dealer);
    }

    private static void initSettingCards(Players players, Dealer dealer) {
        BlackJackGame.distributeCard(dealer, 2);
        players.initDistribute();
    }

    private static void printInitMemberCards(Players players, Dealer dealer) {
        OutputView.printParticipantResult(dealer.getName(), dealer.getCardNames());
        players.printInitPlayerCards();
    }

    private static void dealerDistributeOrNot(Dealer dealer) {
        while (dealer.dealerMustDraw()) {
            BlackJackGame.distributeCard(dealer, 1);
            OutputView.printDealerReceivedMessage();
        }
    }

    private static void printFinalGameStatus(Players players, Dealer dealer) {
        System.out.println();
        OutputView.printParticipantFinalResult(dealer.getName(), dealer.getCardNames(), dealer.calculateScore());
        players.printFinalPlayerResults();
    }

    private static void printFinalFightResult(Players players, Dealer dealer) {
        ResultCalculator resultCalculator = new ResultCalculator(players, dealer);
        resultCalculator.executeGame(players, dealer);
        OutputView.printFinalFightResult(resultCalculator.getFinalFightResults());
    }

}
