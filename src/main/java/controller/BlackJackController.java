package controller;

import domain.BetMoney;
import domain.CardShuffleStrategy;
import domain.Dealer;
import domain.Game;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Players;
import dto.GameResultDto;
import dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void doGame(CardShuffleStrategy strategy) {
        Game game = setUpGame(strategy);

        drawInitialCardsAndShowResult(game);

        drawCardPerPlayerAndShowResult(game);

        checkAndAdjustDealerCards(game);

        showGameResult(game);
    }

    private Game setUpGame(CardShuffleStrategy strategy) {
        Players players = generatePlayers();
        return Game.registerParticipantsAndPrepareTotalDeck(players, strategy);
    }

    private Players generatePlayers() {
        List<Name> playerNames = repeatAskPlayerNamesUntilSuccess();
        List<Player> players = new ArrayList<>();
        for (Name playerName : playerNames) {
            Player player = generatePlayer(playerName);
            players.add(player);
        }
        return Players.of(players);
    }

    private List<Name> repeatAskPlayerNamesUntilSuccess() {
        try {
            return askPlayerNames();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return repeatAskPlayerNamesUntilSuccess();
        }
    }

    private List<Name> askPlayerNames() {
        outputView.printNamePrompt();
        List<String> playerNames = inputView.readNames();
        Players.validatePlayersCount(playerNames.size());
        return playerNames.stream()
                .map(Name::new)
                .toList();
    }

    private Player generatePlayer(Name playerName) {
        BetMoney betMoney = repeatAskBetMoneyUntilSuccess(playerName);
        return new Player(playerName, betMoney);
    }

    private BetMoney repeatAskBetMoneyUntilSuccess(Name playerName) {
        try {
            return askBetAmount(playerName);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return repeatAskBetMoneyUntilSuccess(playerName);
        }
    }

    private BetMoney askBetAmount(Name playerName) {
        outputView.printBetMoneyPrompt(playerName.name());
        int betAmount = inputView.readBetAmount();
        return new BetMoney(betAmount);
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
        boolean isPlayerDrawingCard = player.isDrawable();
        while (isPlayerDrawingCard) {
            boolean hasSuccessfullyDrawn = drawCardIfDrawableAndWantToHit(game, player);
            isPlayerDrawingCard = player.isDrawable() && hasSuccessfullyDrawn;
            showPlayerCards(player);
        }
    }

    private boolean drawCardIfDrawableAndWantToHit(Game game, Player player) {
        boolean wantToHit = wantToHit(player);
        boolean isDrawable = wantToHit;
        if (wantToHit) {
            isDrawable = game.drawCardUnderCondition(player);
        }
        return isDrawable;
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
        return inputView.readHitOrStand();
    }

    private void showPlayerCards(Player player) {
        ParticipantDto updatedPlayerDto = ParticipantDto.from(player);
        outputView.printCardShareDetail(updatedPlayerDto);
    }

    private void checkAndAdjustDealerCards(Game game) {
        Dealer dealer = game.getDealer();
        boolean isDealerDrawingCard = dealer.isDrawable();
        while (isDealerDrawingCard) {
            boolean hasDealerDrawnMoreCard = game.drawCardUnderCondition(dealer);
            printDescriptionIfDealerDrewCard(hasDealerDrawnMoreCard);
            isDealerDrawingCard = dealer.isDrawable() && hasDealerDrawnMoreCard;
        }
    }

    private void printDescriptionIfDealerDrewCard(boolean hasDealerDrawnMoreCard) {
        if (hasDealerDrawnMoreCard) {
            outputView.printAdditionalCardForDealerDescription();
        }
    }

    private void showGameResult(Game game) {
        List<Participant> participants = game.getParticipants();
        List<ParticipantDto> participantDtos = ParticipantDto.listOf(participants);
        outputView.printCardInfosWithSum(participantDtos);

        GameResultDto gameResultDto = GameResultDto.from(game.generateGameResult());
        outputView.printFinalGameProfitResult(gameResultDto);
    }
}
