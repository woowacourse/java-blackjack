package view;

import java.util.List;
import java.util.stream.Collectors;
import model.cards.Card;
import model.participants.Dealer;
import model.participants.Player;
import model.participants.Players;

public class Output {
    private static final int INDEX_START = 0;

    public void printHandDistribution(List<Player> playerMembers) {
        System.out.println();
        StringBuilder sb = new StringBuilder();
        sb.append("딜러와 ");
        sb.append(playerMembers.stream().map(Player::getName).collect(Collectors.joining(", ")));
        sb.append("에게 2장의 카드를 나누었습니다.");
        System.out.println(sb);
    }

    public void printDealerHand(List<Card> cards) {
        StringBuilder sb = new StringBuilder();
        sb.append("딜러 카드: ");
        sb.append(cards.getFirst().toString());
        System.out.println(sb);
    }

    public void printParticipantHand(List<Card> cards, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("카드: ");
        sb.append(cards.stream().map(Card::toString).collect(Collectors.joining(", ")));
        System.out.println(sb);
    }

    public void printFinalHand(List<Card> cards, int score, String... name) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatFinalHandActor(name));
        sb.append("카드: ");
        sb.append(cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", ")));
        sb.append(String.format(" - 결과: %d", score));
        System.out.println(sb);
    }

    private String formatFinalHandActor(String... name) {
        if (name.length == INDEX_START) {
            return "\n딜러 ";
        }
        return name[INDEX_START];
    }

    public void printPlayerBustNotice(boolean isBust, String name) {
        if (isBust) {
            System.out.println(name + "는 21을 초과하여 버스트되었습니다.");
        }
    }

    public void printDealerHitNotice() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printProfits(Dealer dealer, Players players) {
        System.out.println();
        System.out.println("## 최종 수익");
        StringBuilder sb = new StringBuilder();
        sb.append("딜러: ").append(dealer.getWager());
        for (Player player : players.getPlayers()) {
            sb.append("\n").append(player.getName()).append(": ").append(player.getWager());
        }
        System.out.println(sb);
    }
}
