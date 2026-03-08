package presentation.ui;

import static constant.Word.*;

import domain.dto.GameResult;
import domain.RoundResult;
import domain.card.Card;
import domain.dto.MemberStatus;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialStatus(List<MemberStatus> playerStatuses) {
        System.out.println();
        printDistributeMessage(playerStatuses);
        playerStatuses.forEach(this::printMemberCurrentCard);
        System.out.println();
    }

    public void printCurrentCard(String playerName, List<Card> cards) {
        String cardNames = cards.stream().map(Card::cardName).collect(Collectors.joining(", "));
        System.out.println(CARD_STATUS.format(playerName, cardNames));
    }

    public void printDealerDrawResult() {
        System.out.println();
        System.out.println(DEALER_DRAW_MESSAGE.format());
        System.out.println();
    }

    public void printFinalMemberStatus(List<MemberStatus> statuses) {
        statuses.forEach(this::printFinalMemberCardAndResult);
        System.out.println();
    }

    public void printGameResult(GameResult gameResult) {
        System.out.println(FINAL_GAME_RESULT_MESSAGE.format());
        printDealerGameResult(
                gameResult.dealerWinAmount(),
                gameResult.dealerLoseAmount()
        );
        printPlayerGameResult(gameResult.playerResults());
    }

    private void printDealerGameResult(int winAmount, int loseAmount) {
        System.out.println(DEALER_GAME_RESULT.format(winAmount, loseAmount));
    }

    private void printPlayerGameResult(Map<String, RoundResult> roundResults) {
        for (Entry<String, RoundResult> round : roundResults.entrySet()) {
            System.out.println(
                    ROUND_RESULT.format(
                            round.getKey(),
                            round.getValue().result()
                    )
            );
        }
    }

    private void printDistributeMessage(List<MemberStatus> playerStatuses) {
        String playerNames = playerStatuses.stream()
                .map(MemberStatus::playerName)
                .filter(s -> !s.equals(DEALER.format()))
                .collect(Collectors.joining(", "));
        System.out.println(DISTRIBUTE_MESSAGE.format(playerNames));
    }

    private void printMemberCurrentCard(MemberStatus playerStatus) {
        if (playerStatus.playerName().equals(DEALER.format())) {
            printDealerCurrentCard(playerStatus);
            return;
        }
        System.out.println(
                playerStatus.playerName()
                        + ": "
                        + playerStatus.cards().stream()
                        .map(Card::cardName)
                        .collect(Collectors.joining(", "))
        );
    }

    private void printDealerCurrentCard(MemberStatus dealerStatus) {
        System.out.println(
                dealerStatus.playerName()
                    + ": "
                    + dealerStatus.cards().getFirst().cardName()
        );
    }

    private void printFinalMemberCardAndResult(MemberStatus status) {
        String cards = status.cards().stream().map(Card::cardName).collect(Collectors.joining(", "));
        System.out.println(CARD_STATUS.format(status.playerName(), cards) + RESULT_MESSAGE.format(status.totalValue()));
    }
}
