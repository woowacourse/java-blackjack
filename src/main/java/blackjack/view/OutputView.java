package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.GameResultType;
import blackjack.domain.Nickname;
import blackjack.domain.Player;
import blackjack.domain.PlayerWinningStatistics;
import blackjack.dto.DrawnCardResult;
import blackjack.dto.PlayerWinningResult;
import java.util.List;

public class OutputView {

    public void printCardHeader(List<Player> players) {
        System.out.print("딜러와 ");
        System.out.print(String.join(", ", players.stream().map(player -> player.getNickname().getValue()).toList()));
        System.out.println("에게 2장을 나누었습니다.");
    }

    public void printCard(Nickname nickname, List<Card> cardsByPlayer) {
        System.out.println(buildCardContent(nickname, cardsByPlayer));
    }

    public void printDealerHit(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
        System.out.println();
    }

    public void printDrawnCardResults(List<DrawnCardResult> drawnCardResults) {
        for (DrawnCardResult drawnCardResult : drawnCardResults) {
            String content = buildCardContent(drawnCardResult.nickname(), drawnCardResult.cards());
            System.out.printf("%s - 결과: %s%n", content, drawnCardResult.point());
        }
        System.out.println();
    }

    public void printPlayerWinningResults(PlayerWinningStatistics playerWinningStatistics) {
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");

        for (GameResultType gameResultType : GameResultType.values()) {
            System.out.print(playerWinningStatistics.getDealerStatistics(gameResultType)
                    + GameResultType.WIN.getDescription() + " ");
        }
        System.out.println();

        List<PlayerWinningResult> results = playerWinningStatistics.getPlayerWinningResults();
        for (PlayerWinningResult result : results) {
            System.out.println(result.getNickname().getValue() + ": " + result.getResult().getDescription());
        }
    }

    private String buildCardContent(Nickname nickname, List<Card> cardsByPlayer) {
        List<String> cards = cardsByPlayer.stream()
                .map(card -> card.getValue().getDescription() + card.getShape())
                .toList();
        String cardContents = String.join(", ", cards);
        return String.format("%s카드: %s", nickname.getValue(), cardContents);
    }
}
