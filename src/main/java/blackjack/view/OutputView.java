package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final String HANDOUT_MESSAGE = "딜러와 %s에게 2장의 카드를 나누어 주었습니다.\n";
    private static final String CARD_INFORMATION_FORMAT = "%s: %s\n";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";


    public static void printInitialCardInformation(Participants participants) {
        List<String> participantName = participants.getPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList());

        System.out.printf(HANDOUT_MESSAGE, String.join(NAME_DELIMITER, participantName));

        printInitialDealerCardInformation(participants.getDealer());
        printInitialPlayerCardInformation(participants.getPlayers());
    }

    private static void printInitialPlayerCardInformation(List<Player> players) {
        players.forEach(OutputView::printCards);
    }

    private static void printInitialDealerCardInformation(Dealer dealer) {

        Card dealerFirstCard = dealer.getFirstCard();
        System.out.printf(CARD_INFORMATION_FORMAT, dealer.getName(),
            dealerFirstCard.getDenominationName() + dealerFirstCard.getSuitName());
    }

    public static void printCards(Player player) {
        List<String> participantCardInfo = player.getCards().stream()
            .map(x -> x.getDenominationName() + x.getSuitName())
            .collect(Collectors.toList());
        String cardInfo = String.join(NAME_DELIMITER, participantCardInfo);
        System.out.printf(CARD_INFORMATION_FORMAT, player.getName(), cardInfo);
    }

    public static void printDealerHitMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
    }
}
