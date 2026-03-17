package presentation.ui;

import static presentation.ui.ViewMessage.*;

import dto.RoundResult;
import domain.card.Card;
import dto.GameResult;
import dto.MemberStatus;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialStatus(List<MemberStatus> playerStatuses) {
        System.out.println();
        printDistributeMessage(playerStatuses);
        playerStatuses.forEach(this::printMemberCurrentCard);
        System.out.println();
    }

    public void printCurrentCard(String playerName, RoundResult roundResult) {
        String cards = roundResult.cards().stream().map(Card::cardName).collect(Collectors.joining(", "));
        System.out.println(CARD_STATUS.format(playerName, cards));
    }

    public void printDealerDrawOut() {
        System.out.println();
        System.out.println(DEALER_DRAW_MESSAGE.format());
    }

    public void printFinalMemberStatus(List<MemberStatus> statuses) {
        System.out.println();
        statuses.forEach(this::printFinalMemberCardAndResult);
        System.out.println();
    }

    public void printGameResult(List<GameResult> gameResults) {
        System.out.println(FINAL_GAME_RESULT_MESSAGE.format());
        gameResults.stream()
                .filter(GameResult::isDealer)
                .forEach(this::printMemberResult);
        gameResults.stream()
                .filter(result -> !result.isDealer())
                .forEach(this::printMemberResult);
    }

    private void printDistributeMessage(List<MemberStatus> playerStatuses) {
        String dealerName = playerStatuses.stream()
                .filter(MemberStatus::isDealer)
                .map(MemberStatus::memberName)
                .findFirst()
                .orElseThrow();
        String playerNames = playerStatuses.stream()
                .filter(memberStatus -> !memberStatus.isDealer())
                .map(MemberStatus::memberName)
                .collect(Collectors.joining(", "));
        System.out.println(DISTRIBUTE_MESSAGE.format(dealerName, playerNames));
    }

    private void printMemberCurrentCard(MemberStatus playerStatus) {
        if (playerStatus.isDealer()) {
            printDealerCurrentCard(playerStatus);
            return;
        }
        System.out.println(
                playerStatus.memberName()
                        + ": "
                        + playerStatus.cards().stream()
                        .map(Card::cardName)
                        .collect(Collectors.joining(", "))
        );
    }

    private void printDealerCurrentCard(MemberStatus dealerStatus) {
        System.out.println(
                dealerStatus.memberName()
                        + ": "
                        + dealerStatus.cards().getFirst().cardName()
        );
    }

    private void printFinalMemberCardAndResult(MemberStatus status) {
        String cards = status.cards().stream().map(Card::cardName).collect(Collectors.joining(", "));
        System.out.println(CARD_STATUS.format(status.memberName(), cards) + RESULT_MESSAGE.format(status.totalValue()));
    }

    private void printMemberResult(GameResult gameResult) {
        String name = gameResult.name();
        int result = gameResult.result();
        System.out.println(MEMBER_GAME_RESULT_MESSAGE.format(name, result));
    }
}
