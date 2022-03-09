package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final int INIT_DISTRIBUTE_SIZE = 2;
    private static final String BASIC_DISTRIBUTE = "딜러와 %s에게 " + INIT_DISTRIBUTE_SIZE + "을 나누어주었습니다.";


    public static void printPlayersInitCardInfo(final Players players) {
        final List<Participant> participants = players.getParticipants();
        final Dealer dealer = players.getDealer();

        System.out.println();
        System.out.printf((BASIC_DISTRIBUTE) + "%n", String.join(DELIMITER, extractNames(participants)));
        printInitDealerInfo(dealer);
        printParticipantsInfo(participants);
    }

    private static List<String> extractNames(final List<Participant> participants) {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    private static void printInitDealerInfo(final Dealer dealer) {
        Card dealerCard = dealer.getCardOne();
        System.out.println("딜러: " + dealerCard.getScore().getAmount() + dealerCard.getType().getName());
    }

    private static void printParticipantsInfo(final List<Participant> participants) {
        participants.forEach(participant -> {
            System.out.print(participant.getName() + "카드: ");
            printCards(participant.getCards());
        });
        System.out.println();
    }

    private static void printCards(final List<Card> cards) {
        System.out.println(String.join(DELIMITER, convertToText(cards)));
    }

    private static List<String> convertToText(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getScore().getAmount() + card.getType().getName())
                .collect(Collectors.toList());
    }
}
