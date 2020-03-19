package view;

import common.DealerDto;
import common.GamerDto;
import common.PlayerDto;
import common.PlayersDto;
import domain.GameResult;
import domain.PlayerResult;
import domain.card.Card;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static void printDealerHit(DealerDto dealer) {
        System.out.println(String.format("%s는 %d이하라 한 장의 카드를 더 받았습니다.", dealer.getName(), STANDARD_FOR_DEALER_HIT));
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

    public static void printInitGamersState(GamerDto dealerDto, List<GamerDto> playerDtos) {
        String dealerName = dealerDto.getName();
        String playerNames = parsePlayerNames(playerDtos);
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.\n", dealerName, playerNames);
        printInitDealerCard(dealerDto);
        printInitPlayersCards(playerDtos);
    }

    private static void printInitPlayersCards(List<GamerDto> playerDtos) {
        for (GamerDto playerDto : playerDtos) {
            printGamerCardsState(playerDto);
        }
    }

    private static String parsePlayerNames(List<GamerDto> playerDtos) {
        return playerDtos.stream()
                .map(GamerDto::getName)
                .collect(Collectors
                        .joining(DEALIMETER_FOR_CARDS));
    }

    private static void printInitDealerCard(GamerDto dealerDto) {
        Card card = dealerDto.getCards().get(FIRST_CARD_INDEX);
        String dealerName = dealerDto.getName();
        String wordOfCard = card.getWord();
        String patternOfCard = card.getPattern();
        System.out.printf("%s: %s%s\n", dealerName, wordOfCard, patternOfCard);
    }

    public static void printGamerCardsState(GamerDto gamerDto) {
        String name = gamerDto.getName();
        String cardsToPrint = makeUpCardsToPrint(gamerDto.getCards());
        System.out.printf("%s: %s\n", name, cardsToPrint);
    }

    public static void printDealerHit() {
        System.out.println("딜러 16이하라 한 장의 카드를 더 받습니다.\n");
    }

    public static void printGamerCardsStateWithScore(GamerDto gamerDto, int gamerScore) {
        String name = gamerDto.getName();
        String gamerCardsToPrint = makeUpCardsToPrint(gamerDto.getCards());
        System.out.printf("%s: %s - 결과: %d\n", name, gamerCardsToPrint, gamerScore);
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println("최종 승패");
        printResultOfDealer(gameResult);
        printResultOfPlayers(gameResult);
    }

    private static void printResultOfDealer(GameResult gameResults) {
        int winSizeOfDealer = gameResults.sizeOfDealerWins();
        int drawSizeOfDealer = gameResults.sizeOfDraws();
        int loseSizeOfDealer = gameResults.sizeOfDealerLosses();
        System.out.printf("딜러 : %d승, %d무, %d패 \n", winSizeOfDealer, drawSizeOfDealer, loseSizeOfDealer);
    }

    private static void printResultOfPlayers(GameResult gameResult) {
        for (PlayerResult playerResult : PlayerResult.values()) {
            printResultOfPlayer(gameResult.getPlayersOf(playerResult), playerResult);
        }
    }

    private static String makeUpCardsToPrint(List<Card> cards) {
        List<String> cardInfos = new ArrayList<>();
        for (Card card : cards) {
            String wordOfCard = card.getWord();
            String patternOfCard = card.getPattern();
            String cardInfo = wordOfCard + patternOfCard;
            cardInfos.add(cardInfo);
        }
        return String.join(DEALIMETER_FOR_CARDS, cardInfos);
    }

    private static void printResultOfPlayer(List<Player> players, PlayerResult playerResult) {
        for (Player player : players) {
            String name = player.getName();
            String resultState = playerResult.getResultState();
            System.out.println(name + " : " + resultState);
        }
    }
}
