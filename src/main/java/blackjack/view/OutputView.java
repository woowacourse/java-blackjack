package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.game.dto.ParticipantsDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String JOIN_DELIMITER = ", ";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    private OutputView() {

    }

    public static void printInitialCards(ParticipantsDto participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        System.out.printf("%n%s와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), getPlayerNames(players));
        System.out.printf("%s: %s%n", dealer.getName(), getCardName(dealer.getCards().getValue().get(0)));
        for (Player player : players) {
            printCards(player);
        }
        System.out.println();
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String getCardNames(Cards cards) {
        return cards.getValue().stream()
                .map(OutputView::getCardName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String getCardName(Card card) {
        return card.getDenomination() + card.getSuit();
    }

    public static void printCards(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), getCardNames(player.getCards()));
    }

    public static void printDealerDrawInfo(String name) {
        System.out.printf("%n%s는 %d이하라 한장의 카드를 더 받았습니다.%n", name, Dealer.DRAW_STANDARD);
    }

    public static void printCardsResult(ParticipantsDto participantsDto) {
        System.out.println();
        printCardResult(participantsDto.getDealer());
        for (Player player : participantsDto.getPlayers()) {
            printCardResult(player);
        }
    }

    private static void printCardResult(Participant participant) {
        System.out.printf("%s 카드: %s - 결과: %d%n",
                participant.getName(), getCardNames(participant.getCards()), participant.getCards().sum());
    }

    public static void printGameResult(GameResult gameResult) {
        String stringFormat = "%s: %d%n";
        Map<Player, Long> map = gameResult.getBettingResult();

        System.out.printf("%n## 최종 수익%n");
        System.out.printf(stringFormat, Dealer.DEALER_NAME, gameResult.getDealerProfit());

        for (Player player : map.keySet()) {
            System.out.printf(stringFormat, player.getName(), gameResult.getBettingResult(player));
        }
    }

    public static void printException(Exception e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());
    }
}
