package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerScoreDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerScoreDto;
import blackjack.dto.ResultDto;
import blackjack.model.BlackjackResult;
import java.util.List;
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

    public void printResult(List<ResultDto> resultDtos) {
        System.out.println("## 최종 승패");

        long dealerLoseCount = resultDtos.stream()
                .filter(resultDto -> resultDto.result() == BlackjackResult.WIN)
                .count();
        long dealerWinCount = resultDtos.stream()
                .filter(resultDto -> resultDto.result() == BlackjackResult.LOSE)
                .count();
        int playerCount = resultDtos.size();
        System.out.println("딜러: " + dealerWinCount + "승 " + dealerLoseCount + "패 " + (playerCount - dealerLoseCount
                - dealerWinCount) + "무");

        resultDtos.forEach(this::printResult);
    }

    public void printResult(ResultDto resultDto) {
        System.out.println(resultDto.name() + ": " + resultDto.result().getLabel());
    }

    private List<String> parseCardsToOutputs(List<CardDto> cards) {
        return cards.stream()
                .map(this::parseCardToOutput)
                .toList();
    }

    private String parseCardToOutput(CardDto card) {
        String rank = card.rank().getLabel();
        String suit = card.suit().getKorean();

        return rank + suit;
    }
}
