package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.compete.CompeteResult;
import blackjack.domain.compete.CompeteResultGroup;
import blackjack.domain.participant.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    
    private static final int SECOND_CARD_INDEX = 1;
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    private static final String DEALT_BASE_CARDS_FORMAT = "%s와 %s에게 2장의 카드를 나누어주었습니다." + LINE_SEPARATOR;
    private static final String NAME_AND_HAND_FORMAT = "%s 카드: %s";
    
    private static final String DEALER_RECEIVE_MESSAGE = LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    
    private static final String SUMMARY_STATISTICS_FORMAT = "%s - 결과 : %d" + LINE_SEPARATOR;
    private static final String COMPETE_RESULT_FORMAT = "%s : %s" + LINE_SEPARATOR;
    
    private static final String CARD_HANDS_DELIMITER = ", ";
    private static final String PLAYER_NAMES_DELIMITER = ", ";
    
    public static void printDealtBaseCards(Dealer dealer, List<Player> players) {
        String playerNames = joinPlayerNames(players);
        System.out.printf(DEALT_BASE_CARDS_FORMAT, dealer.getName(), playerNames);
        
        System.out.printf(NAME_AND_HAND_FORMAT, dealer.getName(), dealer.getCard(SECOND_CARD_INDEX)
                                                                        .combineInitialAndSuit() + LINE_SEPARATOR);
        for (Player player : players) {
            printNameAndHand(player);
        }
        System.out.println();
    }
    
    private static String joinPlayerNames(List<Player> players) {
        return players.stream()
                      .map(Player::getName)
                      .collect(Collectors.joining(PLAYER_NAMES_DELIMITER));
    }
    
    public static void printNameAndHand(Participant participant) {
        System.out.println(getNameAndHand(participant));
    }
    
    private static String getNameAndHand(Participant participant) {
        return String.format(NAME_AND_HAND_FORMAT, participant.getName(), getJoinedHand(participant));
    }
    
    public static String getJoinedHand(Participant participant) {
        return participant.getCards()
                          .stream()
                          .map(Card::combineInitialAndSuit)
                          .collect(Collectors.joining(CARD_HANDS_DELIMITER));
    }
    
    public static void printSummaryStatistics(Dealer dealer, List<Player> players) {
        System.out.println();
        
        System.out.printf(SUMMARY_STATISTICS_FORMAT, getNameAndHand(dealer), dealer.sumCardHand());
        for (Player player : players) {
            System.out.printf(SUMMARY_STATISTICS_FORMAT, getNameAndHand(player), player.sumCardHand());
        }
    }
    
    public static void printDealerDrewMessage() {
        System.out.println(DEALER_RECEIVE_MESSAGE);
    }
    
    public static void printGameResult(Dealer dealer, List<Player> players) {
        System.out.println();
        
        CompeteResultGroup competeResultGroup = CompeteResult.compete(dealer, players);
        printCompeteResult(dealer.getName(), competeResultGroup.getCompeteResultOfDealer());
        for (Player player : players) {
            printCompeteResult(player.getName(), competeResultGroup.getCompeteResultOfPlayer(player));
        }
    }
    
    private static void printCompeteResult(String name, String competeResult) {
        System.out.printf(COMPETE_RESULT_FORMAT, name, competeResult);
    }
}
