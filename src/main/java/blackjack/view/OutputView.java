package blackjack.view;

import static blackjack.constant.Rule.DEALER_HIT_STANDARD_SCORE;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.ParticipantResult;
import blackjack.domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String CARD_DELIMITER = ", ";
    private static final String RESULT_DELIMITER = " ";
    private static final String COLON_AND_BLANK = ": ";

    public static void printInitialCards(final Dealer dealer, final Participants participants) {
        printDealMessage(dealer, participants);
        printDealerCard(dealer);
        printParticipantsCards(participants);
        printNewLine();
    }

    private static void printDealMessage(final Dealer dealer, final Participants participants) {
        final List<String> names = new ArrayList<>();
        for (Participant participant : participants) {
            names.add(participant.getName());
        }
        System.out.println("\n" + dealer.getName() + "와 " + String.join(CARD_DELIMITER, names) + "에게 2장의 나누었습니다.");
    }

    private static void printDealerCard(final Dealer dealer) {
        System.out.println(dealer.getName() + COLON_AND_BLANK + dealer.getCards().iterator().next());
    }

    private static void printParticipantsCards(final Participants participants) {
        for (Participant participant : participants) {
            printPlayerCards(participant);
        }
    }

    public static void printPlayerCards(final Player player) {
        final List<String> cards = new ArrayList<>();
        for (Card card : player.getCards()) {
            cards.add(card.toString());
        }
        System.out.println(player.getName() + "카드: " + String.join(CARD_DELIMITER, cards));
    }

    public static void printDealerGetCardMessage(final Dealer dealer) {
        System.out.println(dealer.getName() + "는 " + DEALER_HIT_STANDARD_SCORE.getValue() + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printTotalScore(final Player player, final int totalScore) {
        final List<String> cards = new ArrayList<>();
        for (Card card : player.getCards()) {
            cards.add(card.toString());
        }
        System.out.println(player.getName() + "카드: " + String.join(CARD_DELIMITER, cards) + " - 결과: " + totalScore);
    }

    public static void printTotalResult(final Dealer dealer, final DealerResult dealerResult,
                                        final ParticipantResult results) {
        System.out.println("\n## 최종 승패");
        printDealerResult(dealer, dealerResult);
        printParticipantResult(results);
    }

    private static void printDealerResult(final Dealer dealer, final DealerResult result) {
        final Map<Result, Integer> dealerResult = result.getResult();

        final String dealerResultString = dealerResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(Collectors.joining(RESULT_DELIMITER));

        System.out.println(dealer.getName() + COLON_AND_BLANK + dealerResultString);
    }

    private static void printParticipantResult(final ParticipantResult result) {
        final Map<Participant, Result> participantResult = result.getResult();

        participantResult.keySet()
                .forEach(participant -> System.out.println(
                        participant.getName() + COLON_AND_BLANK + participantResult.get(participant).getName()));
    }

    public static void printNewLine() {
        System.out.println();
    }
}
