package controller;

import model.*;
import model.card.CardFactory;
import model.card.Deck;
import model.result.GameResult;
import model.user.Dealer;
import model.user.Player;
import model.user.Players;
import model.user.data.PlayerNames;
import model.user.data.PlayersData;
import model.user.money.BettingMoney;
import view.InputView;
import view.OutputView;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGame {
    public static final int INITIAL_DRAW_COUNT = 2;
    private static final int ADDITIONAL_DRAW_COUNT = 1;
    public static final String COMMA = ",";

    public static void play() {
        Deck deck = new Deck(CardFactory.createCardList());
        Players players = new Players(initialCreateUsersData());
        Dealer dealer = new Dealer();

        drawInitialCard(deck, players, dealer);

        drawAdditionalCard(deck, players, dealer);

        OutputView.printFinalCardHandResult(players, dealer);

        GameResult gameResult = new GameResult(players, dealer);
        OutputView.printRevenue(gameResult);
    }

    private static PlayersData initialCreateUsersData() {
        PlayerNames playerNames = new PlayerNames(InputView.inputPlayerNames());
        return new PlayersData(makePlayersData(playerNames));
    }

    private static Map<String, BettingMoney> makePlayersData(PlayerNames playerNames) {
        Map<String, BettingMoney> playerData = new LinkedHashMap<>();
        for (String name : playerNames) {
            playerData.put(name, new BettingMoney(InputView.inputBettingMoney(name)));
        }
        return Collections.unmodifiableMap(playerData);
    }

    private static void drawInitialCard(Deck deck, Players players, Dealer dealer) {
        initialDrawCardUsers(players, dealer, deck);
        OutputView.printInitialCards(players, dealer);
        OutputView.printUsersCard(players, dealer);
    }

    private static void drawAdditionalCard(Deck deck, Players players, Dealer dealer) {
        drawCardToPlayers(players, deck);
        hitOrStayForDealer(dealer, deck);
    }

    private static void initialDrawCardUsers(Players players, Dealer dealer, Deck deck) {
        dealer.drawCard(deck, INITIAL_DRAW_COUNT);
        for (Player player : players) {
            player.drawCard(deck, INITIAL_DRAW_COUNT);
        }
    }

    private static void drawCardToPlayers(final Players players, final Deck deck) {
        for (Player player : players) {
            drawCardEachPlayer(deck, player);
        }
    }

    private static void drawCardEachPlayer(Deck deck, Player player) {
        while (!player.isOverBlackJack() && Answer.find(InputView.inputYesOrNo(player)).isYes()) {
            player.drawCard(deck, ADDITIONAL_DRAW_COUNT);
            OutputView.printPlayerCard(player);
        }
        showWhenFirstAnswerIsNo(player);
    }

    private static void showWhenFirstAnswerIsNo(Player player) {
        if (!player.isOverBlackJack()) {
            OutputView.printPlayerCard(player);
        }
    }

    private static void hitOrStayForDealer(Dealer dealer, Deck deck) {
        if (dealer.isHitBound()) {
            OutputView.printDealerDraw(dealer);
            dealer.drawCard(deck, ADDITIONAL_DRAW_COUNT);
        }
    }
}