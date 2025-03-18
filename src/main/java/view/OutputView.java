package view;

import bet.BetResult;
import card.Card;
import player.Dealer;
import player.Participant;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String DEALER_NAME = "딜러";

    public void printOpenInitialCards(List<Participant> participants) {
        List<String> names = participants.stream()
                .map(Participant::getName)
                .toList();

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", DEALER_NAME, String.join(", ", names));
    }

    public void printInitialCards(List<Card> dealerInitialCards, Map<String, List<Card>> participantsInitialCards) {
        printPlayerCards(DEALER_NAME, dealerInitialCards);
        participantsInitialCards.forEach(this::printPlayerCards);
    }

    public void printPlayerCards(String name, List<Card> cards) {
        System.out.printf("%s카드: %s%n", name,
                String.join(", ", cards.stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()));
    }

    public void printPlayersCardsAndSum(Dealer dealer, List<Participant> participants,
                                        Map<String, Integer> nameAndSum) {
        printDealerCardsAndSum(dealer.computeOptimalSum(), dealer.getCards());
        participants.forEach(participant -> printPlayerCardsAndSum(nameAndSum, participant));
        System.out.println();
    }

    private void printDealerCardsAndSum(int sum, List<Card> cards) {
        System.out.printf("%n%s카드: %s - 결과: %d%n", DEALER_NAME,
                String.join(", ", cards.stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()),
                sum);
    }

    private void printPlayerCardsAndSum(Map<String, Integer> nameAndSum, Participant participant) {
        System.out.printf("%s카드: %s - 결과: %d%n", participant.getName(),
                String.join(", ", participant.getCards().stream()
                        .map(card -> String.format("%s%s", card.getCardNumber().getTitle(),
                                card.getCardShape().getTitle()))
                        .toList()),
                nameAndSum.get(participant.getName()));
    }

    public void printAddCardToDealer() {
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%n%n", DEALER_NAME);
    }

    public void printBettingResult(int dealerTotalAmount, Map<Participant, BetResult> betResults) {
        System.out.println("## 최종 수익");
        System.out.printf("%s: %d%n", DEALER_NAME, dealerTotalAmount);
        betResults.forEach((key, value) -> System.out.printf("%s: %d%n", key.getName(), value.getAmount()));

    }
}
