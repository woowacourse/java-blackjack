package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DEALT_BASE_CARDS_FORMAT = "%s와 %s에게 2장의 카드를 나누어주었습니다.%s";
    private static final String NAME_AND_CARD_HAND_STATUS_FORMAT = "%s 카드: %s";
    private static final String SUMMARY_STATISTICS_FORMAT =  "%s - 결과 : %d%s";
    private static final String GAME_RESULT_FORMAT = "%s: %s";
    private static final String CARD_HANDS_DELIMITER = ", ";
    private static final String PLAYER_NAMES_DELIMITER = ", ";
    
    public static void printDealtBaseCards(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                                    .map(Player::getName)
                                    .collect(Collectors.joining(PLAYER_NAMES_DELIMITER));
        
        System.out.printf(DEALT_BASE_CARDS_FORMAT, dealer.getName(), playerNames, LINE_SEPARATOR);
    }
    
    public static void printPlayersCardHandStatus(Dealer dealer, List<Player> players) {
        printNameAndHandStatusExceptFirstCard(dealer);
        
        for (Player player : players) {
            printNameAndHandStatus(player);
        }
    
        System.out.println();
    }
    
    public static void printNameAndHandStatusExceptFirstCard(Dealer dealer) {
        System.out.println(getNameAndHandStatusExceptFirstCard(dealer));
    }
    
    public static String getNameAndHandStatusExceptFirstCard(Dealer dealer) {
        return String.format(NAME_AND_CARD_HAND_STATUS_FORMAT, dealer.getName(), getHandStatus(dealer.getCardsExceptFirstCard()));
    }
    
    public static void printNameAndHandStatus(Participant participant) {
        System.out.println(getNameAndHandStatus(participant));
    }
    
    public static String getNameAndHandStatus(Participant participant) {
        return String.format(NAME_AND_CARD_HAND_STATUS_FORMAT, participant.getName(), getHandStatus(participant.getCards()));
    }
    
    public static String getHandStatus(List<Card> cards) {
        return cards.stream()
                    .map(card -> card.getRankInitial() + card.getSuitName())
                    .collect(Collectors.joining(CARD_HANDS_DELIMITER));
    }
    
    public static void printDealerDrewMessage() {
        System.out.println(LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
    
    public static void printSummaryStatistics(Dealer dealer, List<Player> players) {
        System.out.println();
        
        System.out.printf(SUMMARY_STATISTICS_FORMAT, getNameAndHandStatus(dealer), dealer.sumCardHand(), LINE_SEPARATOR);
    
        for (Player player : players) {
            System.out.printf(SUMMARY_STATISTICS_FORMAT, getNameAndHandStatus(player), player.sumCardHand(), LINE_SEPARATOR);
        }
    }
//
//    public static void printGameResult(Dealer dealer, List<Player> players) {
//        ## 최종 승패
//        딜러: 1승 1패
//        pobi: 승
//        jason: 패
//        */
//    }
}
