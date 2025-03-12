package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public class OutputView {

    public void printInitCards(Dealer dealer, Players players) {
        List<String> playerNames = players.getPlayersName();
        String names = playerNames.stream()
                .reduce((a, b) -> String.join(", ", a, b))
                .orElse("");

        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");

        Card dealerCard = dealer.getOwnedCards().getFirst();
        String dealerCardContent = dealerCard.getNumber().getValue() + dealerCard.getShape().getValue();
        System.out.printf("딜러카드: %s\n", dealerCardContent);

        for (String name : playerNames) {
            Player player = players.findByName(name);
            System.out.printf("%s카드: %s\n", name, getCardContents(player.getOwnedCards()));
        }
        System.out.println();
    }

    public void printCardsByName(Player player) {
        System.out.printf("%s카드: %s\n", player.getName(), getCardContents(player.getOwnedCards()));
    }

    public void printDealerReceived() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void printFinalCardsContent(Dealer dealer, Players players) {
        System.out.printf("딜러카드: %s - 결과: %d\n", getCardContents(dealer.getOwnedCards()), dealer.getScore());

        List<String> playerNames = players.getPlayersName();
        for (String name : playerNames) {
            Player player = players.findByName(name);
            System.out.printf("%s카드: %s - 결과: %d\n", name, getCardContents(player.getOwnedCards()), player.getScore());
        }
        System.out.println();
    }

    public void printResult(Dealer dealer, Players players) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + dealer.getTotalWinnings());

        List<String> playersName = players.getPlayersName();
        for (String name : playersName) {
            System.out.println(name + ": " + players.findByName(name).getTotalWinnings());
        }
    }

    private String getCardContents(List<Card> cards) {
        return cards
                .stream()
                .map(card -> card.getNumber().getValue() + card.getShape().getValue())
                .reduce((a, b) -> String.join(", ", a, b))
                .orElse("");
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
