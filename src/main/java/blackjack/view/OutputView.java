package blackjack.view;

import blackjack.view.dto.CardDto;
import blackjack.view.dto.DealerScoreDto;
import blackjack.view.dto.PlayerDto;
import blackjack.view.dto.PlayerScoreDto;
import blackjack.view.dto.ResultDto;
import blackjack.model.BlackjackResult;
import blackjack.view.parser.RankParser;
import blackjack.view.parser.ResultParser;
import blackjack.view.parser.SuitParser;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";

    public void printInitialDeal(List<CardDto> dealerCards, List<PlayerDto> players) {
        String joinedPlayerNames = players.stream()
                .map(PlayerDto::playerName)
                .collect(Collectors.joining(DELIMITER));
        System.out.println("딜러와 " + joinedPlayerNames + "에게 2장을 나누었습니다.");

        printDealerCards(dealerCards);
        for (PlayerDto player : players) {
            printPlayerCards(player.playerName(), player.cards());
        }
        System.out.println();
    }

    public void printPlayerCards(String playerName, List<CardDto> cards) {
        List<String> cardOutputs = parseCardsToOutputs(cards);
        String joinedCards = String.join(DELIMITER, cardOutputs);

        System.out.println(playerName + "카드: " + joinedCards);
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printScore(DealerScoreDto dealer, List<PlayerScoreDto> players) {
        printDealerScore(dealer);
        for (PlayerScoreDto player : players) {
            printPlayerScore(player.playerName(), player.cards(), player.score());
        }
        System.out.println();
    }

    private void printDealerCards(List<CardDto> cards) {
        printPlayerCards("딜러", List.of(cards.getFirst()));
    }

    private void printDealerScore(DealerScoreDto dealer) {
        printPlayerScore("딜러", dealer.cards(), dealer.score());
    }

    private void printPlayerScore(String playerName, List<CardDto> cards, int score) {
        List<String> cardOutputs = parseCardsToOutputs(cards);
        String joinedCards = String.join(DELIMITER, cardOutputs);

        System.out.println(playerName + "카드: " + joinedCards + " - 결과: " + score);
    }

    public void printResult(List<ResultDto> playerResults) {
        System.out.println("## 최종 승패");

        printDealerResult(playerResults);
        playerResults.forEach(this::printPlayerResult);
    }

    private void printDealerResult(List<ResultDto> playerResults) {
        Map<BlackjackResult, Integer> playerResultCounts = playerResults.stream()
                .map(ResultDto::result)
                .collect(Collectors.toMap(
                        Function.identity(),
                        result -> 1,
                        Integer::sum
                ));

        int dealerWinCount = playerResultCounts.getOrDefault(BlackjackResult.LOSE, 0);
        int dealerLoseCount = playerResultCounts.getOrDefault(BlackjackResult.WIN, 0);
        int dealerDrawCount = playerResultCounts.getOrDefault(BlackjackResult.PUSH, 0);

        System.out.println("딜러: " + dealerWinCount + "승 " + dealerLoseCount + "패 " + dealerDrawCount + "무");
    }

    private void printPlayerResult(ResultDto playerResult) {
        String result = ResultParser.parseToLabel(playerResult.result());

        System.out.println(playerResult.playerName() + ": " + result);
    }

    private List<String> parseCardsToOutputs(List<CardDto> cards) {
        return cards.stream()
                .map(this::parseCardToOutput)
                .toList();
    }

    private String parseCardToOutput(CardDto card) {
        String rank = RankParser.parseToLabel(card.rank());
        String suit = SuitParser.parseToLabel(card.suit());

        return rank + suit;
    }
}
