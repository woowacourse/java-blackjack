import domain.BlackjackGame;
import domain.member.BettingAmount;
import domain.member.Member;
import domain.member.Members;
import domain.member.Name;
import domain.member.PlayerRole;
import java.util.ArrayList;
import java.util.List;
import presentation.dto.GameResult;
import presentation.dto.HitInfo;
import presentation.dto.MemberStatus;
import presentation.ui.InputView;
import presentation.ui.OutputView;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        run(inputView, outputView);
    }

    private static void run(InputView inputView, OutputView outputView) {
        BlackjackGame game = init(inputView);
        hit(inputView, outputView, game);
        printResult(outputView, game);
    }

    private static BlackjackGame init(InputView inputView) {
        List<Name> playerNames = inputView.readPlayerNames();
        Members members = new Members(initPlayer(inputView, playerNames));
        return new BlackjackGame(members);
    }

    private static List<Member> initPlayer(InputView inputView, List<Name> playerNames) {
        List<Member> players = new ArrayList<>();
        for (Name playerName : playerNames) {
            BettingAmount amount = inputView.readBettingAmount(playerName.getValue());
            players.add(new Member(playerName, new PlayerRole(amount)));
        }
        return players;
    }

    private static void hit(InputView inputView, OutputView outputView, BlackjackGame game) {
        game.initGame();
        outputView.printInitialStatus(HitInfo.firstCardFrom(game.getDealer()), HitInfo.firstCardFrom(game.getPlayers()));
        game.applyBlackjackBonus();
        game.getPlayers().stream()
                .filter(member -> !member.hasBlackjack())
                .forEach(member -> askToDraw(member, inputView, outputView, game));
    }

    private static void askToDraw(Member player, InputView inputView, OutputView outputView, BlackjackGame game) {
        while (!player.hasBust() && inputView.playContinue(player.getName())) {
            game.drawPlayer(player);
            outputView.printHandCard(HitInfo.from(player));
        }
        printBustOrStay(player, outputView);
    }

    private static void printBustOrStay(Member player, OutputView outputView) {
        if (player.hasBust()) {
            outputView.printBustMessage(player.getName());
            return;
        }
        outputView.printHandCard(HitInfo.from(player));
    }

    private static void printResult(OutputView outputView, BlackjackGame game) {
        while (game.canDealerDraw()) {
            game.drawDealer();
            outputView.printDealerDrawResult();
        }
        outputView.printFinalMemberStatus(MemberStatus.from(game.getDealer()), MemberStatus.from(game.getPlayers()));
        printGameResult(outputView, game);
    }

    private static void printGameResult(OutputView outputView, BlackjackGame game) {
        outputView.printGameResult(GameResult.from(game.getPlayerProfits(), game.getDealerProfit()));
    }
}
