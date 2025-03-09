package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.WinningType;
import blackjack.dto.FinalHands;
import blackjack.dto.HandState;
import blackjack.dto.HiddenDealerHandState;
import blackjack.dto.InitialHands;
import blackjack.dto.PlayerWinningResult;
import blackjack.dto.WinningState;
import java.util.List;

public class OutputView {

    public void printInitialHands(InitialHands hands) {
        printCardDistributionHeader(hands);
        printHiddenDealerHands(hands.dealerHand());
        for (HandState handState : hands.playerHand()) {
            String playerContent = makeCardContent(handState.nickname(), handState.cards());
            System.out.println(playerContent);
        }
        System.out.println();
    }

    public void printHitResult(HandState handState) {
        String content = makeCardContent(handState.nickname(), handState.cards());
        System.out.println(content);
    }

    public void printDealerDrawing(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
        System.out.println();
    }

    public void printFinalHands(FinalHands handStates) {
        HandState dealerHandState = handStates.dealerHand();
        String dealerContent = makeCardContent(dealerHandState.nickname(), dealerHandState.cards());
        System.out.printf("%s - 결과: %s%n", dealerContent, dealerHandState.point());
        for (HandState handState : handStates.playerHand()) {
            String content = makeCardContent(handState.nickname(), handState.cards());
            System.out.printf("%s - 결과: %s%n", content, handState.point());
        }
        System.out.println();
    }

    public void printGameResult(WinningState winningState) {
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");

        for (WinningType winningType : WinningType.values()) {
            System.out.print(winningState.getDealerStatistics(winningType)
                    + winningType.getDescription() + " ");
        }
        System.out.println();

        List<PlayerWinningResult> results = winningState.playerWinningResults();
        for (PlayerWinningResult result : results) {
            System.out.println(result.nickname() + ": " + result.winningType().getDescription());
        }
    }

    private void printCardDistributionHeader(InitialHands hands) {
        String dealerNameContent = hands.dealerHand().nickname();
        List<String> playerNicknames = hands.playerHand().stream()
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

    private String makeCardContent(String nickname, List<Card> cardsByPlayer) {
        List<String> cards = cardsByPlayer.stream()
                .map(card -> card.getValue().getDescription() + card.getShape())
                .toList();
        String cardContents = String.join(", ", cards);
        return String.format("%s카드: %s", nickname, cardContents);
    }

    private String makeNicknamesContent(List<String> nicknames) {
        return String.join(", ", nicknames);
    }
}
