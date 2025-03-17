package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GamePlayView {

    private final Scanner scanner;

    public GamePlayView(Scanner scanner) {
        this.scanner = scanner;
    }

    public ParticipantAction readHitOrNot(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        return ParticipantAction.from(scanner.nextLine().trim());
    }

    public void printInitialCards(Card dealerVisibleCard, List<Player> players) {
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", joinPlayerNamesWithComma(players));
        System.out.printf("딜러카드: %s%n", dealerVisibleCard.getDisplayLabel());
        for (Player player : players) {
            printPlayerHand(player);
        }
        System.out.println();
    }

    private String joinPlayerNamesWithComma(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printPlayerHand(Player player) {
        System.out.printf("%s: %s%n", player.getName(), getHand(player));
    }

    private String getHand(Player player) {
        return player.getHandCards()
                .stream()
                .map(Card::getDisplayLabel)
                .collect(Collectors.joining(", "));
    }

    public void printDealerHit(boolean isDealerHit) {
        if (isDealerHit) {
            System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.DEALER_HIT_THRESHOLD);
            return;
        }
        System.out.println();
    }
}
