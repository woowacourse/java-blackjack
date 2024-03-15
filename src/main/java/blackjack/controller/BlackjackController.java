package blackjack.controller;

import blackjack.domain.batting.BattingAmount;
import blackjack.domain.batting.BattingAmountRepository;
import blackjack.domain.card.CardDeck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameCommand;
import blackjack.domain.game.ResultStatus;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.profit.ProfitCalculator;
import blackjack.domain.profit.ProfitResult;
import blackjack.domain.strategy.CardShuffleStrategy;
import blackjack.domain.strategy.RandomCardShuffleStrategy;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.ProfitResultDto;
import blackjack.utils.Converter;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = generateDealer();
        Players players = replyOnException(this::generatePlayers);
        BattingAmountRepository repository = initBattingAmountRepository(players);

        Map<Player, ResultStatus> gameResult = playBlackjackGame(dealer, players);

        showParticipantsCardsWithScore(dealer, players);
        showParticipantsProfit(dealer, repository, gameResult);
    }

    private Dealer generateDealer() {
        CardDeck cardDeck = new CardDeck();
        CardShuffleStrategy cardShuffleStrategy = new RandomCardShuffleStrategy();

        return new Dealer(cardDeck, cardShuffleStrategy);
    }

    private Players generatePlayers() {
        String readPlayerNames = inputView.readPlayerNames();
        List<String> playerNames = Converter.stringToList(readPlayerNames);

        return new Players(playerNames);
    }

    private BattingAmountRepository initBattingAmountRepository(Players players) {
        BattingAmountRepository repository = new BattingAmountRepository();

        for (int index = 0; index < players.count(); index++) {
            Player player = players.findPlayerByIndex(index);
            BattingAmount battingAmount = replyOnException(() -> readBattingAmount(player));

            repository.save(player, battingAmount);
        }
        return repository;
    }

    private BattingAmount readBattingAmount(Player player) {
        String name = player.getName();
        int readBattingAmount = inputView.readBattingAmount(name);

        return new BattingAmount(readBattingAmount);
    }

    private Map<Player, ResultStatus> playBlackjackGame(Dealer dealer, Players players) {
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);
        blackjackGame.start();

        showCardsAfterFirstDeal(dealer, players);

        dealToParticipants(players, dealer, blackjackGame);
        return blackjackGame.judgeGameResult();
    }

    private void showCardsAfterFirstDeal(Dealer dealer, Players players) {
        ParticipantDto dealerDto = ParticipantDto.from(dealer);
        ParticipantsDto playersDto = ParticipantsDto.toDtoWithoutDealer(players);

        outputView.printInitialSettingMessage(dealerDto, playersDto);
        outputView.printFirstCardOfDealer(dealerDto);
        outputView.printCardsOfAllParticipant(playersDto);
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
            receiveCardUntilStay(player, blackjackGame);

            ParticipantDto participantDto = ParticipantDto.from(player);
            outputView.printCardsOfParticipant(participantDto);
        }
    }

    private void receiveCardUntilStay(Player player, BlackjackGame blackjackGame) {
        GameCommand gameCommand = replyOnException(() -> readGameCommand(player));

        if (gameCommand.isYes()) {
            blackjackGame.dealCardTo(player);
        }
        if (gameCommand.isNo()) {
            player.stay();
        }
    }

    private GameCommand readGameCommand(Player player) {
        String name = player.getName();
        String expression = inputView.readReceiveMoreCardOrNot(name);

        return GameCommand.from(expression);
    }

    private void dealToDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.canReceiveCard()) {
            blackjackGame.dealCardToDealer();
            outputView.printDealerReceiveCard();
        }
    }

    private void showParticipantsCardsWithScore(Dealer dealer, Players players) {
        ParticipantsDto participantsDto = ParticipantsDto.toDtoWithDealer(dealer, players);
        outputView.printCardsWithScore(participantsDto);
    }

    private void showParticipantsProfit(Dealer dealer, BattingAmountRepository repository, Map<Player, ResultStatus> gameResult) {
        ProfitCalculator profitCalculator = new ProfitCalculator(repository);
        ProfitResult profitResult = profitCalculator.calculate(dealer, gameResult);

        ProfitResultDto profitResultDto = ProfitResultDto.from(profitResult);
        outputView.printProfits(profitResultDto);
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
