package blackjack.controller;

import blackjack.domain.betting.DealerBetting;
import blackjack.domain.betting.PlayerBettings;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Score;
import blackjack.dto.BettingResultDto;
import blackjack.dto.ParticipantCardsDto;
import blackjack.dto.ParticipantScoreDto;
import blackjack.service.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void runBlackjack() {
        final Players players = createPlayers();
        final PlayerBettings playerBettings = createBettings(players);

        final BlackjackGame blackjackGame = readyGame(players);

        if (blackjackGame.isNotDealerBlackjack()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame, playerBettings);
    }

    private Players createPlayers() {
        try {
            return Players.from(inputView.readPlayerNames());
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return createPlayers();
        }
    }

    private PlayerBettings createBettings(final Players players) {
        try {
            final Map<String, Integer> playerBettings = inputView.readBettings(players.getNames());
            return PlayerBettings.from(playerBettings);
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return createBettings(players);
        }
    }

    private BlackjackGame readyGame(final Players players) {
        try {
            final BlackjackGame blackjackGame = new BlackjackGame(players);
            blackjackGame.initCard();
            blackjackGame.divideCard();
            outputView.printStartCards(blackjackGame.getStartCards());
            return blackjackGame;
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readyGame(players);
        }
    }

    private void playGame(final BlackjackGame blackjackGame) { // name -> player 방식 고민 마저
        for (Player player : blackjackGame.getPlayers()) {
            runPlayerTurn(blackjackGame, player);
        }

        int count = blackjackGame.giveDealerMoreCards();
        outputView.printDealerMoreCard(count);
    }

    private void runPlayerTurn(final BlackjackGame blackjackGame, final Player player) {
        boolean isFirst = true;

        while (blackjackGame.isPlayerAlive(player) && needMoreCard(player)) {
            blackjackGame.addCardToPlayer(player);
            printPlayerCards(player);
            isFirst = false;
        }

        if (isFirst) {
            printPlayerCards(player);
        }
    }

    private boolean needMoreCard(final Player player) {
        ParticipantName rawName = player.getName();
        String name = rawName.getName();

        try {
            return inputView.readNeedMoreCard(name);
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return needMoreCard(player);
        }
    }

    private void printPlayerCards(final Player player) {
        outputView.printPlayerCard(ParticipantCardsDto.from(player));
    }

    private void finishGame(final BlackjackGame blackjackGame, final PlayerBettings playerBettings) {
        printCardScore(blackjackGame);
        printBettingResult(blackjackGame, playerBettings);
    }

    private void printCardScore(final BlackjackGame blackjackGame) {
        final List<ParticipantScoreDto> playerScoreDtos = convertToParticipantScoreDtos(
                blackjackGame.getPlayersHandResult(), blackjackGame.getPlayersScoreResult());

        final ParticipantScoreDto dealerScoreDto = ParticipantScoreDto.of(blackjackGame.getDealerHandResult(),
                blackjackGame.getDealerScore());

        outputView.printCardScore(playerScoreDtos, dealerScoreDto);
    }

    private void printBettingResult(final BlackjackGame blackjackGame, final PlayerBettings playerBettings) {
        final PlayerBettings playerBettingResults = playerBettings.applyWinStatus(blackjackGame.getWinningResult());

        final List<BettingResultDto> playerBettingResultDtos = convertToBettingResultDtos(playerBettingResults);
        final DealerBetting dealerBetting = DealerBetting.of(playerBettingResults, blackjackGame.getDealer());

        outputView.printBettingResult(playerBettingResultDtos, BettingResultDto.from(dealerBetting));
    }

    private List<ParticipantScoreDto> convertToParticipantScoreDtos(final Map<ParticipantName, Hands> handResult,
                                                                    final Map<ParticipantName, Score> scoreResult) {
        return handResult.entrySet().stream()
                .map(entry -> ParticipantScoreDto.of(entry, scoreResult.get(entry.getKey())))
                .toList();
    }

    private List<BettingResultDto> convertToBettingResultDtos(final PlayerBettings bettingResults) {
        return bettingResults.getPlayerBettings().stream()
                .map(BettingResultDto::from)
                .collect(Collectors.toList());
    }
}
