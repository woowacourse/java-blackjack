import domain.BlackjackGame;
import java.util.HashMap;
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
        BlackjackGame game = new BlackjackGame(initPlayer(inputView, playerNames));
        game.initialDeal();
        outputView.printInitialStatus(game.getDealerName(), memberFirstHands(game));
        game.applyBlackjackBonus();
        game.getMemberNames().stream()
                .filter(game::isNotDealer)
                .filter(playerName -> !game.hasBlackjack(playerName))
                .forEach(playerName -> askToDraw(playerName, inputView, outputView, game));
        printResult(outputView, game);
    }

    private static Map<String, Integer> initPlayer(InputView inputView, List<String> playerNames) {
        Map<String, Integer> players = new HashMap<>();
        for (String playerName : playerNames) {
            int amount = inputView.readBettingAmount(playerName);
            players.put(playerName, amount);
        }
        return players;
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
        boolean canContinue = inputView.playContinue(playerName);
        while (game.isContinuable(playerName) && canContinue) {
            game.drawPlayer(playerName);
            outputView.printHandCard(playerName, game.getCardNames(playerName));
            canContinue = inputView.playContinue(playerName);
        }
        if (!game.isContinuable(playerName) || !canContinue) {
            outputView.printHandCard(playerName, game.getCardNames(playerName));
        }
    }

    private static void printResult(OutputView outputView, BlackjackGame game) {
        while (game.canDealerDraw()) {
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
        outputView.printGameResult(new GameResult(game.getGameResults()));
    }
}
