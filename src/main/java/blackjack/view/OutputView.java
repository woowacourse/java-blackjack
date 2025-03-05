package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public void printStartGame(List<String> playerNames) {
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", playerNames));
    }

    public void printParticipant(Participant participant) {
        if (participant instanceof Dealer dealer) {
            String cardResult =
                    dealer.openFirstCard().denomination().getText() + dealer.openFirstCard().suit().getText();
            System.out.printf("딜러카드: %s%n", cardResult);
        }
        if (participant instanceof Player player) {
            String cardResult = player.getCards()
                    .stream()
                    .map(card -> card.denomination().getText() + card.suit().getText())
                    .collect(Collectors.joining(", "));
            System.out.printf("%s카드: %s%n", player.getName(), cardResult);
        }
    }

    public void printAddExtraCardToDealer() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받습니다.");
    }
}
