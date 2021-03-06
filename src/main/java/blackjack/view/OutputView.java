package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CompeteResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DEALER_RECEIVE_MESSAGE = LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALT_BASE_CARDS_FORMAT = "%s와 %s에게 2장의 카드를 나누어주었습니다." + LINE_SEPARATOR;
    private static final String NAME_AND_HAND_FORMAT = "%s 카드: %s";
    private static final String SUMMARY_STATISTICS_FORMAT =  "%s - 결과 : %d" + LINE_SEPARATOR;
    private static final String DEALER_COMPETE_RESULT_FORMAT = "%s : %d승 %d무 %d패" + LINE_SEPARATOR;
    private static final String PLAYER_COMPETE_RESULT_FORMAT = "%s : %s" + LINE_SEPARATOR;
    private static final String CARD_HANDS_DELIMITER = ", ";
    private static final String PLAYER_NAMES_DELIMITER = ", ";
    
    public static void printDealtBaseCards(Dealer dealer, List<Player> players) {
        String playerNames = joinPlayerNames(players);
        System.out.printf(DEALT_BASE_CARDS_FORMAT, dealer.getName(), playerNames);
    
        printNameAndHandExceptFirstCard(dealer);
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
    
    public static void printNameAndHandExceptFirstCard(Dealer dealer) {
        System.out.println(getNameAndHand(dealer, dealer.getSecondCard().getJoinedInitialAndSuit()));
    }
    
    private static String getNameAndHand(Participant participant, List<Card> cards) {
        return String.format(NAME_AND_HAND_FORMAT, participant.getName(), getHand(cards));
    }
    
    public static String getHand(List<Card> cards) {
        return cards.stream()
                    .map(Card::getJoinedInitialAndSuit)
                    .collect(Collectors.joining(CARD_HANDS_DELIMITER));
    }
    
    public static void printNameAndHand(Participant participant) {
        System.out.println(getNameAndHand(participant, participant.getCards()));
    }
    
    private static String getNameAndHand(Participant participant) {
        return getNameAndHand(participant, participant.getCards());
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
        
        final EnumMap<CompeteResult, Long> competeResultMap = dealer.competeResultMap(players);
        long winCount = competeResultMap.getOrDefault(CompeteResult.WIN, 0L);
        long drawCount = competeResultMap.getOrDefault(CompeteResult.DRAW, 0L);
        long defeatCount = competeResultMap.getOrDefault(CompeteResult.DEFEAT, 0L);
        System.out.printf(DEALER_COMPETE_RESULT_FORMAT, dealer.getName(), winCount, drawCount, defeatCount);
    
        for (Player player : players) {
            String competeResult = player.compete(dealer).getCompeteResult();
            System.out.printf(PLAYER_COMPETE_RESULT_FORMAT, player.getName(), competeResult);
        }
    }
}
