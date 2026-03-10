package view;

import domain.MatchResult;
import domain.card.Card;
import dto.DealerDto;
import dto.GameResultDto;
import dto.PlayersDto;
import dto.ProfitResultDto;
import view.message.MatchResultMessage;
import view.message.RankMessage;
import view.message.SuitMessage;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String COMMA_SEPARATOR = ", ";
    private static final String SPACE_SEPARATOR = " ";

    public void showInitialHandsOfParticipants(DealerDto dealerDto, PlayersDto playersDto) {
        String playerNames = String.join(COMMA_SEPARATOR, playersDto.getPlayersHand().keySet());
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", playerNames);

        Card firstCard = dealerDto.getFirstOpenCard();
        System.out.printf("딜러카드: %s%s\n", RankMessage.of(firstCard.getRank()), SuitMessage.of(firstCard.getSuit()));

        for (Map.Entry<String, List<Card>> playerInfo : playersDto.getPlayersHand().entrySet()) {
            String name = playerInfo.getKey();
            List<Card> cards = playerInfo.getValue();
            System.out.printf("%s카드: %s\n", name, formatCards(cards));
        }

        System.out.println();
    }

    public void showPlayerHand(String name, PlayersDto playersDto) {
        List<Card> cards = playersDto.getPlayersHand().get(name);
        System.out.printf("%s카드: %s\n", name, formatCards(cards));
    }

    public void showDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void showDealerStandMessage() {
        System.out.println("\n딜러는 17이상이므로 카드를 받지 않았습니다.");
    }

    public void showDealerHand(DealerDto dealerDto) {
        System.out.printf("딜러카드: %s\n", formatCards(dealerDto.getDealerHand()));
    }

    public void showHandResultsOfParticipants(DealerDto dealerDto, PlayersDto playersDto) {
        System.out.printf("\n딜러카드: %s - 결과: %d\n", formatCards(dealerDto.getDealerHand()), dealerDto.getScore());

        for (Map.Entry<String, List<Card>> playerInfo : playersDto.getPlayersHand().entrySet()) {
            String name = playerInfo.getKey();
            List<Card> cards = playerInfo.getValue();
            int score = playersDto.getPlayersScore().get(name);

            System.out.printf("%s카드: %s - 결과: %d\n", name, formatCards(cards), score);
        }
    }

    public void showMatchResult(GameResultDto gameResultDto) {
        System.out.println("\n## 최종 승패");

        String dealerResult = gameResultDto.getDealerResult().entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + MatchResultMessage.of(entry.getKey()))
                .collect(java.util.stream.Collectors.joining(SPACE_SEPARATOR));

        System.out.printf("딜러: %s\n", dealerResult);

        for (Map.Entry<String, MatchResult> playersResult : gameResultDto.getPlayersResult().entrySet()) {
            String name = playersResult.getKey();
            String matchResult = MatchResultMessage.of(playersResult.getValue());
            System.out.printf("%s: %s\n", name, matchResult);
        }
    }

    public void showProfitResult(ProfitResultDto profitResultDto) {
        System.out.println("\n## 최종 수익");

        System.out.printf("딜러: %,d\n", profitResultDto.getDealerProfitResult());

        for (Map.Entry<String, Integer> playersProfitResult : profitResultDto.getPlayersProfitResult().entrySet()) {
            String name = playersProfitResult.getKey();
            int profit = playersProfitResult.getValue();
            System.out.printf("%s: %,d\n", name, profit);
        }
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
                .map(card -> RankMessage.of(card.getRank()) + SuitMessage.of(card.getSuit()))
                .collect(java.util.stream.Collectors.joining(COMMA_SEPARATOR));
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
