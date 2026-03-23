package view;

import static domain.BlackjackGame.INITIAL_CARD_COUNT;
import static domain.Constant.DELIMITER;
import static domain.participant.Dealer.DEALER_HIT_STAND_BOUNDARY;

import dto.DealerDto;
import dto.ParticipantDto;
import dto.PlayerDto;
import dto.TotalProfitResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public void printParticipantsCards(List<PlayerDto> players, DealerDto dealer) {
        printEmptyLine();
        System.out.println(dealer.getName() + "와 " + joinPlayersNameByDelimiter(players) + "에게 " + INITIAL_CARD_COUNT
                + "장을 나누었습니다.");
        System.out.println("딜러: " + dealer.getFirstCard());
        printParticipantsCard(players);
        printEmptyLine();
    }

    public void printCards(PlayerDto player) {
        String cardNames = player.getCards().stream()
                        .collect(Collectors.joining(DELIMITER));
        System.out.println(player.getName() + "카드: " + cardNames);
    }

    public void printBustMessage(String playerName) {
        System.out.println(playerName + "는 버스트로 패배하였습니다.");
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

    public void printFinalProfit(TotalProfitResponse response) {
        printEmptyLine();
        System.out.println("## 최종 수익");

        response.dealerProfit().forEach((name, profit) -> {
            System.out.println(name + ": " + profit);
        });

        response.playerProfits().forEach((name, profit) -> {
            System.out.println(name + ": " + profit);
        });
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

    private void printCardWithResult(ParticipantDto participant) {
        String cardNames = participant.getCards().stream()
                .collect(Collectors.joining(DELIMITER));
        System.out.println(participant.getName() + "카드: " + cardNames + " - 결과: " + participant.getTotalSum());
    }

    private void printEmptyLine() {
        System.out.println();
    }
}
