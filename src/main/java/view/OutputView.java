package view;

import domain.MatchResult;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.GameResultDto;
import view.message.MatchResultMessage;
import view.message.RankMessage;
import view.message.SuitMessage;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void showInitialHands(Dealer dealer, Players players) {
        String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(java.util.stream.Collectors.joining(", "));

        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", playerNames);

        Card firstCard = dealer.getFirstCard();
        System.out.printf("딜러카드: %s%s\n", RankMessage.of(firstCard.getRank()), SuitMessage.of(firstCard.getSuit()));

        for (Player player : players.getPlayers()) {
            showPlayerHand(player);
        }

        System.out.println();
    }

    public void showPlayerHand(Player player) {
        System.out.printf("%s카드: %s\n", player.getName(), formatCards(player.getCards()));
    }

    public void showDealerHitMessage() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void showDealerStandMessage() {
        System.out.println("\n딜러는 17이상이므로 카드를 받지 않았습니다.");
    }

    public void showDealerHand(Dealer dealer) {
        System.out.printf("딜러카드: %s\n", formatCards(dealer.getCards()));
    }

    public void showHandResults(Dealer dealer, Players players) {
        System.out.printf("\n딜러카드: %s - 결과: %d\n", formatCards(dealer.getCards()), dealer.getScore());

        for (Player player : players.getPlayers()) {
            System.out.printf("%s카드: %s - 결과: %d\n", player.getName(), formatCards(player.getCards()), player.getScore());
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
            System.out.printf("%s: %s\n", playersResult.getKey(), MatchResultMessage.of(playersResult.getValue()));
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
