package presentation.ui;

import static presentation.ui.ViewMessage.*;

import dto.RoundResult;
import domain.card.Card;
import domain.MatchResult;
import dto.GameResult;
import dto.MemberStatus;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";

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
                .filter(result -> result.name().equals(DEALER_NAME))
                .forEach(this::printMemberResult);
        gameResults.stream()
                .filter(result -> !result.name().equals(DEALER_NAME))
                .forEach(this::printMemberResult);
    }

    private void printDistributeMessage(List<MemberStatus> playerStatuses) {
        String playerNames = playerStatuses.stream()
                .map(MemberStatus::memberName)
                .filter(s -> !s.equals(DEALER_NAME))
                .collect(Collectors.joining(", "));
        System.out.println(DISTRIBUTE_MESSAGE.format(DEALER_NAME, playerNames));
    }

    private void printMemberCurrentCard(MemberStatus playerStatus) {
        if (playerStatus.memberName().equals(DEALER_NAME)) {
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
