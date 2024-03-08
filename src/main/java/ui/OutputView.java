package ui;

import domain.blackjackgame.ResultStatus;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printCardHand(Dealer dealer, Players players) {
        String result = String.join(", ", players.getPlayerNames());
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), result);

        printDealerCardHand(dealer);
        printPlayersCardHand(players);
        System.out.println();
    }

    private void printDealerCardHand(Dealer dealer) {
        String message = generateDealerCardHandMessage(dealer);
        System.out.println(message);
    }

    private String generateDealerCardHandMessage(Dealer dealer) {
        Card card = dealer.getFirstCardHand();
        String message = card.getDenominationExpression() + card.getEmblem();
        return String.format("%s카드: %s", dealer.getName(), message);
    }

    private void printPlayersCardHand(Players players) {
        for (int i = 0; i < players.count(); i++) {
            Player player = players.findPlayerByIndex(i);
            String message = generateCardHandMessage(player);
            System.out.println(message);
        }
    }

    private String generateCardHandMessage(Participant participant) {
        String name = participant.getName();
        String cardHandMessage = participant.getCardHand()
                .stream()
                .map(card -> card.getDenominationExpression() + card.getEmblem())
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", name, cardHandMessage);
    }

    public void printCardHandAfterHit(Player player) {
        System.out.println(generateCardHandMessage(player));
    }

    public void printDealerReceiveCardMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public void printCardHandWithScore(Dealer dealer, Players players) {
        String dealerCardHand = generateCardHandMessage(dealer);
        System.out.printf("%s - 결과: %d%n", dealerCardHand, dealer.calculateScore());

        for (int i = 0; i < players.count(); i++) {
            Player player = players.findPlayerByIndex(i);
            String playerCardHand = generateCardHandMessage(player);
            System.out.printf("%s - 결과: %d%n", playerCardHand, player.calculateScore());
        }
        System.out.println();
    }

    public void printParticipantResult(Map<ResultStatus, Integer> dealerResult,
                                       Map<Player, ResultStatus> playerResult) {
        System.out.println("## 최종 승패");
        String dealerResultMessage = generateDealerResultMessage(dealerResult);
        System.out.printf("딜러: %s%n", dealerResultMessage);
        playerResult.forEach((key, value) -> System.out.printf("%s: %s%n", key.getName(), value.getName()));
    }

    private String generateDealerResultMessage(Map<ResultStatus, Integer> dealerResult) {
        return dealerResult.entrySet()
                .stream()
                .map(entry -> String.format("%d%s", entry.getValue(), entry.getKey().getName()))
                .collect(Collectors.joining(" "));
    }
}
