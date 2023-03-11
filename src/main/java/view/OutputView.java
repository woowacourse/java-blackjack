package view;

import domain.model.Card;
import domain.model.Dealer;
import domain.model.Participant;
import domain.model.Player;
import domain.vo.Profit;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class OutputView {

    private static final String DELIMITER = ",";
    private static final String NEW_LINE = "\n";
    private static final String PRINT_CARDS_MESSAGE = NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.";
    private static final String COLON = ": ";
    private static final String DEALER_RECEIVE_NOTICE = NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_STATE_RESULT_SIGN = " - 결과: ";
    private static final String CARD = " 카드";
    private static final String DEALER_NAME = "딜러";
    private static final String PROFIT_MESSAGE = "## 최종 수익";

    public void printInitialCards(final Dealer dealer, final List<Player> players) {
        printCardsMessage(players);
        printFirstCard(dealer);
        players.forEach(this::printCard);
    }

    private void printCardsMessage(final List<Player> players) {
        final StringJoiner stringJoiner = new StringJoiner(DELIMITER + " ");
        players.stream()
            .map(Player::getName)
            .forEach(stringJoiner::add);
        System.out.printf(PRINT_CARDS_MESSAGE + NEW_LINE, stringJoiner);
    }

    private void printFirstCard(final Dealer dealer) {
        final Card card = dealer.getCards()
            .stream()
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
        System.out.println(DEALER_NAME + COLON + makeCardView(card));
    }

    public void printCard(final Player player) {
        final String stringifyCard = stringifyCard(player);
        System.out.println(player.getName() + COLON + stringifyCard);
    }

    private String stringifyCard(final Participant participant) {
        final StringJoiner stringJoiner = new StringJoiner(DELIMITER + " ");
        participant.getCards()
            .stream()
            .map(this::makeCardView)
            .forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    private String makeCardView(final Card card) {
        return LetterView.from(card.getLetter()).getSign() + SuitView.from(card.getSuit()).getSign();
    }

    public void printDealerReceiveNotice() {
        System.out.println(DEALER_RECEIVE_NOTICE);
    }

    public void printTotalCardState(final Dealer dealer, final List<Player> players) {
        System.out.print(NEW_LINE);
        printPlayerCardState(dealer, DEALER_NAME);
        players.forEach(player -> printPlayerCardState(player, player.getName()));
    }

    private void printPlayerCardState(final Participant participant, final String name) {
        System.out.println(name + CARD + COLON + stringifyCard(participant) + CARD_STATE_RESULT_SIGN
            + participant.getScore().getValue());
    }

    public void printProfits(final Profit dealerProfit, final List<Profit> profits, final List<String> names) {
        System.out.println(NEW_LINE + PROFIT_MESSAGE);
        System.out.println(DEALER_NAME + ": " + (int) dealerProfit.getValue());
        IntStream.range(0, names.size())
            .mapToObj(index -> names.get(index) + ": " + (int) profits.get(index).getValue())
            .forEach(System.out::println);
    }
}
