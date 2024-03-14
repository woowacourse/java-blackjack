import domain.*;
import dto.DealerInfo;
import dto.GameResult;
import dto.PlayerInfo;
import dto.PlayerInfos;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(final String[] args) {
        final BlackjackGame blackjackGame = createBlackjackGame();
        final PlayerInfos initPlayerInfos = PlayerInfos.from(blackjackGame.getPlayers());
        final DealerInfo initDealerInfo = DealerInfo.from(blackjackGame.getDealer());
        OutputView.printInitInfosOfPlayersAndDealer(initPlayerInfos, initDealerInfo);

        playGame(blackjackGame);
        final PlayerInfos playerInfos = PlayerInfos.from(blackjackGame.getPlayers());
        final DealerInfo dealerInfo = DealerInfo.from(blackjackGame.getDealer());
        OutputView.printInfosOfPlayersAndDealer(playerInfos, dealerInfo);

        final GameResult gameResult = blackjackGame.finishGame();
        OutputView.printBlackjackGameResults(gameResult);
    }

    private static BlackjackGame createBlackjackGame() {
        return new BlackjackGame(createPlayers(), new Dealer(new Deck()));
    }

    private static Players createPlayers() {
        return Players.from(InputView.inputNames());
    }

    private static void playGame(final BlackjackGame blackjack) {
        for (final var player : blackjack.getPlayers()) {
            drawCardDuringPlayerTurn(player, blackjack);
        }

        final Dealer dealer = blackjack.getDealer();

        if (dealer.canHit()) {
            blackjack.dealCard(dealer);
            OutputView.printDealerHitMessage();
        }
    }

    private static void drawCardDuringPlayerTurn(final Player player, final BlackjackGame blackjack) {
        while (player.canHit() && wantToHit(player)) {
            blackjack.dealCard(player);
        }
        OutputView.printPlayerInfo(PlayerInfo.from(player));
    }

    private static boolean wantToHit(final Player player) {
        final String input = InputView.inputHitCommand(player.name());
        final UserCommand userCommand = UserCommand.fromInput(input);
        return userCommand.isYes();
    }
}
