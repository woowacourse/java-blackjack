package view;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.DealerDTO;
import dto.PlayerDTO;
import dto.ResultDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DIAMOND = "다이아몬드";
    private static final String CLUB = "클로버";
    private static final String SPADE = "스페이드";
    private static final String HEART = "하트";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";
    private static final String ENTER_LINE = System.lineSeparator();

    public static void printStartMessage(List<PlayerDTO> PlayerDTO) {
        List<String> playerNames = PlayerDTO.stream()
                .map(playerDTO -> playerDTO.getName())
                .collect(Collectors.toList());
        System.out.println(ENTER_LINE + "딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerCard(DealerDTO dealerDTO) {
        System.out.println(dealerDTO.getName() + ": " + dealerDTO.getFirstCards());
    }

    public static void printPlayersCard(List<PlayerDTO> playerDTOs) {
        playerDTOs.stream()
                .map(playerDTO -> printPlayerHand(playerDTO))
                .forEach(System.out::println);
        System.out.println();
    }

    private static String printPlayerHand(PlayerDTO playerDTO) {
        List<String> cards = playerDTO.getCards();
        return playerDTO.getName() + "카드: " + String.join(", ", cards);
    }

    public static void printPlayerCard(PlayerDTO playerDTO) {
        System.out.println(String.join(", ", playerDTO.getCards()));
    }

    public static void printDealerHit() {
        System.out.println(ENTER_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllHands(DealerDTO dealerDTO, List<PlayerDTO> playerDTOs) {
        List<String> dealerCards = dealerDTO.getCards();
        System.out.println(ENTER_LINE + dealerDTO.getName() + " 카드: " + dealerCards.stream().
                collect(Collectors.joining(", ")) + " - 결과: " + dealerDTO.getScore());

        playerDTOs.stream()
                .map(playerDTO -> playerDTO.getName() + "카드: " +
                        playerDTO.getCards().stream()
                                .collect(Collectors.joining(", ")) + " - 결과: " + playerDTO.getScore())
                .forEach(System.out::println);
    }

    public static void printBettingResult(ResultDTO resultDTO) {
        System.out.println(ENTER_LINE + "## 최종 수익");
        printDealerResult(resultDTO.getDealerResult());
        printPlayersResult(resultDTO.getPlayerResult());
    }

    private static void printDealerResult(int dealerBettingResult) {
        System.out.println("딜러: " + dealerBettingResult);
    }


    private static void printPlayersResult(Map<String, Integer> bettingResult) {
        bettingResult.forEach((playerName, betAmount) -> System.out.println(playerName + ": " + betAmount));
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
