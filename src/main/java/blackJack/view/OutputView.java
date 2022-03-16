package blackJack.view;

import blackJack.domain.result.BlackJackGameBoard;
import java.util.List;
import java.util.stream.Collectors;

import blackJack.domain.participant.Participants;
import blackJack.domain.card.Card;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Player;

public class OutputView {

    private static final String NEWLINE = System.getProperty("line.separator");

    private static final String JOINING_DELIMITER_COMMA = ", ";
    private static final int DEFAULT_DEALER_CARD_SIZE = 2;

    private static final String OUTPUT_MESSAGE_INIT_CARD_RESULT =
        NEWLINE.concat("%s와 %s에게 2장의 카드를 나누었습니다.").concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD =
        "%s 카드: %s".concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_DEALER_RECEIVE_CARD_COUNT =
        NEWLINE.concat("%s는 %d장의 카드를 더 받았습니다.").concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_PARTICIPANT_GAME_RESULT =
        "%s 카드: %s - 결과: %d".concat(NEWLINE);
    private static final String OUTPUT_MESSAGE_EARNING = "## 최종 수익";
    private static final String OUTPUT_MESSAGE_EARNING_INFO = "%s: %s".concat(NEWLINE);

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
        Card firstDealerCard = dealer.getCards().iterator().next();
        String dealerCardInfo = firstDealerCard.getDenominationName() + firstDealerCard.getSymbolName();
        System.out.printf(OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD, dealer.getName(), dealerCardInfo);

        for (Player player : players) {
            printNowHoldCardInfo(player);
        }
    }

    public static void printNowHoldCardInfo(Player player) {
        String playerCardsInfo = player.getCards().stream()
            .map(OutputView::createStringCardInfo)
            .collect(Collectors.joining(JOINING_DELIMITER_COMMA));
        System.out.printf(OUTPUT_MESSAGE_PARTICIPANT_HOLD_CARD, player.getName(), playerCardsInfo);
    }

    public static void printDealerReceiveCardCount(Dealer dealer) {
        System.out.printf(OUTPUT_MESSAGE_DEALER_RECEIVE_CARD_COUNT,
            dealer.getName(), dealer.getCards().size() - DEFAULT_DEALER_CARD_SIZE);
        System.out.println();
    }

    public static void printGameResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        printParticipantGameResult(dealer);
        for (Player player : players) {
            printParticipantGameResult(player);
        }
    }

    private static void printParticipantGameResult(Participant participant) {
        String playerCardsInfo = participant.getCards().stream()
            .map(OutputView::createStringCardInfo)
            .collect(Collectors.joining(JOINING_DELIMITER_COMMA));
        System.out.printf(OUTPUT_MESSAGE_PARTICIPANT_GAME_RESULT, participant.getName(), playerCardsInfo,
            participant.getScore());
    }

    public static void printEarningResult(Dealer dealer, BlackJackGameBoard blackJackGameBoard) {
        System.out.println(NEWLINE.concat(OUTPUT_MESSAGE_EARNING));
        System.out.printf(OUTPUT_MESSAGE_EARNING_INFO, dealer.getName(), blackJackGameBoard.getDealerEarning());

        blackJackGameBoard.getPlayerEarnings().forEach((key, value)
                -> System.out.printf(OUTPUT_MESSAGE_EARNING_INFO, key, value));
    }

    private static String createStringCardInfo(Card card) {
        return card.getDenominationName() + card.getSymbolName();
    }
}
