package view;

import common.GamerDto;
import domain.GameResult;
import domain.PlayerResult;
import domain.card.Card;
import domain.gamer.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final int FIRST_CARD_INDEX = 0;

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
                        .joining(DELIMITER));
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
        return String.join(DELIMITER, cardInfos);
    }

    private static void printResultOfPlayer(List<Player> players, PlayerResult playerResult) {
        for (Player player : players) {
            String name = player.getName();
            String resultState = playerResult.getResultState();
            System.out.println(name + " : " + resultState);
        }
    }
}
