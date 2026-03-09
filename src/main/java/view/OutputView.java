package view;

import domain.MatchResult;
import domain.card.Card;
import dto.DealerHandDto;
import dto.GameResultDto;
import dto.PlayersHandDto;
import view.message.MatchResultMessage;
import view.message.RankMessage;
import view.message.SuitMessage;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void showInitialHandsOfParticipants(DealerHandDto dealerHandDto, PlayersHandDto playersHandDto) {
        String playerNames = String.join(", ", playersHandDto.getPlayersHand().keySet());
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", playerNames);

        Card firstCard = dealerHandDto.getFirstOpenCard();
        System.out.printf("딜러카드: %s%s\n", RankMessage.of(firstCard.getRank()), SuitMessage.of(firstCard.getSuit()));

        for (Map.Entry<String, List<Card>> playerInfo : playersHandDto.getPlayersHand().entrySet()) {
            String name = playerInfo.getKey();
            List<Card> cards = playerInfo.getValue();
            System.out.printf("%s카드: %s\n", name, formatCards(cards));
        }

        System.out.println();
    }

    public void showPlayerHand(String name, PlayersHandDto playersHandDto) {
        List<Card> cards = playersHandDto.getPlayersHand().get(name);
        System.out.printf("%s카드: %s\n", name, formatCards(cards));
    }

    public void showDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void showDealerStandMessage() {
        System.out.println("\n딜러는 17이상이므로 카드를 받지 않았습니다.");
    }

    public void showDealerHand(DealerHandDto dealerHandDto) {
        System.out.printf("딜러카드: %s\n", formatCards(dealerHandDto.getDealerHand()));
    }

    public void showHandResults(DealerHandDto dealerHandDto, PlayersHandDto playersHandDto) {
        System.out.printf("\n딜러카드: %s - 결과: %d\n", formatCards(dealerHandDto.getDealerHand()), dealerHandDto.getScore());

        for (Map.Entry<String, List<Card>> playerInfo : playersHandDto.getPlayersHand().entrySet()) {
            String name = playerInfo.getKey();
            List<Card> cards = playerInfo.getValue();
            int score = playersHandDto.getPlayersScore().get(name);

            System.out.printf("%s카드: %s - 결과: %d\n", name, formatCards(cards), score);
        }
    }

    public void showGameResult(GameResultDto gameResultDto) {
        System.out.println("\n## 최종 승패");

        String dealerResult = gameResultDto.getDealerResult().entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + MatchResultMessage.of(entry.getKey()))
                .collect(java.util.stream.Collectors.joining(" "));

        System.out.printf("딜러: %s\n", dealerResult);

        for (Map.Entry<String, MatchResult> playersResult : gameResultDto.getPlayersResult().entrySet()) {
            String name = playersResult.getKey();
            String matchResult = MatchResultMessage.of(playersResult.getValue());
            System.out.printf("%s: %s\n", name, matchResult);
        }
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
                .map(card -> RankMessage.of(card.getRank()) + SuitMessage.of(card.getSuit()))
                .collect(java.util.stream.Collectors.joining(", "));
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
