package blackjack.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.FinalProfit;
import blackjack.domain.game.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGameController {

    private static final String YES_COMMAND = "y";
    private static final String NO_COMMAND = "n";
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
        final BlackJackGame blackJackGame = generateBlackJackGame();
        final Dealer dealer = blackJackGame.getDealer();
        final Players players = blackJackGame.getPlayers();
        final Map<Player, Money> playerProfit = makeMoneys(players);

        playBlackJackGame(blackJackGame, dealer, players);

        printFinalResult(dealer, players, blackJackGame.makePlayerProfit(playerProfit));
    }

    private BlackJackGame generateBlackJackGame() {
        Optional<BlackJackGame> blackJackGame;
        do {
            blackJackGame = generateNames();
        } while (blackJackGame.isEmpty());
        return blackJackGame.get();
    }

    private Optional<BlackJackGame> generateNames() {
        try {
            final String inputNames = inputView.readNames();
            return Optional.of(new BlackJackGame(new Dealer(), Players.createPlayers(inputNames)));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    // TODO: 재입력 로직 추가
    private Map<Player, Money> makeMoneys(final Players players) {
        final Map<Player, Money> playerProfit = new LinkedHashMap<>();

        for (final Player player : players.getPlayers()) {
            final String bettingAmount = inputView.readPlayersBettingAmount(player.getName());
            playerProfit.put(player, new Money(bettingAmount));
        }

        return playerProfit;
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
        boolean command = true;
        while (player.isUnderThanBoundary(PLAYER_BUST_BOUNDARY) && command) {
            final String playerAnswer = inputGameCommandToGetOneMoreCard(player);
            command = isCardHandedOutByCommand(blackJackGame, player, playerAnswer);
        }
    }

    private String inputGameCommandToGetOneMoreCard(final Player player) {
        Optional<String> gameCommand;
        do {
            gameCommand = checkGameCommand(player);
        } while (gameCommand.isEmpty());
        return gameCommand.get();
    }

    private Optional<String> checkGameCommand(final Player player) {
        try {
            final String gameCommand = inputView.readGameCommandToGetOneMoreCard(player.getName());
            validateCorrectCommand(gameCommand);
            return Optional.of(gameCommand);
        } catch (final IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private void validateCorrectCommand(final String gameCommand) {
        if (!(YES_COMMAND.equals(gameCommand) || NO_COMMAND.equals(gameCommand))) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }

    private boolean isCardHandedOutByCommand(final BlackJackGame blackJackGame, final Player player,
                                             final String playerAnswer) {
        if (playerAnswer.equals(YES_COMMAND)) {
            blackJackGame.handOutCardTo(shufflingMachine, player, DRAWING_CARD_SIZE);
            outputView.printParticipantCards(player.getName(), player.getCards());
            return true;
        }
        outputView.printParticipantCards(player.getName(), player.getCards());
        return false;
    }

    private void handOutCardTo(final BlackJackGame blackJackGame, final Dealer dealer) {
        while (dealer.isUnderThanBoundary(DEALER_DRAWING_BOUNDARY)) {
            blackJackGame.handOutCardTo(shufflingMachine, dealer, DRAWING_CARD_SIZE);
            outputView.printDealerReceiveOneMoreCard();
        }
    }

    private void printFinalResult(final Dealer dealer, final Players players, final FinalProfit finalProfit) {
        outputView.printCardsWithSum(players.getPlayers(), dealer);
        outputView.printFinalResult(finalProfit.getPlayerMoney(), finalProfit.calculateDealerProfit());
    }
}
