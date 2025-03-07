package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.GameResultType;
import blackjack.domain.Nickname;
import blackjack.dto.HandState;
import blackjack.dto.HandsAfterDrawingCard;
import blackjack.dto.HandsBeforeDrawingCard;
import blackjack.dto.HiddenDealerHandState;
import blackjack.dto.PlayerWinningResult;
import blackjack.dto.PlayerWinningStatistics;
import java.util.List;

public class OutputView {

    public void printHandsBeforeDrawingCard(HandsBeforeDrawingCard hands) {
        printCardDistributionHeader(hands);
        printHiddenDealerHands(hands.dealerHand());
        for (HandState handState : hands.playerHand()) {
            String playerContent = makeCardContent(handState.nickname(), handState.cards());
            System.out.println(playerContent);
        }
        System.out.println();
    }

    public void printCard(Nickname nickname, List<Card> cardsByPlayer) {
        System.out.println(makeCardContent(nickname, cardsByPlayer));
    }

    public void printDealerHit(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
        System.out.println();
    }

    public void printHandsAfterDrawingCard(HandsAfterDrawingCard handStates) {
        HandState dealerHandState = handStates.dealerHand();
        String dealerContent = makeCardContent(dealerHandState.nickname(), dealerHandState.cards());
        System.out.printf("%s - 결과: %s%n", dealerContent, dealerHandState.point());
        for (HandState handState : handStates.playerHand()) {
            String content = makeCardContent(handState.nickname(), handState.cards());
            System.out.printf("%s - 결과: %s%n", content, handState.point());
        }
        System.out.println();
    }

    public void printPlayerWinningResults(PlayerWinningStatistics playerWinningStatistics) {
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");

        for (GameResultType gameResultType : GameResultType.values()) {
            System.out.print(playerWinningStatistics.getDealerStatistics(gameResultType)
                    + gameResultType.getDescription() + " ");
        }
        System.out.println();

        List<PlayerWinningResult> results = playerWinningStatistics.getPlayerWinningResults();
        for (PlayerWinningResult result : results) {
            System.out.println(result.getNickname().getValue() + ": " + result.getResult().getDescription());
        }
    }

    private void printCardDistributionHeader(HandsBeforeDrawingCard hands) {
        String dealerNameContent = hands.dealerHand().nickname().getValue();
        List<Nickname> playerNicknames = hands.playerHand().stream()
                .map(HandState::nickname)
                .toList();
        String playerNameContent = makeNicknamesContent(playerNicknames);
        String content = String.format("%s와 %s에게 2장을 나누었습니다.", dealerNameContent, playerNameContent);
        System.out.println(content);
    }

    private void printHiddenDealerHands(HiddenDealerHandState handState) {
        String dealerContent = makeCardContent(handState.nickname(), List.of(handState.card()));
        System.out.println(dealerContent);
    }

    private String makeCardContent(Nickname nickname, List<Card> cardsByPlayer) {
        List<String> cards = cardsByPlayer.stream()
                .map(card -> card.getValue().getDescription() + card.getShape())
                .toList();
        String cardContents = String.join(", ", cards);
        return String.format("%s카드: %s", nickname.getValue(), cardContents);
    }

    private String makeNicknamesContent(List<Nickname> nicknames) {
        List<String> nicknameValues = nicknames.stream().map(Nickname::getValue).toList();
        return String.join(", ", nicknameValues);
    }
}
