package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.BlackjackMatch;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String JOINING_DELIMITER_COMMA = ", ";
    private static final String JOINING_DELIMITER_SPACE = " ";
    private static final int DEFAULT_DEALER_CARD_SIZE = 2;
    private static final String OUTPUT_MESSAGE_INIT_CARD_RESULT =
            NEWLINE.concat("%s와 %s에게 2장의 카드를 나누었습니다.").concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD =
            "%s 카드: %s".concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_DEALER_RECEIVE_CARD_COUNT =
            NEWLINE.concat("%s는 %d장의 카드를 더 받았습니다.").concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_PARTICIPANT_GAME_RESULT =
            "%s 카드: %s - 결과: %d".concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_MATCH = "## 최종 승패";
    private static final String OUTPUT_MESSAGE_MATCH_INFO = "%s: %s".concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_PROFIT = NEWLINE.concat("## 최종 수익");
    private static final String OUTPUT_MESSAGE_PROFIT_RESULT = "%s: %.0f".concat(NEWLINE);

    public static void printErrorMessage(RuntimeException error) {
        System.out.println(error.getMessage());
    }

    public static void printInitCardResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        printInitCardMessage(dealer, players);
        printInitHoldCardMessage(dealer, players);
        System.out.println();
    }

    private static void printInitCardMessage(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(JOINING_DELIMITER_COMMA));
        System.out.printf(OUTPUT_MESSAGE_INIT_CARD_RESULT, dealer.getName(), playerNames);
    }

    private static void printInitHoldCardMessage(Dealer dealer, List<Player> players) {
        Card firstDealerCard = dealer.getCards().get(0);
        String dealerCardInfo = firstDealerCard.getDenominationName() + firstDealerCard.getSymbolName();
        System.out.printf(OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD, dealer.getName(), dealerCardInfo);

        for (Player player : players) {
            printNowHoldCardInfo(player);
        }
    }

    public static void printNowHoldCardInfo(Player player) {
        String playerCardsInfo = player.getCards().stream()
                .map(card -> card.getDenominationName() + card.getSymbolName())
                .collect(Collectors.joining(JOINING_DELIMITER_COMMA));
        System.out.printf(OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD, player.getName(), playerCardsInfo);
    }

    public static void printDealerReceiveCardCount(Dealer dealer) {
        System.out.printf(OUTPUT_MESSAGE_DEALER_RECEIVE_CARD_COUNT,
                dealer.getName(), dealer.getCards().size() - DEFAULT_DEALER_CARD_SIZE);
        System.out.println();
    }

    public static void printGameResult(Dealer dealer, List<Player> players) {
        printParticipantGameResult(dealer);
        for (Player player : players) {
            printParticipantGameResult(player);
        }
    }

    private static void printParticipantGameResult(Participant participant) {
        String playerCardsInfo = participant.getCards().stream()
                .map(card -> card.getDenominationName() + card.getSymbolName())
                .collect(Collectors.joining(JOINING_DELIMITER_COMMA));
        System.out.printf(OUTPUT_MESSAGE_PARTICIPANT_GAME_RESULT, participant.getName(), playerCardsInfo,
                participant.getScore());
    }

    public static void printMatchResult(Dealer dealer, Map<String, Integer> dealerResult, Map<Player, BlackjackMatch> playersResult) {
        System.out.println(NEWLINE.concat(OUTPUT_MESSAGE_MATCH));
        String dealerGameResult = getDealerGameResult(dealerResult);

        System.out.printf(OUTPUT_MESSAGE_MATCH_INFO, dealer.getName(), dealerGameResult);
        playersResult.forEach((key, value) -> System.out.printf(
                OUTPUT_MESSAGE_MATCH_INFO, key.getName(), value.getResult()));
    }

    private static String getDealerGameResult(Map<String, Integer> matchInfo) {
        List<String> matchEssentialInfo = matchInfo.entrySet().stream()
                .filter(resultCount -> resultCount.getValue() > 0)
                .map(resultCount -> resultCount.getValue() + resultCount.getKey())
                .collect(Collectors.toUnmodifiableList());
        return String.join(JOINING_DELIMITER_SPACE, matchEssentialInfo);
    }

    public static void printProfitResult(Dealer dealer, double dealerProfit, Map<Player, BlackjackMatch> playersResult) {
        System.out.println(OUTPUT_MESSAGE_PROFIT);
        System.out.printf(OUTPUT_MESSAGE_PROFIT_RESULT, dealer.getName(), dealerProfit);
        for (Player player : playersResult.keySet()) {
            System.out.printf(OUTPUT_MESSAGE_PROFIT_RESULT, player.getName(), player.calculateProfit(dealer));
        }
    }
}
