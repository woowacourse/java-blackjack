package controller;

import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.GameResult;
import domain.card.deck.CardDeck;
import domain.card.deck.CardDeckFactory;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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
        CardDeck cardDeck = CardDeckFactory.createCardDeck();
        BlackjackGame blackjackGame = new BlackjackGame(cardDeck);
        GameResult gameResult = playBlackjackGame(participants, blackjackGame);
        outputView.printGameResult(gameResult);
    }

    private Participants generateParticipants() {
        List<String> playerNames = repeatUntilSuccess(inputView::readPlayerNames);
        return playerNames.stream()
                .map(this::generatePlayer)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Participants::new));
    }

    private Player generatePlayer(String name) {
        return repeatUntilSuccess(() -> new Player(name, inputView.readBetAmount(name)));
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
        while (player.isNotFinished() && isNecessaryMoreCard(player)) {
            blackjackGame.dealCardTo(player);
            outputView.printAllCards(player);
        }
    }

    private boolean isNecessaryMoreCard(Player player) {
        return repeatUntilSuccess(() -> inputView.askForMoreCard(player.getName()));
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
            result = getResult(supplier);
        } while (result.isEmpty());
        return result.get();
    }

    private <T> Optional<T> getResult(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            outputView.printErrorMessage(e);
            return Optional.empty();
        }
    }
}
