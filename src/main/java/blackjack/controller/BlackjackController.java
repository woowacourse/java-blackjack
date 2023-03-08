package blackjack.controller;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackjackController {

    public static final String DEFAULT_DEALER_NAME = "딜러";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = new Dealer(DEFAULT_DEALER_NAME);
        Players players = getPlayers();
        BlackjackGame blackjackGame = new BlackjackGame();

        initDraw(players, dealer, blackjackGame);
        playerDraw(players, dealer, blackjackGame);
        endGame(players, dealer);
    }

    private Players getPlayers() {
        return repeatInput(() -> {
            List<String> playerNames = inputView.readParticipantName();
            return new Players(playerNames.stream()
                    .map(Player::new)
                    .collect(Collectors.toList()));
        });
    }

    private void initDraw(final Players players, final Dealer dealer, final BlackjackGame blackjackGame) {
        blackjackGame.initDraw(dealer, players);

        DealerDto dealerDto = new DealerDto(dealer);
        List<PlayerDto> playersDto = players.getPlayers().stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList());

        outputView.printInitCards(dealerDto, playersDto);
    }

    private void playerDraw(final Players players, final Dealer dealer, final BlackjackGame blackjackGame) {
        players.getPlayers().forEach((player -> processPlayerDraw(blackjackGame, player)));
        processDealerDraw(dealer, blackjackGame);
    }

    private void processPlayerDraw(final BlackjackGame blackjackGame, final Player player) {
        while (!player.isBust() && isCommandContinue(player)) {
            blackjackGame.playerDraw(player);
            outputView.printParticipantCards(player.getName(), player.showCards());
        }
    }

    private boolean isCommandContinue(final Player player) {
        return repeatInput(() -> inputView.readCommand(player.getName()));
    }

    private void processDealerDraw(final Dealer dealer, final BlackjackGame blackjackGame) {
        while (dealer.isHitAble()) {
            blackjackGame.dealerDraw(dealer);
            outputView.printDealerDraw();
        }
    }

    private void endGame(final Players players, final Dealer dealer) {
        DealerDto dealerDto = new DealerDto(dealer);

        List<PlayerDto> playersDto = players.getPlayers().stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList());

        outputView.printCardResult(dealerDto, playersDto);
        printResult(dealer, players);
    }

    private void printResult(final Dealer dealer, final Players players) {
        Map<String, GameResult> playersResult = players.toResults(dealer);
        Map<GameResult, Integer> dealerResult = dealer.getResult(players);
        outputView.printGameResult(dealerResult, playersResult);
    }

    private <T> T repeatInput(Supplier<T> input) {
        try {
            return input.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return repeatInput(input);
        }
    }
}
