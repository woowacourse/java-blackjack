package blackjack.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.CommandType;
import blackjack.domain.game.FinalProfit;
import blackjack.domain.game.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGameController {

    private static final int DEALER_DRAWING_BOUNDARY = 17;
    private static final int PLAYER_BUST_BOUNDARY = 21;
    private static final int DRAWING_CARD_SIZE = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private final ShufflingMachine shufflingMachine;

    public BlackJackGameController(final InputView inputView, final OutputView outputView,
                                   final ShufflingMachine shufflingMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.shufflingMachine = shufflingMachine;
    }

    public void run() {
        final BlackJackGame blackJackGame = repeatInput(this::createBlackJackGame);
        final Dealer dealer = blackJackGame.getDealer();
        final Players players = blackJackGame.getPlayers();
        final Map<Player, Money> playerProfit = createBetting(players);

        playBlackJackGame(blackJackGame, dealer, players);

        printResult(dealer, players, blackJackGame.makePlayerProfit(playerProfit));
    }

    private BlackJackGame createBlackJackGame() {
        final String inputNames = inputView.readNames();
        return new BlackJackGame(new Dealer(), Players.createPlayers(inputNames));
    }

    private Map<Player, Money> createBetting(final Players players) {
        final Map<Player, Money> playerProfit = new LinkedHashMap<>();

        for (final Player player : players.getPlayers()) {
            final Money bettingAmount = repeatInput(() -> makeBettingAmount(player));
            playerProfit.put(player, bettingAmount);
        }

        return playerProfit;
    }

    private Money makeBettingAmount(final Player player) {
        final String inputMoney = inputView.readPlayersBettingAmount(player.getName());
        return new Money(inputMoney);
    }

    private void playBlackJackGame(final BlackJackGame blackJackGame, final Dealer dealer,
                                                      final Players players) {
        blackJackGame.handOutCards(shufflingMachine);

        outputView.printInitCard(players.getPlayers(), dealer.getFirstCard());
        for (final Player player : players.getPlayers()) {
            handOutCardTo(blackJackGame, player);
        }
        handOutCardTo(blackJackGame, dealer);
    }

    private void handOutCardTo(final BlackJackGame blackJackGame, final Player player) {
        while (player.isUnderThanBoundary(PLAYER_BUST_BOUNDARY)) {
            final String inputCommand =
                    repeatInput(() -> inputView.readGameCommandToGetOneMoreCard(player.getName()));
            if (!CommandType.canHit(inputCommand)) {
                outputView.printParticipantCards(player.getName(), player.getCards());
                return;
            }
            blackJackGame.handOutCardTo(shufflingMachine, player, DRAWING_CARD_SIZE);
            outputView.printParticipantCards(player.getName(), player.getCards());
        }
    }

    private void handOutCardTo(final BlackJackGame blackJackGame, final Dealer dealer) {
        while (dealer.isUnderThanBoundary(DEALER_DRAWING_BOUNDARY)) {
            blackJackGame.handOutCardTo(shufflingMachine, dealer, DRAWING_CARD_SIZE);
            outputView.printDealerReceiveOneMoreCard();
        }
    }

    private void printResult(final Dealer dealer, final Players players, final FinalProfit finalProfit) {
        outputView.printCardsWithSum(players.getPlayers(), dealer);
        outputView.printFinalResult(finalProfit.getPlayerMoney(), finalProfit.calculateDealerProfit());
    }

    private <T> T repeatInput(final Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (final IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
