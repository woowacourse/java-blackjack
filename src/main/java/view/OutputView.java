package view;

import domain.GameResultDto;
import domain.MatchResult;
import domain.card.CardsSnapshot;
import domain.dto.InitialDealingResult;
import domain.dto.ParticipantCards;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String LINE_FEED = "\n";

    public void printGameInitResult(Map<String, CardsSnapshot> result) {
        String playersName = result.keySet().stream()
                .filter(name -> !name.equals("딜러"))
                .collect(Collectors.joining(", "));
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", playersName);

        for (Map.Entry<String, CardsSnapshot> entry : result.entrySet()) {
            String name = entry.getKey();

            printParticipantCard(name, entry.getValue().getFormattedCards());
        }
    }

    public void printParticipantCard(String name, String cards) {
        System.out.printf("%s카드: %s%n", name, cards);
    }

    public void printParticipantResult(String name, String cards, int score) {
        System.out.printf("%s카드: %s - 결과: %d%n", name, cards, score);
    }

    public void printCompleteDealerTurn() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printResult(GameResultDto gameResultDto) {
        System.out.println("## 최종 승패");
        printDealerResult(gameResultDto.dealerMatchResult());
        printPlayerResults(gameResultDto.playerMatchResults());
    }

    public void printNewLine() {
        System.out.println();
    }

    private void printDealerResult(EnumMap<MatchResult, Integer> matchResultIntegerEnumMap) {
        System.out.print("딜러: ");
        List<String> messages = new ArrayList<>();
        for (MatchResult matchResult : matchResultIntegerEnumMap.keySet()) {
            int value = matchResultIntegerEnumMap.get(matchResult);
            if (value == 0) {
                continue;
            }
            messages.add(value + matchResult.getResultName());
        }
        System.out.println(String.join(" ", messages));
    }

    private void printPlayerResults(Map<String, MatchResult> stringMatchResultMap) {
        for (Map.Entry<String, MatchResult> entry : stringMatchResultMap.entrySet()) {
            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue().getResultName());
        }
    }

    public void printInitialDealingResult(InitialDealingResult initialDealingResult) {
        ParticipantCards dealer = initialDealingResult.dealerUpCard();
        printlnMessage(Formatter.participantCards(dealer));

        List<ParticipantCards> allPlayerCardInHand = initialDealingResult.allPlayerCardInHand();
        printlnMessage(Formatter.participantCards(allPlayerCardInHand));

        println();
    }

    private void printMessage(String message) {
        System.out.print(message);
    }

    private void println() {
        printMessage(LINE_FEED);
    }

    private void printlnMessage(String message) {
        printMessage(message);
        println();
    }
}
