package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;
import java.util.Optional;

public class BlackJackGameController {

    private static final String HIT_COMMAND = "y";
    private static final String STAND_COMMAND = "n";

    private Deck deck;

    public void run() {
        final BlackJackGame blackJackGame = generateBlackJackGame();
        deck = new Deck();

        blackJackGame.handOutInitCards(deck);
        final Dealer dealer = blackJackGame.getDealer();
        final Players players = blackJackGame.getPlayers();
        OutputView.printInitCard(players.getPlayers(), dealer.getFirstCard());

        handOutHitCard(blackJackGame, players, dealer);
        judgeGameResult(blackJackGame, players, dealer);
    }

    private BlackJackGame generateBlackJackGame() {
        Optional<BlackJackGame> blackJackGame;
        do {
            blackJackGame = checkValidBlackJackGame();
        } while (blackJackGame.isEmpty());
        return blackJackGame.get();
    }

    private Optional<BlackJackGame> checkValidBlackJackGame() {
        try {
            final String playerNames = InputView.readNames();
            return Optional.of(new BlackJackGame(playerNames));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
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
            String gameCommand = generateGameCommand(player);
            isYesCommand = handOutCardByCommand(blackJackGame, player, gameCommand);
        }
    }

    private String generateGameCommand(Player player) {
        Optional<String> gameCommand;
        do {
            gameCommand = checkValidGameCommand(player);
        } while (gameCommand.isEmpty());
        return gameCommand.get();
    }

    private Optional<String> checkValidGameCommand(Player player) {
        try {
            final String gameCommand = InputView.readGameCommandToGetOneMoreCard(player.getName());
            validateCorrectCommand(gameCommand);
            return Optional.of(gameCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
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

    private void validateCorrectCommand(final String gameCommand) {
        if (!(HIT_COMMAND.equals(gameCommand) || STAND_COMMAND.equals(gameCommand))) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }

    private void judgeGameResult(BlackJackGame blackJackGame, Players players, Dealer dealer) {
        final PlayerResult playerResult = new PlayerResult();
        final Map<Result, Integer> dealerResult = blackJackGame.calculateDealerResult(playerResult);
        OutputView.printCardsWithSum(players.getPlayers(), dealer);
        OutputView.printFinalResult(dealerResult, playerResult.getPlayerResults());
    }
}
