package controller;

import domain.CardShuffleStrategy;
import domain.Dealer;
import domain.Game;
import domain.Participant;
import domain.Player;
import domain.Players;
import dto.GameResultDto;
import dto.ParticipantDto;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardShuffleStrategy strategy;

    public BlackJackController(InputView inputView, OutputView outputView, CardShuffleStrategy strategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.strategy = strategy;
    }

    public void doGame() {
        Game game = setUpGame();

        drawInitialCardsAndShowResult(game);

        drawCardPerPlayerAndShowResult(game);

        checkAndAdjustDealerCards(game);

        showGameResult(game);
    }

    private Game setUpGame() {
        List<String> playerNames = repeatAskPlayerNamesUntilSuccess();
        return Game.registerParticipantsAndPrepareTotalDeck(playerNames, strategy);
    }

    private List<String> repeatAskPlayerNamesUntilSuccess() {
        try {
            return askPlayerNames();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return repeatAskPlayerNamesUntilSuccess();
        }
    }

    private List<String> askPlayerNames() {
        outputView.printNamePrompt();
        return inputView.readNames();
    }

    private void drawInitialCardsAndShowResult(Game game) {
        game.readyParticipantDecks();
        showInitialParticipantCardShare(game);
    }

    private void showInitialParticipantCardShare(Game game) {
        Dealer dealer = game.getDealer();
        ParticipantDto dealerDto = ParticipantDto.initialFrom(dealer);

        Players players = game.getPlayers();
        List<ParticipantDto> playerDtos = ParticipantDto.listOf(players);

        outputView.printInitialCardShare(playerDtos);
        outputView.printInitialCardShareDetail(dealerDto, playerDtos);
    }

    private void drawCardPerPlayerAndShowResult(Game game) {
        for (Player player : game.getPlayers()) {
            drawCardUntilBustOrStand(game, player);
        }
    }

    private void drawCardUntilBustOrStand(Game game, Player player) {
        while (player.isDrawable() && wantToHit(player)) {
            drawCardAndPrintResult(game, player);
        }
    }

    private boolean wantToHit(Player player) {
        ParticipantDto playerDto = ParticipantDto.from(player);
        return repeatAskDrawCardUntilSuccess(playerDto);
    }

    private boolean repeatAskDrawCardUntilSuccess(ParticipantDto playerDto) {
        try {
            return askDrawCard(playerDto);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return repeatAskDrawCardUntilSuccess(playerDto);
        }
    }

    private boolean askDrawCard(ParticipantDto participantDto) {
        outputView.printHitOrStandPrompt(participantDto);
        String input = inputView.readHitOrStand();
        return input.equals("y");
    }

    private void drawCardAndPrintResult(Game game, Player player) {
        game.drawCardUnderCondition(player);
        ParticipantDto updatedPlayerDto = ParticipantDto.from(player);
        showPlayerCards(updatedPlayerDto);
    }

    private void showPlayerCards(ParticipantDto participantDto) {
        outputView.printCardShareDetail(participantDto);
    }

    private void checkAndAdjustDealerCards(Game game) {
        Dealer dealer = game.getDealer();
        boolean hasDealerDrawnMoreCard = game.drawCardUnderCondition(dealer);
        if (hasDealerDrawnMoreCard) {
            outputView.printAdditionalCardForDealerDescription();
        }
    }

    public void showGameResult(Game game) {
        List<Participant> participants = game.getParticipants();
        List<ParticipantDto> participantDtos = ParticipantDto.listOf(participants);
        outputView.printCardInfosWithSum(participantDtos);

        GameResultDto gameResultDto = GameResultDto.from(game);
        outputView.printWinTieLossResult(gameResultDto);
    }
}
