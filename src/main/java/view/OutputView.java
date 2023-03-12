package view;

import java.util.List;
import java.util.stream.Collectors;

import betting.Reward;
import card.Card;
import dto.BettingResultDto;
import dto.BettingResultsDto;
import dto.DealerFirstOpenDto;
import dto.DealerWinningDto;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;

public class OutputView {

    private static final String COMMA = ", ";
    private static final String DIVISION = ": ";
    private static final String ERROR_HEADER = "[ERROR] ";

    public void printErrorMessage(Exception exception) {
        System.out.println(ERROR_HEADER + exception.getMessage());
    }

    public void printFirstDrawMessage(List<String> names) {
        String joinedNames = String.join(COMMA, names);
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", joinedNames);
    }

    public void printFirstOpenCards(DealerFirstOpenDto dealerFirstOpen, List<PlayerOpenDto> playersCards) {
        System.out.println(dealerFirstOpen.getName().getValue() + DIVISION + dealerFirstOpen.getCard());
        playersCards.forEach(this::printPlayerCard);
        System.out.println();
    }

    public void printPlayerCard(PlayerOpenDto playerOpenDto) {
        System.out.println(
                playerOpenDto.getName().getValue() + "카드: " + parseCards(playerOpenDto));
    }

    private String parseCards(PlayerOpenDto playerOpenDto) {
        return playerOpenDto.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA));
    }

    public void printDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalResults(PlayerResultDto dealerResult, List<PlayerResultDto> playerResults) {
        System.out.println();
        printFinalResult(dealerResult);
        for (PlayerResultDto result : playerResults) {
            printFinalResult(result);
        }
    }

    private void printFinalResult(PlayerResultDto result) {
        System.out.println(
                result.getName().getValue() + " 카드: " + result.getCards().stream().map(Card::toString).collect(
                        Collectors.joining(COMMA)) + " - 결과: " + result.getScore());
    }

    public void printWinningResults(DealerWinningDto dealerWinningResult, List<PlayerWinningDto> playerWinningResults) {
        System.out.println();
        System.out.println("## 최종 승패");
        String parsedDealerWinningResult = parseDealerWinningResult(dealerWinningResult);
        System.out.println(dealerWinningResult.getName().getValue() + DIVISION + parsedDealerWinningResult);

        playerWinningResults.forEach(playerWinningDto -> System.out.println(
                playerWinningDto.getName().getValue() + DIVISION + playerWinningDto.getResult().getLabel()));
    }

    private String parseDealerWinningResult(DealerWinningDto dealerWinningResult) {
        return dealerWinningResult.getWinningMap()
                .keySet()
                .stream()
                .map(result -> dealerWinningResult.getWinningMap().get(result).getCount() + result.getLabel())
                .collect(Collectors.joining(" "));
    }

    public void printRewardResults(Reward dealerRewardResult, BettingResultsDto playersResultDto) {
        System.out.println("\n## 최종 수익");
        System.out.println("딜러: " + dealerRewardResult.getReward());
        playersResultDto.getBettingResults()
                .stream()
                .map(this::parseMessage)
                .forEach(System.out::println);
    }

    private String parseMessage(BettingResultDto bettingResultDto) {
        return bettingResultDto.getName().getValue() + DIVISION + bettingResultDto.getReward();
    }
}
