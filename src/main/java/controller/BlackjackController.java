package controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.GameResult;
import domain.card.deck.CardDeck;
import domain.card.deck.CardDeckFactory;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.betting.BetAmount;
import domain.participant.name.Name;
import domain.participant.name.Names;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Participants participants = generateParticipants();
        CardDeck cardDeck = CardDeckFactory.createCardDeck(Collections::shuffle);
        BlackjackGame blackjackGame = new BlackjackGame(cardDeck);
        GameResult gameResult = playBlackjackGame(participants, blackjackGame);
        outputView.printGameResult(gameResult);
    }

    private Participants generateParticipants() {
        Names names = repeatUntilSuccess(() -> new Names(inputView.readPlayerNames()));
        return names.getNames().stream()
                .map(this::generatePlayer)
                .collect(collectingAndThen(toList(), Participants::new));
    }

    private Player generatePlayer(Name name) {
        BetAmount betAmount = repeatUntilSuccess(() -> new BetAmount(inputView.readBetAmount(name.getValue())));
        return new Player(name, betAmount);
    }

    private GameResult playBlackjackGame(Participants participants, BlackjackGame blackjackGame) {
        blackjackGame.initGame(participants);
        outputView.printInitialCards(participants);

        for (Player player : participants.getPlayers()) {
            dealToPlayer(player, blackjackGame);
        }
        dealToDealer(participants.getDealer(), blackjackGame);
        outputView.printCardsWithScore(participants);
        return blackjackGame.createGameResult(participants);
    }

    private void dealToPlayer(Player player, BlackjackGame blackjackGame) {
        while (player.isNotFinished() && repeatUntilSuccess(() -> inputView.askForMoreCard(player.getName()))) {
            blackjackGame.dealCardTo(player);
            outputView.printAllCards(player);
        }
    }

    private void dealToDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.isNotFinished()) {
            blackjackGame.dealCardTo(dealer);
            outputView.printDealerReceiveCardMessage();
        }
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        Optional<T> result;
        do {
            result = request(supplier);
        } while (result.isEmpty());
        return result.get();
    }

    private <T> Optional<T> request(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            outputView.printErrorMessage(e);
            return Optional.empty();
        }
    }
}
