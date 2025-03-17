package view;

import bet.Bet;
import card.Card;
import player.Player;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printOpenInitialCards(Player dealer, List<Player> participants) {
        List<String> names = participants.stream()
                .map(Player::getName)
                .toList();

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), String.join(", ", names));
    }

    public void printInitialCards(Map<String, List<Card>> initialCards) {
        initialCards.forEach(this::printPlayerCards);
    }

    public void printPlayerCards(String name, List<Card> cards) {
        System.out.printf("%s카드: %s%n", name,
                String.join(", ", cards.stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()));
    }

    public void printPlayersCardsAndSum(Player dealer, List<Player> participants,
                                               Map<String, Integer> nameAndSum) {
        printPlayerCardsAndSum(nameAndSum, dealer);
        participants.forEach(player -> printPlayerCardsAndSum(nameAndSum, player));
        System.out.println();
    }

    private void printPlayerCardsAndSum(Map<String, Integer> nameAndSum, Player player) {
        System.out.printf("%s카드: %s - 결과: %d%n", player.getName(),
                String.join(", ", player.getCards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()),
                nameAndSum.get(player.getName()));
    }

    public void printAddCardToDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.%n");
    }

    public void printBettingResult(int dealerTotalAmount, Map<Player, Bet> wager) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d%n", dealerTotalAmount);
        wager.forEach((key, value) -> System.out.printf("%s: %d%n", key.getName(), value.getAmount()));

    }
}
