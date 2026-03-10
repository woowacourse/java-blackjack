import domain.BlackjackGame;
import domain.vo.RoundResult;
import java.util.List;
import java.util.Map;
import presentation.dto.GameResult;
import presentation.dto.MemberStatus;
import presentation.ui.InputView;
import presentation.ui.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        List<String> playerNames = inputView.readPlayerNames();
        BlackjackGame game = new BlackjackGame(playerNames);
        game.initialDeal();
        outputView.printInitialStatus(game.getDealerName(), memberFirstHands(game));
        for (String playerName : playerNames) {
            askToDraw(playerName, inputView, outputView, game);
        }
        printResult(outputView, game);
    }

    private static List<MemberStatus> memberFirstHands(BlackjackGame game) {
        return game.getMemberNames()
                .stream()
                .map(name -> {
                    List<String> cards = game.getFirstCardNames(name);
                    int memberPoint = game.getMemberPoint(name);
                    return new MemberStatus(name, cards, memberPoint);
                }).toList();
    }

    private static void askToDraw(String playerName, InputView inputView, OutputView outputView, BlackjackGame game) {
        while (game.isContinuable(playerName) && inputView.playContinue(playerName)) {
            game.drawPlayer(playerName);
            outputView.printHandCard(playerName, game.getCardNames(playerName));
        }
    }

    private static void printResult(OutputView outputView, BlackjackGame game) {
        if (game.canDealerDraw()) {
            game.drawDealer();
            outputView.printDealerDrawResult();
        }
        outputView.printFinalMemberStatus(memberHands(game));
        printGameResult(outputView, game);
    }

    private static List<MemberStatus> memberHands(BlackjackGame game) {
        return game.getMemberNames()
                .stream()
                .map(name -> {
                    List<String> cards = game.getCardNames(name);
                    int playerPoint = game.getMemberPoint(name);
                    return new MemberStatus(name, cards, playerPoint);
                }).toList();
    }

    private static void printGameResult(OutputView outputView, BlackjackGame game) {
        Map<String, RoundResult> gameResults = game.getGameResults();
        int dealerLoseAmount = Math.toIntExact(gameResults.values().stream()
                .filter(result -> result.equals(RoundResult.WIN))
                .count());
        int dealerWinAmount = Math.toIntExact(gameResults.values().stream()
                .filter(result -> result.equals(RoundResult.LOSE))
                .count());
        outputView.printGameResult(new GameResult(dealerWinAmount, dealerLoseAmount, gameResults));
    }
}
