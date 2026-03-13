package view;

import static domain.BlackjackGame.INITIAL_CARD_COUNT;
import static domain.Constant.DELIMITER;
import static domain.participant.Dealer.DEALER_HIT_STAND_BOUNDARY;

import domain.Result;
import dto.DealerDto;
import dto.ParticipantDto;
import dto.PlayerDto;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public void printParticipantsCards(List<PlayerDto> players, DealerDto dealer) {
        printEmptyLine();
        System.out.println(dealer.getName() + "와 " + joinPlayersNameByDelimiter(players) + "에게 " + INITIAL_CARD_COUNT
                + "장을 나누었습니다.");
        System.out.println("딜러카드: " + dealer.getFirstCard());
        printParticipantsCard(players);
        printEmptyLine();
    }

    private String joinPlayersNameByDelimiter(List<PlayerDto> players) {
        return players.stream()
                .map(PlayerDto::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printParticipantsCard(List<PlayerDto> players) {
        for (PlayerDto player : players) {
            printCards(player);
        }
    }

    public void printCards(PlayerDto player) {
        String cardNames = player.getCards().stream()
                        .collect(Collectors.joining(DELIMITER));
        System.out.println(player.getName() + "카드: " + cardNames);
    }


    public void printDealerHitStand(boolean value) {
        printEmptyLine();
        if (value) {
            System.out.println("딜러는 " + DEALER_HIT_STAND_BOUNDARY + "이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("딜러는 " + (DEALER_HIT_STAND_BOUNDARY + 1) + "이상이라 카드를 받지 않았습니다.");
    }


    public void printCardsWithResult(List<PlayerDto> players, DealerDto dealer) {
        printEmptyLine();
        printCardWithResult(dealer);

        for (PlayerDto player : players) {
            printCardWithResult(player);
        }
    }

    private void printCardWithResult(ParticipantDto participant) {
        String cardNames = participant.getCards().stream()
                .collect(Collectors.joining(DELIMITER));
        System.out.println(participant.getName() + "카드: " + cardNames + " - 결과: " + participant.getTotalSum());
    }


    public void printResultStatistics(List<PlayerDto> players, DealerDto dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("## 최종 승패\n");

        int playerWinCount = 0;
        int playerDrawCount = 0;
        int playerLoseCount = 0;

        for (PlayerDto player : players) {
            Result result = Result.judge(player.getTotalSum(), dealer.getTotalSum());
            if (result == Result.WIN) {
                playerWinCount += 1;
            }
            if (result == Result.DRAW) {
                playerDrawCount += 1;
            }
            if (result == Result.LOSE) {
                playerLoseCount += 1;
            }
        }

        sb.append("딜러: " + playerLoseCount + "승 " + playerDrawCount + "무 " + playerWinCount + "패\n");

        for (PlayerDto player : players) {
            sb.append(player.getName() + ": ");
            Result result = Result.judge(player.getTotalSum(), dealer.getTotalSum());

            if (result == Result.WIN) {
                sb.append("승\n");
                continue;
            }
            if (result == Result.LOSE) {
                sb.append("패\n");
                continue;
            }
            sb.append("무\n");
        }
        System.out.println(sb);
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
