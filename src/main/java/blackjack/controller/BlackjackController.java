package blackjack.controller;

import blackjack.domain.blackjackgame.BlackjackAction;
import blackjack.domain.blackjackgame.BlackjackGame;
import blackjack.domain.blackjackgame.GameResult;
import blackjack.domain.blackjackgame.ResultStatus;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Emblem;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.ui.InputView;
import blackjack.ui.OutputView;

import java.util.*;
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

    public void start() {
        Dealer dealer = generateDealer();
        Players players = replyOnException(this::generatePlayers);
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        GameResult gameResult = playBlackjackGame(dealer, players, blackjackGame);
        showGameResult(gameResult);
    }

    private Dealer generateDealer() {
        CardDeck cardDeck = generateCardDeck();
        return new Dealer(cardDeck, Collections::shuffle);
    }

    private CardDeck generateCardDeck() {
        return Arrays.stream(Emblem.values())
                .map(CardFactory::create)
                .flatMap(Collection::stream)
                .collect(collectingAndThen(toList(), CardDeck::new));
    }

    private Players generatePlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return new Players(playerNames);
    }

    private GameResult playBlackjackGame(Dealer dealer, Players players, BlackjackGame blackjackGame) {
        blackjackGame.startGame();
        outputView.printCardHand(dealer, players);

        dealToParticipants(dealer, players, blackjackGame);
        return blackjackGame.createGameResult();
    }

    private void dealToParticipants(Dealer dealer, Players players, BlackjackGame blackjackGame) {
        dealToPlayers(players, blackjackGame);
        dealToDealer(blackjackGame);
        outputView.printCardHandWithScore(dealer, players);
    }

    private void dealToPlayers(Players players, BlackjackGame blackjackGame) {
        for (int i = 0; i < players.count(); i++) {
            Player player = players.findPlayerByIndex(i);
            dealToPlayer(blackjackGame, i, player);
        }
    }

    private void dealToPlayer(BlackjackGame blackjackGame, int playerIndex, Player player) {
        while (player.canReceiveCard()) {
            hitUntilStay(blackjackGame, playerIndex, player);
        }
    }

    private void hitUntilStay(final BlackjackGame blackjackGame, final int playerIndex, final Player player) {
        BlackjackAction action = replyOnException(() -> selectBlackjackAction(player.getName()));

        if (action.isHit()) {
            blackjackGame.dealToPlayer(playerIndex);
            outputView.printCardHandAfterHit(player);
        }
    }

    private BlackjackAction selectBlackjackAction(String playerName) {
        String action = inputView.readBlackjackAction(playerName);
        return BlackjackAction.from(action);
    }

    private void dealToDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.shouldDealerDrawCard()) {
            blackjackGame.dealToDealer();
            outputView.printDealerReceiveCardMessage();
        }
    }

    private void showGameResult(GameResult gameResult) {
        Map<ResultStatus, Integer> dealerResult = gameResult.getDealerResult();
        Map<Player, ResultStatus> playerResult = gameResult.getPlayerResult();

        outputView.printParticipantResult(dealerResult, playerResult);
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
