package presentation.ui;

import static constant.Word.*;

import dto.RoundResult;
import domain.card.Card;
import domain.MatchResult;
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
        gameResults.forEach(this::printMemberResult);
    }

    private void printDistributeMessage(List<MemberStatus> playerStatuses) {
        String playerNames = playerStatuses.stream()
                .map(MemberStatus::playerName)
                .filter(s -> !s.equals(DEALER.getWord()))
                .collect(Collectors.joining(", "));
        System.out.println(DISTRIBUTE_MESSAGE.format(DEALER.getWord(), playerNames));
    }

    private void printMemberCurrentCard(MemberStatus playerStatus) {
        if (playerStatus.playerName().equals(DEALER.getWord())) {
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
        List<MatchResult> results = gameResult.result();
        if (name.equals(DEALER.getWord())) {
            printDealerResult(results, name);
            return;
        }
        printPlayerResult(results.getFirst(), name);
    }

    private void printPlayerResult(MatchResult playerResult, String name) {
        if (playerResult == MatchResult.WIN) {
            System.out.println(PLAYER_GAME_WIN.format(name));
            return;
        }
        if (playerResult == MatchResult.DRAW) {
            System.out.println(PLAYER_GAME_DRAW.format(name));
        }
        System.out.println(PLAYER_GAME_LOSE.format(name));
    }

    private void printDealerResult(List<MatchResult> results, String name) {
        int win = countResult(results, MatchResult.WIN);
        int draw = countResult(results, MatchResult.DRAW);
        int lose = countResult(results, MatchResult.LOSE);

        StringBuilder dealerResult = new StringBuilder();
        dealerResult.append(name).append(": ");
        if (win > 0) dealerResult.append(win).append("승 ");
        if (draw > 0) dealerResult.append(draw).append("무 ");
        if (lose > 0) dealerResult.append(lose).append("패 ");
        System.out.println(dealerResult);
    }

    private int countResult(List<MatchResult> results, MatchResult target) {
        return (int) results.stream()
                .filter(result -> result == target)
                .count();
    }
}
