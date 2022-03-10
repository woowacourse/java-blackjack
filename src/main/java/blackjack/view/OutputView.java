package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.GameResult;
import blackjack.dto.CardDto;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printStartInfo(DealerDto dealerDto, List<PlayerDto> players) {
        System.out.println("\n딜러와 " + generateNames(players) + "에게 2장씩 나누었습니다.");
        System.out.println("딜러: " + cardInfo(dealerDto.getCards().get(0)));
        players.forEach(OutputView::printPlayerCardInfo);
        System.out.println();
    }

    private static String generateNames(List<PlayerDto> players) {
        return players.stream()
                .map(PlayerDto::getName)
                .collect(joining(", "));
    }

    public static void printPlayerCardInfo(PlayerDto playerDto) {
        String cardsInfo = playerDto.getCards()
                .stream()
                .map(OutputView::cardInfo)
                .collect(joining(", "));

        System.out.println(playerDto.getName() + ": " + cardsInfo);
    }

    private static String cardInfo(CardDto cardDto) {
        return cardDto.getDenomination() + cardDto.getSuit();
    }

    public static void printDealerDrawableInfo() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultInfo(DealerDto dealerDto, List<PlayerDto> players) {
        System.out.println();
        createDealerResultInfo(dealerDto);
        for (PlayerDto playerDto : players) {
            createPlayerResultInfo(playerDto);
        }
    }

    private static void createPlayerResultInfo(PlayerDto playerDto) {
        String cardsInfo = playerDto.getCards().stream()
                .map(card -> cardInfo(card))
                .collect(joining(", "));

        System.out.println(playerDto.getName() + ": " + cardsInfo + " - 결과: " + playerDto.getTotalScore());
    }

    private static void createDealerResultInfo(DealerDto dealerDto) {
        String cardsInfo = dealerDto.getCards().stream()
                .map(card -> cardInfo(card))
                .collect(joining(", "));

        System.out.println("딜러: " + cardsInfo + " - 결과: " + dealerDto.getTotalScore());
    }

    public static void printDealerGameResult(Map<GameResult, Long> result) {
        Map<GameResult, Long> results = new EnumMap<>(result);
        String resultInfo = results.keySet().stream()
                .map(score -> results.get(score) + score.getValue())
                .collect(Collectors.joining(" "));

        System.out.printf("\n## 최종 승패\n딜러: %s\n", resultInfo);
    }

    public static void printPlayerGameResult(Map<String, GameResult> results) {
        String resultInfo = results.keySet()
                .stream()
                .map(name -> name + ": " + results.get(name).getValue())
                .collect(Collectors.joining("\n"));

        System.out.println(resultInfo);
    }
}

