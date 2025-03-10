package blackjack.view;


import blackjack.domain.card.Card;
import blackjack.domain.gamer.GameParticipant;
import blackjack.domain.gamer.GameParticipants;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printPlayersCards(GameParticipants participants) {
        String playerNames = String.join(", ",
                participants.getPlayers().stream()
                        .map(player -> player.getNickname().getValue())
                        .toList());

        System.out.println("딜러와 " + playerNames + "에게 2장을 나누었습니다.");

        participants.getGameParticipants().forEach(this::printParticipantCards);
    }

    public void printParticipantCards(GameParticipant participant) {
        System.out.println(
                participant.getNickname().getValue() + ": " +
                        buildCardContent(participant.getHand().getCards()));
    }

    private String buildCardContent(List<Card> cards) {
        return cards.stream()
                .filter(Card::isNotHidden)
                .map(card -> String.format("%s%s", card.getRank().getDescription(), card.getSuit().getDescription()))
                .collect(Collectors.joining(", "));
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}

//
//    public void printDrawnCardResults(List<DrawnCardResult> drawnCardResults) {
//        for (DrawnCardResult drawnCardResult : drawnCardResults) {
//            String content = buildCardContent(drawnCardResult.nickname(), drawnCardResult.cards());
//            System.out.printf("%s - 결과: %s%n", content, drawnCardResult.point());
//        }
//        System.out.println();
//    }
//
//    public void printPlayerWinningResults(PlayerWinningStatistics playerWinningStatistics) {
//        System.out.println("## 최종 승패");
//        System.out.print("딜러: ");
//
//        for (GameResultType gameResultType : GameResultType.values()) {
//            System.out.print(playerWinningStatistics.getDealerStatistics(gameResultType)
//                    + GameResultType.WIN.getDescription() + " ");
//        }
//        System.out.println();
//
//        List<PlayerWinningResult> results = playerWinningStatistics.getPlayerWinningResults();
//        for (PlayerWinningResult result : results) {
//            System.out.println(result.getNickname().getValue() + ": " + result.getResult().getDescription());
//        }
//    }
//
//    private String buildCardContent(Nickname nickname, List<Card> cardsByPlayer) {
//        List<String> cards = cardsByPlayer.stream()
//                .map(card -> card.getRank().getDescription() + card.getSuit())
//                .toList();
//        String cardContents = String.join(", ", cards);
//        return String.format("%s카드: %s", nickname.getValue(), cardContents);
//    }
//}
