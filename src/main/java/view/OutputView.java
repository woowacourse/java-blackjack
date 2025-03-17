package view;

import static domain.Dealer.DEALER_DRAWABLE_THRESHOLD;

import domain.Card;
import domain.Cards;
import domain.DealerProfit;
import domain.Gamer;
import domain.Player;
import domain.PlayerName;
import domain.PlayerProfit;
import java.util.List;

public class OutputView {

    public void printInitialState(List<Player> players, Card dealerCard) {
        List<String> usernames = players.stream()
                .map(Player::getPlayerName)
                .map(PlayerName::username)
                .toList();
        String names = String.join(", ", usernames);

        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", names);
        System.out.printf("딜러카드: %s\n", dealerCard.getCardName());

        for (Player player : players) {
            Cards cards = player.getCards();
            printGamerCards(player.getPlayerName().username(), cards);
            System.out.println();
        }
    }

    public void printGamerCards(String username, Cards cards) {
        String userCards = String.join(", ", cards.getCards().stream()
                .map(Card::getCardName)
                .toList());
        System.out.printf("%s카드: %s", username, userCards);
    }

    public void printDealerDrawMoreCard() {
        System.out.printf("\n딜러는 %s이하라 카드를 더 받았습니다.\n", DEALER_DRAWABLE_THRESHOLD);
    }

    public void printFinalState(List<Player> players, Gamer dealer) {
        System.out.println();
        printGamerCards("딜러", dealer.getCards());
        printGamerScore(dealer);
        players.forEach(
                player -> {
                    printGamerCards(player.getPlayerName().username(), player.getCards());
                    printGamerScore(player);
                });
        System.out.println();
    }

    private void printGamerScore(Gamer gamer) {
        System.out.printf(" - 결과: %d\n", gamer.getScore());
    }

    public void printFinalResult(List<PlayerProfit> playerProfits, DealerProfit dealerProfit) {
        System.out.println("## 최종 수익\n");
        System.out.printf("딜러: %d\n", dealerProfit.getDealerProfit());
        playerProfits.forEach(playerProfit -> System.out.printf("%s: %d\n", playerProfit.getPlayerName().username(),
                playerProfit.getPlayerProfit()));
    }
}
