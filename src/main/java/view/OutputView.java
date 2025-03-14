package view;

import static domain.Dealer.DEALER_DRAWABLE_THRESHOLD;

import domain.Card;
import domain.Cards;
import domain.Gamer;
import domain.Player;
import domain.PlayerName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    
    public void printInitialState(Map<PlayerName, Player> playersInfo, Card dealerCard) {
        List<PlayerName> playerNames = new ArrayList<>(playersInfo.keySet());
        List<String> usernames = playerNames.stream()
                .map(PlayerName::username)
                .toList();
        String names = String.join(", ", usernames);

        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", names);
        System.out.printf("딜러카드: %s\n", dealerCard.getCardName());

        for (PlayerName playerName : playerNames) {
            Gamer player = playersInfo.get(playerName);
            Cards cards = player.getCards();
            printGamerCards(playerName.username(), cards);
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

    public void printFinalState(Map<PlayerName, Player> playersInfo, Gamer dealer) {
        System.out.println();
        printGamerCards("딜러", dealer.getCards());
        printGamerScore(dealer);
        playersInfo.forEach((key, value) -> {
            printGamerCards(key.username(), value.getCards());
            printGamerScore(value);
        });
        System.out.println();
    }

    private void printGamerScore(Gamer gamer) {
        System.out.printf(" - 결과: %d\n", gamer.getScore());
    }

    public void printFinalResult(Map<PlayerName, Integer> gameResults, int dealerBenefit) {
        System.out.println("## 최종 수익\n");
        System.out.printf("딜러: %d\n", dealerBenefit);
        gameResults.forEach((key, value) -> {
            System.out.printf("%s: %d\n", key.username(), value);
        });
    }
}
