import domain.*;
import dto.DealerInfo;
import dto.GameResult;
import dto.PlayerInfo;
import dto.PlayerInfos;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(final String[] args) {
        final Blackjack blackjack = createBlackjack();
        final PlayerInfos initPlayerInfos = PlayerInfos.from(blackjack.getPlayers());
        final DealerInfo initDealerInfo = DealerInfo.from(blackjack.getDealer());
        OutputView.printInitInfosOfPlayersAndDealer(initPlayerInfos, initDealerInfo);

        playGame(blackjack);
        final PlayerInfos playerInfos = PlayerInfos.from(blackjack.getPlayers());
        final DealerInfo dealerInfo = DealerInfo.from(blackjack.getDealer());
        OutputView.printInfosOfPlayersAndDealer(playerInfos, dealerInfo);

        final GameResult gameResult = blackjack.finishGame();
        OutputView.printBlackjackGameResults(gameResult);
    }

    private static Blackjack createBlackjack() {
        return new Blackjack(createPlayers(), new Dealer(new Deck()));
    }

    private static Players createPlayers() {
        return Players.from(InputView.inputNames());
    }

    private static void playGame(final Blackjack blackjack) {
        for (final var player : blackjack.getPlayers()) {
            drawCardDuringPlayerTurn(player, blackjack);
        }

        final Dealer dealer = blackjack.getDealer();

        if (dealer.canHit()) {
            blackjack.dealCard(dealer);
            OutputView.printDealerHitMessage();
        }
    }

    private static void drawCardDuringPlayerTurn(final Player player, final Blackjack blackjack) {
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
