package view;

import domain.betting.Money;
import domain.card.Card;
import domain.card.Score;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public class OutputView {

    private static final String DEALER_NAME = "딜러";
    private static final int STARTING_CARDS_AMOUNT = 2;

    private final MessageResolver resolver;

    public OutputView(MessageResolver resolver) {
        this.resolver = resolver;
    }

    public void printDistributionMessage(List<Player> players) {
        System.out.printf("%n딜러와 %s에게 %d장을 나누었습니다.%n",
            resolver.playerNamesText(players),
            STARTING_CARDS_AMOUNT);
    }

    public void printStartingCardsOfAllParticipants(Dealer dealer, Players players) {
        System.out.println(resolver.dealerNameAndStartingCardsText(dealer));
        for (Player player : players.getPlayers()) {
            printNameAndCardsOfParticipant(player.getName(), player.getCards());
        }
        System.out.println();
    }

    public void printNameAndCardsOfParticipant(String name, List<Card> cards) {
        System.out.printf("%s 카드: %s%n", name, resolver.cardsText(cards));
    }

    public void printBustMessage(String name) {
        System.out.printf("%s님은 버스트되었습니다.%n", name);
    }

    public void printDealerDrawMessage() {
        System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.MAX_RECEIVABLE_SCORE);
    }

    public void printFinalCardsAndScoresOfAllParticipants(Dealer dealer, Players players) {
        System.out.println();
        printFinalCardsAndScoresOfParticipant(DEALER_NAME, dealer.getCards(), dealer.score());
        for (Player player : players.getPlayers()) {
            printFinalCardsAndScoresOfParticipant(player.getName(), player.getCards(), player.score());
        }
        System.out.println();
    }

    public void printFinalCardsAndScoresOfParticipant(String name, List<Card> cards, Score score) {
        System.out.printf("%s - 결과: %s%n",
            resolver.participantNameAndCardsText(name, cards),
            resolver.scoreText(score));
    }

    public void printPlayerNameAndProfit(String name, Money money) {
        System.out.println("## 최종 결과");
        System.out.printf("%s: %s%n", name, resolver.moneyText(money)); // TODO: for문 쓰기
    }
}
