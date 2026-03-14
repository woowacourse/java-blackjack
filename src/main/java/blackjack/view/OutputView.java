package blackjack.view;

import blackjack.view.dto.CardDto;
import blackjack.view.dto.DealerScoreDto;
import blackjack.view.dto.PlayerDto;
import blackjack.view.dto.PlayerScoreDto;
import blackjack.view.dto.ResultDto;
import blackjack.view.label.RankLabel;
import blackjack.view.label.SuitLabel;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";

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

    public void printEmptyLine() {
        System.out.println();
    }

    public void printDealerHit() {
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

    public void printErrorMessage(Exception error) {
        System.out.println(error.getMessage());
    }

    private void printDealerCards(List<CardDto> cards) {
        printPlayerCards(DEALER_NAME, List.of(cards.getFirst()));
    }

    private void printDealerScore(DealerScoreDto dealer) {
        printPlayerScore(DEALER_NAME, dealer.cards(), dealer.score());
    }

    private void printPlayerScore(String playerName, List<CardDto> cards, int score) {
        List<String> cardOutputs = parseCardsToOutputs(cards);
        String joinedCards = String.join(DELIMITER, cardOutputs);

        System.out.println(playerName + "카드: " + joinedCards + " - 결과: " + score);
    }

    public void printResult(List<ResultDto> playerResults) {
        System.out.println("## 최종 수익");

        printDealerResult(playerResults);
        playerResults.forEach(this::printPlayerResult);
    }

    private void printDealerResult(List<ResultDto> playerResults) {
        double dealerProfit = playerResults.stream()
                .mapToDouble(ResultDto::profit)
                .sum()
                * -1;

        printPlayerResult(new ResultDto(DEALER_NAME, dealerProfit));
    }

    private void printPlayerResult(ResultDto playerResult) {
        int integerProfit = (int) playerResult.profit();

        System.out.println(playerResult.playerName() + ": " + integerProfit);
    }

    private List<String> parseCardsToOutputs(List<CardDto> cards) {
        return cards.stream()
                .map(this::parseCardToOutput)
                .toList();
    }

    private String parseCardToOutput(CardDto card) {
        String rank = RankLabel.from(card.rank());
        String suit = SuitLabel.from(card.suit());

        return rank + suit;
    }
}
