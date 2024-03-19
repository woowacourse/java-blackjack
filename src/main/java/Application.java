import domain.blackjack.BlackjackGame;
import domain.blackjack.UserCommand;
import domain.card.Deck;
import domain.participant.*;
import dto.DealerInfo;
import dto.GameResult;
import dto.PlayerInfo;
import dto.PlayerInfos;
import view.InputView;
import view.OutputView;

import java.util.Map;
import java.util.stream.Collectors;

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
        final Names names = new Names(InputView.inputNames());
        final Map<Name, BetAmount> mapNamesToBetAmounts = mapNamesToBetAmounts(names);
        return Players.from(mapNamesToBetAmounts);
    }

    private static Map<Name, BetAmount> mapNamesToBetAmounts(final Names names) {
        return names.getNames().stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> new BetAmount(InputView.inputBetAmount(name.getName()))
                ));
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
        while (player.canHit()) {
            if (wantToHit(player)) {
                blackjack.dealCard(player);
                continue;
            }
            player.stand();
        }
        OutputView.printPlayerInfo(PlayerInfo.from(player));
    }

    private static boolean wantToHit(final Player player) {
        final String input = InputView.inputHitCommand(player.name());
        final UserCommand userCommand = UserCommand.fromInput(input);
        return userCommand.isYes();
    }
}
