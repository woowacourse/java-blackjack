package blackjack.view;

import static java.util.stream.Collectors.*;

import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.DealerResultsDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerResultDto;
import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void printStartInfo(DealerDto dealerDto, List<PlayerDto> players) {
        System.out.println("\n딜러와 " + generateNames(players) + "에게 2장씩 나누었습니다.");
        printDealerCardInfo(dealerDto);

        players.forEach(OutputView::printPlayerCardInfo);
        System.out.println();
    }

    private static String generateNames(List<PlayerDto> players) {
        return players.stream()
                .map(PlayerDto::getName)
                .collect(joining(", "));
    }

    private static void printDealerCardInfo(DealerDto dealerDto) {
        System.out.println(dealerDto.getName() + " 카드: " + cardInfo(dealerDto.getCards().get(0)));
    }

    public static void printPlayerCardInfo(PlayerDto playerDto) {
        String cardsInfo = generateCardsInfo(playerDto.getCards());
        System.out.println(playerDto.getName() + " 카드: " + cardsInfo);
    }

    public static void printDealerDrawableInfo() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultInfo(DealerDto dealerDto, List<PlayerDto> players) {
        System.out.println();
        printDealerResultInfo(dealerDto);
        players.forEach(OutputView::printPlayerResultInfo);
    }

    private static void printDealerResultInfo(DealerDto dealerDto) {
        String cardsInfo = generateCardsInfo(dealerDto.getCards());
        System.out.println("딜러: " + cardsInfo + " - 결과: " + dealerDto.getTotalScore());
    }

    private static void printPlayerResultInfo(PlayerDto playerDto) {
        String cardsInfo = generateCardsInfo(playerDto.getCards());
        System.out.println(playerDto.getName() + ": " + cardsInfo + " - 결과: " + playerDto.getTotalScore());
    }

    private static String generateCardsInfo(List<CardDto> cards) {
        return cards.stream()
                .map(OutputView::cardInfo)
                .collect(joining(", "));
    }

    private static String cardInfo(CardDto cardDto) {
        return cardDto.getDenomination() + cardDto.getSuit();
    }

    public static void printGameResult(DealerResultsDto dealerResults, List<PlayerResultDto> playerResults) {
        System.out.println("\n## 최종 승패");
        System.out.println("딜러: " + generateDealerGameResult(dealerResults));
        System.out.println(generatePlayerGameResult(playerResults));
    }

    private static String generateDealerGameResult(DealerResultsDto dealerResults) {
        return dealerResults.getValue()
                .stream()
                .map(dealerResult -> dealerResult.getCount() + dealerResult.getGameResult())
                .collect(joining(" "));
    }

    private static String generatePlayerGameResult(List<PlayerResultDto> playerResults) {
        return playerResults.stream()
                .map(playerResult -> playerResult.getName() + ": " + playerResult.getGameResult())
                .collect(joining("\n"));
    }
}

