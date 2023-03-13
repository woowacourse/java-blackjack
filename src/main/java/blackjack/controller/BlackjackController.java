package blackjack.controller;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Money;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.player.Player;
import blackjack.domain.user.player.Players;
import blackjack.dto.CardDto;
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
    private final BlackjackGame blackjackGame;

    public BlackjackController(final InputView inputView, final OutputView outputView, final BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        Dealer dealer = new Dealer(DEFAULT_DEALER_NAME);
        Players players = getPlayers();

        initBetting(players);
        initDraw(players, dealer);
        playerDraw(players, dealer);
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

    private void initBetting(final Players players) {
        for (final Player player : players.getPlayers()) {
            Money money = repeatInput(() -> {
                int moneyValue = inputView.readBettingMoney(player.getName());
                return new Money(moneyValue);
            });
            blackjackGame.playerBet(player, money);
        }
    }

    private void initDraw(final Players players, final Dealer dealer) {
        blackjackGame.initDraw(dealer, players);

        DealerDto dealerDto = new DealerDto(dealer);
        List<PlayerDto> playersDto = players.getPlayers().stream()
                .map(PlayerDto::new)
                .collect(Collectors.toList());

        outputView.printInitCards(dealerDto, playersDto);
    }

    private void playerDraw(final Players players, final Dealer dealer) {
        players.getPlayers().forEach((this::processPlayerDraw));
        processDealerDraw(dealer);
    }

    private void processPlayerDraw(final Player player) {
        while (!player.isBust() && isCommandContinue(player)) {
            blackjackGame.playerDraw(player);
            List<CardDto> cards = player.showCards().stream().map(CardDto::new).collect(Collectors.toList());
            outputView.printParticipantCards(player.getName(), cards);
        }
    }

    private boolean isCommandContinue(final Player player) {
        return repeatInput(() -> inputView.readCommand(player.getName()));
    }

    private void processDealerDraw(final Dealer dealer) {
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
        Map<String, Integer> playersResult = blackjackGame.toPlayerProfit(players, dealer);
        Map<String, Integer> dealerResult = blackjackGame.toDealerProfit(players, dealer);
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
