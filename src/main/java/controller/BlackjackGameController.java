package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import model.blackjackgame.Answer;
import model.blackjackgame.Betting;
import model.blackjackgame.Bettings;
import model.blackjackgame.Blackjack;
import model.blackjackgame.BlackjackGame;
import model.card.Card;
import model.card.Cards;
import model.participants.dealer.Dealer;
import model.participants.player.Player;
import model.participants.player.Players;
import model.result.Profit;
import model.result.Result;
import view.InputView;
import view.OutputView;

public class BlackjackGameController {

    private static final int DEALER_COUNT = 1;
    private static final int CARD_COUNT_FOR_SETTING = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = repeatUntilSuccess(inputView::requestPlayersName);
        BlackjackGame blackjackGame = new BlackjackGame(new Dealer(), players);
        Bettings bettings = new Bettings(players.getPlayers()
                .stream()
                .map(this::betting)
                .toList());
        gameSetting(players, blackjackGame);
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer(), blackjackGame.getPlayers());
        turnHit(blackjackGame);
        Result result = gameResult(blackjackGame, blackjack);
        Profit profit = gameProfit(blackjackGame, bettings, result);
        outputView.printFinalProfit(profit);
    }

    private Betting betting(Player player) {
        return new Betting(player, repeatUntilSuccess(inputView::requestBettingMoney, player));
    }

    private void gameSetting(Players players, BlackjackGame blackjackGame) {
        cardSettingBeforeGameStart(players, blackjackGame);
        printCardSetting(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    private void cardSettingBeforeGameStart(Players players, BlackjackGame blackjackGame) {
        List<Card> generatedCards = new ArrayList<>();
            generatedCards.add(new Card());
        }
        blackjackGame.distributeCardsForSetting(new Cards(generatedCards));
    }

    private void printCardSetting(Dealer dealer, Players players) {
        outputView.printDistributeCards(dealer, players);
        outputView.printCardsStock(dealer.getName(),
                Collections.singletonList(dealer.captureFirstCardType()));
        players.getPlayers()
                .forEach(player -> outputView.printCardsStock(player.getName(), player.captureCardType()));
    }

    private void turnHit(BlackjackGame blackjackGame) {
        turnHitPlayers(blackjackGame.getPlayers(), blackjackGame);
        turnHitDealer(blackjackGame);
    }

    private void turnHitPlayers(Players players, BlackjackGame blackjackGame) {
        players.getPlayers()
                .forEach(player -> hitOrStay(player, blackjackGame));
    }

    private void hitOrStay(Player player, BlackjackGame blackjackGame) {
        Answer answer = new Answer(repeatUntilSuccess(inputView::requestHitAnswer, player));
        while (answer.isHit()) {
            Player updatedPlayer = blackjackGame.updatedPlayer(player);
            boolean continueHit = blackjackGame.hitForPlayer(updatedPlayer, new Card());
            answer = hitResultInfo(continueHit, player, blackjackGame);
        }
    }

    private Answer hitResultInfo(boolean continueHit, Player player, BlackjackGame blackjackGame) {
        if (continueHit) {
            outputView.printCardsStock(player.getName(),
                    blackjackGame.updatedPlayer(player).captureCardType());
            return new Answer(repeatUntilSuccess(inputView::requestHitAnswer, player));
        }
        outputView.printBustInfo(player);
        return new Answer(false);
    }

    private void turnHitDealer(BlackjackGame blackjackGame) {
        boolean dealerHit = blackjackGame.hitForDealer(new Card());
        outputView.printDealerHitStatus(dealerHit);
    }

    private Result gameResult(BlackjackGame blackjackGame, Blackjack blackjack) {
        printFinalCardStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        return new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
    }

    private void printFinalCardStatus(Dealer dealer, Players players) {
        outputView.printFinalCardStatus(dealer.getName(), dealer.captureCardType(), dealer.totalNumber());
        players.getPlayers()
                .forEach(player ->
                        outputView.printFinalCardStatus(player.getName(), player.captureCardType(),
                                player.totalNumber()));
    }

    private Profit gameProfit(BlackjackGame blackjackGame, Bettings bettings, Result result) {
        return new Profit(blackjackGame.getPlayers(), result, bettings);
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            return repeatUntilSuccess(supplier);
        }
    }

    private <T, R> R repeatUntilSuccess(Function<T, R> function, T input) {
        try {
            return function.apply(input);
        } catch (IllegalArgumentException e) {
            return repeatUntilSuccess(function, input);
        }
    }
}
