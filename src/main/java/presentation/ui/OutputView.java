package presentation.ui;

import static constant.Word.*;

import application.dto.RoundResult;
import domain.Card;
import domain.dto.GameResult;
import domain.dto.MemberStatus;
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
        System.out.println();
    }

    public void printFinalMemberStatus(List<MemberStatus> statuses) {
        statuses.forEach(this::printFinalMemberCardAndResult);
        System.out.println();
    }

    public void printGameResult(List<GameResult> gameResults) {
        System.out.println(FINAL_GAME_RESULT_MESSAGE.format());
        gameResults.forEach(this::printMemberResult);
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

    private void printMemberResult(GameResult gameResult) {
        String name = gameResult.name();
        List<Boolean> results = gameResult.result();
        if (name.equals(DEALER.format())) {
            printDealerResult(results, name);
            return;
        }
        if (results.getFirst()) {
            System.out.println(PLAYER_GAME_WIN.format(name));
            return;
        }
        System.out.println(PLAYER_GAME_LOSE.format(name));
    }

    private static void printDealerResult(List<Boolean> results, String name) {
        int win = Math.toIntExact(results.stream().filter(result -> result == true)
                .count());
        int lose = Math.toIntExact(results.stream().filter(result -> result == false)
                .count());
        System.out.println(DEALER_GAME_RESULT.format(name, win, lose));
    }
}
