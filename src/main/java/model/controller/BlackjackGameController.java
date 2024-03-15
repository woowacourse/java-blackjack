package model.controller;

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
import model.blackjackgame.Profit;
import model.blackjackgame.Result;
import model.card.Card;
import model.card.CardDispenser;
import model.card.CardType;
import model.card.Cards;
import model.participants.dealer.Dealer;
import model.participants.player.Player;
import model.participants.player.Players;
import model.participants.player.UpdatedPlayer;
import model.view.InputView;
import model.view.OutputView;

public class BlackjackGameController {

    private static final int CARD_COUNT_FOR_SETTING = 2;
    private static final String NO_HIT_WORD = "n";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = repeatUntilSuccess(inputView::requestPlayersName);
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);
        Bettings bettings = new Bettings(players.getPlayers().stream().map(this::betting).toList());

        cardSettingBeforeGameStart(players, blackjackGame);
        printCardSetting(blackjackGame.getDealer(), blackjackGame.getPlayers());
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer());
        blackjack.playerBlackjackStatus(blackjackGame.getPlayers());

        turnHitPlayers(blackjackGame.getPlayers(), blackjackGame);
        turnHitDealer(blackjackGame);

        printFinalCardStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
        Profit profit = new Profit(bettings);
        profit.createPlayersProfit(blackjackGame.getPlayers(), bettings, result);

        outputView.printFinalProfit(profit);
    }

    private Betting betting(Player player) {
        return new Betting(player, repeatUntilSuccess(inputView::requestBettingMoney, player));
    }

    private void cardSettingBeforeGameStart(Players players, BlackjackGame blackjackGame) {
        List<Card> generatedCards = new ArrayList<>();
        for (int i = 0; i < (players.getPlayers().size() + 1) * CARD_COUNT_FOR_SETTING; i++) {
            generatedCards.add(new Card(CardDispenser.generateCardNumber(), CardDispenser.generateCardShape()));
        }
        Cards cards = new Cards(generatedCards);
        blackjackGame.distributeCardsForSetting(cards);
    }

    private void printCardSetting(Dealer dealer, Players players) {
        outputView.printDistributeCards(dealer, players);
        outputView.printCardsStock(dealer.getName(),
                Collections.singletonList(captureCardType(dealer).get(0)));
        players.getPlayers()
                .forEach(player -> outputView.printCardsStock(player.getName(), captureCardType(player)));
    }

    private void turnHitPlayers(Players players, BlackjackGame blackjackGame) {
        players.getPlayers().forEach(player -> hitOrStay(player, blackjackGame));
    }

    private void hitOrStay(Player player, BlackjackGame blackjackGame) {
        Answer answer = repeatUntilSuccess(inputView::requestHitAnswer, player);
        while (answer.isHit()) {
            Player updatedPlayer = new UpdatedPlayer(blackjackGame, player).player();
            boolean continueHit = blackjackGame.hitForPlayer(updatedPlayer,
                    new Card(CardDispenser.generateCardNumber(), CardDispenser.generateCardShape()));
            answer = hitResultInfo(continueHit, player, blackjackGame);
        }
    }

    private Answer hitResultInfo(boolean continueHit, Player player, BlackjackGame blackjackGame) {
        if (continueHit) {
            outputView.printCardsStock(player.getName(),
                    captureCardType(new UpdatedPlayer(blackjackGame, player).player()));
            return repeatUntilSuccess(inputView::requestHitAnswer, player);
        }
        outputView.printBustInfo(player);
        return new Answer(NO_HIT_WORD);
    }

    private void turnHitDealer(BlackjackGame blackjackGame) {
        boolean dealerHit = blackjackGame.dealerHitsUnderSixteen(
                new Card(CardDispenser.generateCardNumber(), CardDispenser.generateCardShape()));
        outputView.printDealerHitStatus(dealerHit);
    }

    private void printFinalCardStatus(Dealer dealer, Players players) {
        outputView.printFinalCardStatus(dealer.getName(), captureCardType(dealer), dealer.totalNumber());
        players.getPlayers()
                .forEach(player ->
                        outputView.printFinalCardStatus(player.getName(), captureCardType(player),
                                player.totalNumber()));
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

    private List<CardType> captureCardType(Dealer dealer) {
        List<CardType> cardTypes = new ArrayList<>();
        for (int i = 0; i < dealer.cardsSize(); i++) {
            cardTypes.add(new CardType(dealer.getCards().getCards().get(i)));
        }
        return cardTypes;
    }

    private List<CardType> captureCardType(Player player) {
        List<CardType> cardTypes = new ArrayList<>();
        for (int i = 0; i < player.cardsSize(); i++) {
            cardTypes.add(new CardType(player.getCards().getCards().get(i)));
        }
        return cardTypes;
    }
}
