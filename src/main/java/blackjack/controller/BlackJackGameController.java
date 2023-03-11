package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Profit;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class BlackJackGameController {

    private static final String HIT_COMMAND = "y";
    private static final String STAND_COMMAND = "n";

    private Deck deck;

    public void run() {
        final BlackJackGame blackJackGame = readUntilValidate(this::generateBlackJackGame);
        deck = new Deck();

        final Dealer dealer = blackJackGame.getDealer();
        final Players players = blackJackGame.getPlayers();

        final Map<Player, Profit> bettingMoney = generateBettingMoney(players);

        blackJackGame.handOutInitCards(deck);
        OutputView.printInitCard(players.getPlayers(), dealer.getFirstCard());

        handOutHitCard(blackJackGame, players, dealer);
        judgeGameResult(blackJackGame, players, dealer, bettingMoney);
    }

    private BlackJackGame generateBlackJackGame() {
        final String playerNames = InputView.readNames();
        return new BlackJackGame(playerNames);
    }

    private Map<Player, Profit> generateBettingMoney(final Players players) {
        final Map<Player, Profit> bettingMoney = new LinkedHashMap<>();
        for (final Player player : players.getPlayers()) {
            final Profit profit = readUntilValidate(() -> generateProfit(player));
            bettingMoney.put(player, profit);
        }
        return bettingMoney;
    }

    private Profit generateProfit(final Player player) {
        final int playerBetting = InputView.readBettingMoney(player.getName());
        return Profit.of(playerBetting);
    }

    private void handOutHitCard(BlackJackGame blackJackGame, Players players, Dealer dealer) {
        for (final Player player : players.getPlayers()) {
            handOutCardToEachPlayer(blackJackGame, player);
        }
        handOutCardToDealer(blackJackGame, dealer);
    }

    private void handOutCardToDealer(BlackJackGame blackJackGame, Dealer dealer) {
        while (dealer.isUnderThanBoundary(Dealer.DRAWING_BOUNDARY)) {
            blackJackGame.handOutCardTo(deck, dealer);
            OutputView.printDealerReceiveOneMoreCard();
        }
    }

    private void handOutCardToEachPlayer(BlackJackGame blackJackGame, Player player) {
        boolean isYesCommand = true;
        while (player.isUnderThanBoundary(Participant.BUST_BOUNDARY) && isYesCommand) {
            String gameCommand = readUntilValidate(() -> generateGameCommand(player));
            isYesCommand = handOutCardByCommand(blackJackGame, player, gameCommand);
        }
    }

    private boolean handOutCardByCommand(BlackJackGame blackJackGame, Player player, String playerAnswer) {
        if (playerAnswer.equals(HIT_COMMAND)) {
            blackJackGame.handOutCardTo(deck, player);
            OutputView.printParticipantCards(player.getName(), player.getCards());
            return true;
        }
        OutputView.printParticipantCards(player.getName(), player.getCards());
        return false;
    }

    private String generateGameCommand(final Player player) {
        final String gameCommand = InputView.readGameCommandToGetOneMoreCard(player.getName());
        validateCorrectCommand(gameCommand);
        return gameCommand;
    }

    private void validateCorrectCommand(final String gameCommand) {
        if (!(HIT_COMMAND.equals(gameCommand) || STAND_COMMAND.equals(gameCommand))) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }

    private void judgeGameResult(BlackJackGame blackJackGame, Players players, Dealer dealer, Map<Player, Profit> bettingMoney) {
        final DealerResult dealerResult = new DealerResult();
        final PlayerResult playerResult = new PlayerResult();
        blackJackGame.calculateParticipantResult(dealerResult, playerResult);

        final Map<Player, Profit> playerProfit = blackJackGame.calculatePlayerProfit(playerResult, bettingMoney);
        final Profit dealerProfit = blackJackGame.calculateDealerProfit(playerProfit);
        OutputView.printCardsWithSum(players.getPlayers(), dealer);
        OutputView.printFinalProfit(dealerProfit, playerProfit);
    }

    private <T> T readUntilValidate(final Supplier<T> supplier) {
        Optional<T> userInput;
        do {
            userInput = readUserInput(supplier);
        } while (userInput.isEmpty());
        return userInput.get();
    }

    private <T> Optional<T> readUserInput(final Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
