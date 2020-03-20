package view;

import common.DealerDto;
import common.PlayerDto;
import common.PlayersDto;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String DEALIMETER_FOR_CARDS = ", ";
    private static final int FIRST_CARD_INDEX = 0;
    private static final int INIT_CARDS_SIZE = 2;
    private static final int STANDARD_FOR_DEALER_HIT = 16;


    public static void printResult(DealerDto dealer, PlayersDto players) {
        printStates(dealer, players);
        printProfits(dealer, players);
    }

    private static void printProfits(DealerDto dealer, PlayersDto players) {
        System.out.println("##최종수익");
        System.out.println(String.format("%s: %d", dealer.getName(), dealer.getProfit()));
        for (PlayerDto player : players.getPlayerDtos()) {
            System.out.println(String.format("%s: %d", player.getName(), player.getProfit()));
        }
    }

    private static void printStates(DealerDto dealer, PlayersDto players) {
        playerDealerState(dealer);
        printPlayerStates(players);
        System.out.println();
    }

    private static void playerDealerState(DealerDto dealer) {
        List<String> cardsOfDealer = dealer.getCards();
        System.out.println(String.format("%s 카드: %s - 결과: %d", dealer.getName(), String.join(DEALIMETER_FOR_CARDS, cardsOfDealer), dealer.getScore()));
    }

    private static void printPlayerStates(PlayersDto players) {
        for (PlayerDto player : players.getPlayerDtos()) {
            List<String> cardsOfPlayer = player.getCards();
            System.out.println(String.format("%s 카드: %s - 결과: %d", player.getName(), String.join(DEALIMETER_FOR_CARDS, cardsOfPlayer), player.getScore()));
        }
    }

    public static void printDealerHit(DealerDto dealer, int countOfHit) {
        for (int i = 0; i < countOfHit; i++) {
            System.out.println(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.", dealer.getName(), STANDARD_FOR_DEALER_HIT));
        }
    }

    public static void printCurrentStateOfPlayer(PlayerDto player) {
        List<String> cards = player.getCards();
        System.out.println(String.format("%s카드: %s", player.getName(), String.join(DEALIMETER_FOR_CARDS, cards)));
    }

    public static void printInitGame(DealerDto dealer, PlayersDto players) {
        printCardsDistributed(dealer, players);
        printInitUsersState(dealer, players);
    }

    private static void printCardsDistributed(DealerDto dealer, PlayersDto players) {
        List<String> playerNames = new ArrayList<>();
        for (PlayerDto player : players.getPlayerDtos()) {
            String playerName = player.getName();
            playerNames.add(playerName);
        }

        System.out.println(String.format("%s와 %s에게 %d장의 카드를 나누었습니다.", dealer.getName(), String.join(", ", playerNames), INIT_CARDS_SIZE));
    }

    private static void printInitUsersState(DealerDto dealer, PlayersDto players) {
        printDealerInitState(dealer);
        printPlayerInitStates(players);
    }

    private static void printPlayerInitStates(PlayersDto players) {
        for (PlayerDto player : players.getPlayerDtos()) {
            List<String> cards = player.getCards();
            System.out.println(String.format("%s카드: %s", player.getName(), String.join(DEALIMETER_FOR_CARDS, cards)));
        }
    }

    private static void printDealerInitState(DealerDto dealer) {
        String fristCardOfDealer = getFirstCard(dealer);
        System.out.println(String.format("%s: %s", dealer.getName(), fristCardOfDealer));
    }

    private static String getFirstCard(DealerDto dealer) {
        int firstIndex = 0;
        return dealer.getCards().get(firstIndex);
    }
}
