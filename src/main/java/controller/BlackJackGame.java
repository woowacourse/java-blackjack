package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGame {
    public static final String COMMA = ",";
    public static final int ADDITIONAL_DRAW_COUNT = 1;
    public static final int INITIAL_DRAW_COUNT = 2;
    public static final int HIT_BOUNDARY = 16;
    public static final int BLACK_JACK_COUNT = 21;

    public static void play() {
        Deck deck = new Deck(CardFactory.createCardList());
        PlayerNames playerNames = new PlayerNames(InputView.inputPlayerNames());
        PlayersData playersData = new PlayersData(makePlayersData(playerNames));
        Players players = new Players(playersData, deck);
        Dealer dealer = new Dealer(deck);
        OutputView.printInitialState(players, dealer);
        drawCardToPlayers(players, deck);
        hitOrStayForDealer(dealer, deck);
        GameResult gameResult = new GameResult(players, dealer);
        OutputView.printResult(gameResult, players, dealer);
    }

    private static Map<String, Bet> makePlayersData(PlayerNames playerNames) {
        Map<String, Bet> playerData = new LinkedHashMap<>();
        for (String name : playerNames) {
            playerData.put(name, new Bet(InputView.inputBet(name)));
        }
        return Collections.unmodifiableMap(playerData);
    }

    private static void drawCardToPlayers(final Players players, final Deck deck) {
        for (Player player : players) {
            drawCardEachPlayer(deck, player);
        }
    }

    private static void drawCardEachPlayer(Deck deck, Player player) {
        while (!player.isMoreThanBlackJack()) {
            Answer answer = Answer.getYesOrNoByValue(InputView.inputYesOrNo(player));
            if (!answer.isYes()) {
                break;
            }
            player.additionalDraw(deck);
            OutputView.printPlayerCard(player);
        }
    }

    private static void hitOrStayForDealer(Dealer dealer, Deck deck) {
        if (dealer.isHitBound()) {
            OutputView.printDealerDraw(dealer);
            dealer.additionalDraw(deck);
        }
    }
}
