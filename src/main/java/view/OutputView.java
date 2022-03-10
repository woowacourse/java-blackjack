package view;

import static java.util.stream.Collectors.joining;

import domain.BlackJackResult;
import domain.PlayerDto;
import domain.card.PlayingCard;
import domain.player.Dealer;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String INITIAL_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.%n";

    private OutputView() {
    }

    public static void print(List<String> playerNames) {
        final String joinNames = String.join(", ", playerNames);

        System.out.printf(INITIAL_MESSAGE, joinNames);
    }

    public static void printCards(final PlayerDto playerDto) {
        System.out.println(playerDto.getName() + ": " + playerDto.getJoinedCardNames());
    }

    public static String getCardNames(List<PlayingCard> playingCards) {
        return playingCards.stream()
                .map(PlayingCard::getCardName)
                .collect(joining(", "));
    }

    public static void printSpreadAnnouncement(String dealerName, String playerNames) {
        System.out.println();
        System.out.println(dealerName + "와 " + playerNames + "에게 2장을 나누었습니다.");
    }

    public static void printSingleCardForDealer(PlayerDto dealerDto) {
        System.out.println(dealerDto.getName() + ": " + dealerDto.getFirstCardName());
    }

    public static void printDealerAddCard(Dealer dealer) {
        System.out.println();
        System.out.println(dealer.getName() + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardAndScore(PlayerDto playerDto) {
        System.out.println(playerDto.getName() + "카드: " + playerDto.getJoinedCardNames() + " - 결과: "
                + playerDto.getScore());
    }

    public static void printBust(final PlayerDto playerDto) {
        System.out.println(playerDto.getName() + "님 버스트로 패배하였습니다.");
    }

    public static void printResult(final BlackJackResult blackJackResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        final Map<Boolean, Integer> dealerResult = blackJackResult.getDealerResult();
        System.out.println("딜러: " + dealerResult.get(true) + "승 " + dealerResult.get(false) + "패");

        final Map<String, Boolean> playerResult = blackJackResult.getPlayerResult();

        playerResult.forEach((key, value) -> System.out.println(key + ": " + (value ? "승" : "패")));
    }

    public static void printTwoCardsForGamblers(List<PlayerDto> playerDtos) {
        playerDtos.forEach(
                playerDto -> System.out.println(playerDto.getName() + ": " + playerDto.getJoinedCardNames())
        );
    }

    public static void printLineSeparator() {
        System.out.println();
    }
}
