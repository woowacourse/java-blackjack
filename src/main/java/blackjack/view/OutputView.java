package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Referee;
import blackjack.domain.Score;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static final int DEALER_DISPLAY_COUNT = 1;

    private OutputView() {
    }

    public static void printInitCardDeck(Participant dealer, Players players) {
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();
        final String dealerName = dealer.getName().getValue();
        final String processedPlayerNames = String.join(", ", playerNames);
        final String displayCards = convertCardDeckString(dealer.getCardDeck(),
            DEALER_DISPLAY_COUNT);

        System.out.printf("%s와 %s에게 2장을 나누었습니다.", dealerName, processedPlayerNames);
        System.out.println();
        printParticipantsCardDeck(players, playerNames, dealerName, displayCards);
        System.out.println();
    }

    private static String convertCardDeckString(CardDeck cardDeck) {
        return cardDeck.getCards().stream().map(Card::toString).collect(Collectors.joining(", "));
    }

    private static String convertCardDeckString(CardDeck cardDeck, int displayCount) {
        return cardDeck.getCards().stream().map(Card::toString).limit(displayCount)
            .collect(Collectors.joining(", "));
    }

    private static void printParticipantsCardDeck(Players players, List<String> playerNames,
        String dealerName, String displayCards) {
        System.out.printf("%s: %s", dealerName, displayCards);
        System.out.println();

        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index));
        }
    }

    public static void printParticipantCardDeck(Player player) {
        System.out.printf("%s카드: %s", player.getName().getValue(),
            convertCardDeckString(player.getCardDeck()));
        System.out.println();
    }

    public static void printDealerPickMessage(Dealer dealer) {
        System.out.println(
            String.format("%s는 16 이하라 한장의 카드를 더 받았습니다.", dealer.getName().getValue()));
    }

    public static void printFinalCardDeck(Participant dealer, Players players, Referee referee) {
        System.out.println();
        final List<String> playerNames = players.getPlayerNames();

        printParticipantCardDeck(dealer, dealer.getCardDeck().calculateScore());
        for (int index = 0; index < playerNames.size(); index++) {
            printParticipantCardDeck(players.getPlayers().get(index),
                players.getPlayers().get(index).getCardDeck().calculateScore());
        }
        System.out.println();
    }

    private static void printParticipantCardDeck(Participant participant, Score score) {
        final String cards = convertCardDeckString(participant.getCardDeck());
        System.out.println(
            String.format("%s카드: %s - 결과 : %s", participant.getName().getValue(), cards, score));
    }

    public static void printResult(Double dealerWinningMoney, List<Double> playerWinnningMoneys,
        Dealer dealer, Players players) {
        System.out.println("## 최종 수익");
        List<String> names = players.getPlayerNames();
        System.out.println(
            String.format("%s: %d", dealer.getName().getValue(), dealerWinningMoney.longValue()));
        for (int i = 0; i < names.size(); i++) {
            System.out.println(
                String.format("%s: %d", names.get(i), playerWinnningMoneys.get(i).longValue()));
        }
    }

    public static void printBustMessage() {
        System.out.println(">>>Bust<<< 너~무 아쉬워요:( ");
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
