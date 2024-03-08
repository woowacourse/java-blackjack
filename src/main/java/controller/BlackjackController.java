package controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import domain.blackjackgame.BlackjackAction;
import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.GameResult;
import domain.blackjackgame.ResultStatus;
import domain.card.CardDeck;
import domain.card.CardFactory;
import domain.card.Emblem;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
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
        Dealer dealer = generateDealer();
        Players players = new Players(inputView.readPlayerNames());
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
            Player player = players.getPlayerByIndex(i);
            hitUntilStay(blackjackGame, player, i);
        }
    }

    private void hitUntilStay(BlackjackGame blackjackGame, Player player, int playerIndex) {
        BlackjackAction action = selectBlackjackAction(player.getName());
        while (action.isHit()) {
            blackjackGame.dealToPlayer(playerIndex);
            outputView.printCardHandAfterHit(player);
            action = selectBlackjackAction(player.getName());
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
}
