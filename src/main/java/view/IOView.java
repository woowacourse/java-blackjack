package view;

import common.ExecuteContext;
import common.ExecuteStrategy;
import domain.model.Card;
import domain.model.Dealer;
import domain.model.Participant;
import domain.model.Player;
import domain.model.Players;
import domain.model.Profit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IOView {

    private static final String BLANK_INPUT_INVALID_ERROR_MESSAGE = "빈 값은 허용되지 않습니다.";
    private static final String DELIMITER = ",";
    private static final String INPUT_NAMES_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_CARD_INTENT_REQUEST_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String INPUT_CHECK_LETTER_ERROR_MESSAGE = "허용되지 않는 입력입니다.";
    private static final String INPUT_BATTING_REQUEST_MESSAGE = "%s의 배팅 금액은?";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String NEW_LINE = "\n";
    private static final String PRINT_CARDS_MESSAGE = NEW_LINE + "딜러와 %s에게 2장을 나누었습니다.";
    private static final String COLON = ": ";
    private static final String DEALER_RECEIVE_NOTICE = NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD_STATE_RESULT_SIGN = " - 결과: ";
    private static final String CARD = " 카드";
    private static final String DEALER_NAME = "딜러";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NOT_NUMBER_ERROR_MESSAGE = "숫자만 입력할 수 있습니다.";
    private static final String PROFIT_MESSAGE = "## 최종 수익";

    public List<String> inputNames() {
        return ExecuteContext.workWithExecuteStrategy(() -> {
                System.out.println(INPUT_NAMES_REQUEST_MESSAGE);
                final String input = scanner.nextLine().replace(" ", "");
                checkBlank(input);
                return Arrays.stream(input.split(DELIMITER))
                    .collect(Collectors.toList());
            }
        );
    }

    private void checkBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT_INVALID_ERROR_MESSAGE);
        }
    }

    public boolean inputCardIntent(final String name) {
        return ExecuteContext.workWithExecuteStrategy(() -> {
                System.out.printf(NEW_LINE + INPUT_CARD_INTENT_REQUEST_MESSAGE + NEW_LINE, name);
                final String input = scanner.nextLine();
                checkLetter(input, YES, NO);
                return input.equals(YES);
            }
        );
    }

    private void checkLetter(final String input, final String... expectedLetters) {
        Arrays.stream(expectedLetters)
            .filter(input::equals)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(INPUT_CHECK_LETTER_ERROR_MESSAGE));
    }

    public List<Double> inputBattings(final List<String> names) {
        final List<Double> battings = new ArrayList<>();
        names.stream()
            .<ExecuteStrategy<Boolean>>map(name -> () -> {
                System.out.printf(NEW_LINE + INPUT_BATTING_REQUEST_MESSAGE + NEW_LINE, name);
                return battings.add(getBatting());
            })
            .forEach(ExecuteContext::workWithExecuteStrategy);
        return battings;
    }

    private double getBatting() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }


    public void printInitialCards(final Dealer dealer, final Players players) {
        printCardsMessage(players);
        printFirstCard(dealer);
        IntStream.range(0, players.count())
            .mapToObj(players::get)
            .forEach(this::printCard);
    }

    private void printCardsMessage(final Players players) {
        final StringJoiner stringJoiner = new StringJoiner(DELIMITER + " ");
        players.getNames()
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

    public void printTotalCardState(final Dealer dealer, final Players players) {
        System.out.print(NEW_LINE);
        printPlayerCardState(dealer, DEALER_NAME);
        for (int i = 0; i < players.count(); i++) {
            Player player = players.get(i);
            printPlayerCardState(player, player.getName());
        }
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
