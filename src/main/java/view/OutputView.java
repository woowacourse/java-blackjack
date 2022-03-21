package view;

import static java.util.stream.Collectors.joining;

import domain.BlackJackResult;
import domain.card.PlayingCard;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Player;
import domain.player.Players;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import util.NameMapper;
import vo.Name;
import vo.Revenue;

public class OutputView {
    private static final String INFO_FOR_INITIAL_SPREAD = "%s와 %s에게 2장을 나누었습니다.%n";
    private static final String INFO_PLAYER_CARD_AND_SCORE = "%s카드: %s - 결과: %d%n";
    private static final String COLON_FOR_NAME_AND_REVENUE = ": ";
    private static final String RESULT_TITLE = "## 최종 수익";
    private static final String CARD_NAME_JOIN_CHARACTER = ", ";
    private static final String GAMBLER_NAME_DELIMITER = ", ";
    private static final DecimalFormat REVENUE_FORMAT = new DecimalFormat("#");

    private OutputView() {
    }

    public static void printInitCards(Players players) {
        Dealer dealer = players.getDealer();
        Gamblers gamblers = players.getGamblers();

        System.out.println();
        System.out.printf(INFO_FOR_INITIAL_SPREAD, dealer.getName(), getGamblerNames(gamblers));

        printInitOpenCards(dealer);
        gamblers.getGamblers()
                .forEach(OutputView::printInitOpenCards);
    }

    private static String getGamblerNames(Gamblers gamblers) {
        return gamblers.getGamblers()
                .stream()
                .map(Gambler::getName)
                .collect(joining(GAMBLER_NAME_DELIMITER));
    }

    private static void printInitOpenCards(Player player) {
        System.out.println(player.getName() + "카드: " + getJoinedCardNames(player.getOpenCards()));
    }

    public static void printCardAndScore(Players players) {
        System.out.println();
        Dealer dealer = players.getDealer();
        Gamblers gamblers = players.getGamblers();

        printDealerCardAndScore(dealer);
        printGamblersCardAndScore(gamblers);
    }

    private static void printGamblersCardAndScore(Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            System.out.printf(INFO_PLAYER_CARD_AND_SCORE,
                    gambler.getName(),
                    getJoinedCardNames(gambler.getHoldingCards()),
                    gambler.getScore());
        }
    }

    private static void printDealerCardAndScore(Dealer dealer) {
        System.out.printf(INFO_PLAYER_CARD_AND_SCORE,
                dealer.getName(),
                getJoinedCardNames(dealer.getHoldingCards()),
                dealer.getScore());
    }

    private static String getJoinedCardNames(List<PlayingCard> cards) {
        return cards.stream()
                .map(NameMapper::getCardName)
                .collect(joining(CARD_NAME_JOIN_CHARACTER));
    }

    public static void printResult(BlackJackResult blackJackResult) {
        System.out.println(System.lineSeparator() + RESULT_TITLE);

        blackJackResult.getBlackjackResult()
                .entrySet()
                .forEach(OutputView::printSingleGamblerResult);
    }

    public static void printSingleGamblerResult(Map.Entry<Name, Revenue> entry) {
        Name name = entry.getKey();
        Revenue revenue = entry.getValue();
        System.out.println(name.getName() + COLON_FOR_NAME_AND_REVENUE + REVENUE_FORMAT.format(revenue.getRevenue()));
    }
}
