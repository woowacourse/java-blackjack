package view;

import static domain.Constant.DEALER_HIT_STAND_BOUNDARY;
import static domain.Constant.DEFAULT_HAND_NUMBER;
import static domain.Constant.DELIMITER;

import domain.Result;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public void printParticipantsCards(List<Player> players, Dealer dealer) {
        printEmptyLine();
        System.out.println(
                dealer.getName().getValue() + "와 " + joinPlayersNameByDelimiter(players) + "에게 " + DEFAULT_HAND_NUMBER
                        + "장을 나누었습니다.");
        Card dealerCard = dealer.getFirstCard();
        System.out.println("딜러카드: " + dealerCard.getRank().getDisplayValue() + dealerCard.getSuit().getValue());
        printParticipantsCard(players);
        printEmptyLine();
    }

    private String joinPlayersNameByDelimiter(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .map(Name::getValue)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printParticipantsCard(List<Player> players) {
        for (Player player : players) {
            printCards(player);
        }
    }

    public void printCards(Player player) {
        System.out.println(player.getName().getValue() + "카드: " + printCards(player.getCards().getCards()));
    }

    private String printCards(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getRank().getDisplayValue() + card.getSuit().getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    public void printDealerHitStand(boolean value) {
        printEmptyLine();
        if (value) {
            System.out.println("딜러는 " + DEALER_HIT_STAND_BOUNDARY + "이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("딜러는 " + (DEALER_HIT_STAND_BOUNDARY + 1) + "이상이라 카드를 받지 않았습니다.");
    }

    public void printCardsWithResult(List<Player> players, Dealer dealer) {
        printEmptyLine();
        printCardWithResult(dealer);

        for (Player player : players) {
            printCardWithResult(player);
        }
    }

    private void printCardWithResult(Participant participant) {
        System.out.println(
                participant.getName().getValue() + "카드: " + printCards(participant.getCards().getCards()) + " - 결과: "
                        + participant.getTotalSum());
    }

    public void printResultStatistics(List<Player> players, Dealer dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("## 최종 승패\n");

        int playerWinCount = 0;
        int playerDrawCount = 0;
        int playerLoseCount = 0;

        for (Player player : players) {
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

        for (Player player : players) {
            sb.append(player.getName().getValue() + ": ");
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
