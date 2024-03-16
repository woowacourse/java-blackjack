package blackjack.controller;

import blackjack.domain.card.strategy.RandomCardShuffleStrategy;
import blackjack.domain.game.BlackjackAction;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.*;
import blackjack.domain.profit.ProfitCalculator;
import blackjack.domain.profit.ProfitResult;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.ProfitResultDto;
import blackjack.utils.Converter;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = generateDealer();
        Players players = generatePlayers();
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        playBlackjackGame(blackjackGame, dealer, players);
        showGameResult(blackjackGame, dealer, players);
    }

    private Dealer generateDealer() {
        return new Dealer(new RandomCardShuffleStrategy());
    }

    private Players generatePlayers() {
        String readPlayerNames = replyOnException(inputView::readPlayerNames);
        List<String> playerNames = Converter.stringToList(readPlayerNames);

        return playerNames.stream()
                .map(this::generatePlayer)
                .collect(collectingAndThen(toList(), Players::new));
    }

    private Player generatePlayer(final String name) {
        BattingAmount battingAmount = replyOnException(() -> readBattingAmount(name));
        return new Player(name, battingAmount);
    }

    private BattingAmount readBattingAmount(String playerName) {
        int readBattingAmount = inputView.readBattingAmount(playerName);
        return new BattingAmount(readBattingAmount);
    }

    private void playBlackjackGame(final BlackjackGame blackjackGame, Dealer dealer, Players players) {
        blackjackGame.start();
        showCardsAfterFirstDeal(dealer, players);

        dealToParticipants(players, dealer, blackjackGame);
    }

    private void showCardsAfterFirstDeal(Dealer dealer, Players players) {
        ParticipantDto dealerDto = ParticipantDto.from(dealer);
        ParticipantsDto playersDto = ParticipantsDto.toDtoWithoutDealer(players);

        outputView.printCardsAfterFirstDeal(dealerDto, playersDto);
    }

    private void dealToParticipants(Players players, Dealer dealer, BlackjackGame blackjackGame) {
        dealToPlayers(players, blackjackGame);
        dealToDealer(dealer, blackjackGame);
    }

    private void dealToPlayers(Players players, BlackjackGame blackjackGame) {
        for (int playerIndex = 0; playerIndex < players.count(); playerIndex++) {
            Player player = players.findPlayerByIndex(playerIndex);
            dealToPlayer(player, blackjackGame);
        }
    }

    private void dealToPlayer(Player player, BlackjackGame blackjackGame) {
        while (player.canReceiveCard()) {
            BlackjackAction blackjackAction = replyOnException(() -> readBlackjackAction(player));
            blackjackGame.dealToPlayerIfHit(player, blackjackAction);

            outputView.printCardsOfParticipant(ParticipantDto.from(player));
        }
    }

    private BlackjackAction readBlackjackAction(Player player) {
        String expression = inputView.readReceiveMoreCardOrNot(player.getName());
        return BlackjackAction.from(expression);
    }

    private void dealToDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.canReceiveCard()) {
            blackjackGame.dealCardToDealer();
            outputView.printDealerReceiveCard();
        }
    }

    private void showGameResult(BlackjackGame blackjackGame, Dealer dealer, Players players) {
        showParticipantsCardsWithScore(dealer, players);
        showParticipantsProfit(dealer, blackjackGame.compareDealerAndPlayers());
    }

    private void showParticipantsCardsWithScore(Dealer dealer, Players players) {
        ParticipantsDto participantsDto = ParticipantsDto.toDtoWithDealer(dealer, players);
        outputView.printCardsWithScore(participantsDto);
    }

    private void showParticipantsProfit(Dealer dealer, Map<Player, ResultStatus> playersResult) {
        ProfitResult profitResult = ProfitCalculator.calculate(dealer, playersResult);
        outputView.printProfits(ProfitResultDto.from(profitResult));
    }

    private <T> T replyOnException(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return replyOnException(inputReader);
        }
    }
}
