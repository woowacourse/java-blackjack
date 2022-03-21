package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.dto.ResponseCardResultDto;
import blackjack.domain.dto.ResponseInitHandDto;
import blackjack.domain.dto.ResponseProfitDto;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.domain.user.UserName;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    private static final String PRINT_INIT_CARD_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String PRINT_DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PRINT_PROFIT_RESULT_MESSAGE = "## 최종 수익";

    private static final String PRINT_INIT_DEALER_HAND_FORMAT = "딜러: %s";
    private static final String PRINT_GAMER_CARDS_FORMAT = "%s카드: %s";
    private static final String PRINT_USER_RESULT_FORMAT = PRINT_GAMER_CARDS_FORMAT + " - 결과: %d";

    private static final String DEALER_NAME = "딜러";
    private static final String CARD_DELIMITER = ", ";
    private static final String PRINT_RESULTS_DELIMITER = ": ";
    private static final String SPACE_DELIMITER = " ";

    public static void printInitHand(ResponseInitHandDto initHandDto) {
        System.out.println(String.format(PRINT_INIT_CARD_FORMAT, printPlayerNames(initHandDto.getPlayers())));
        printInitDealerHand(initHandDto.getDealer());
        printInitPlayersHand(initHandDto.getPlayers());
    }

    private static String printPlayerNames(Players players) {
        return players.get().stream()
                .map(User::getName)
                .map(UserName::get)
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    private static void printInitDealerHand(User dealer) {
        List<Card> dealerCards = dealer.getCards().get();
        System.out.printf(PRINT_INIT_DEALER_HAND_FORMAT + System.lineSeparator(),
                getCardNumberAndType(dealerCards.get(0)));
    }

    private static void printInitPlayersHand(Players players) {
        players.get()
                .stream()
                .forEach(player -> printHand(player));
        System.out.println();
    }

    public static void printHand(User user) {
        String hand = user.getCards().get().stream()
                .map(ResultView::getCardNumberAndType)
                .collect(Collectors.joining(CARD_DELIMITER));

        System.out.printf(PRINT_GAMER_CARDS_FORMAT + System.lineSeparator(), user.getName().get(), hand);
    }

    public static void printDealerHitMessage() {
        System.out.println(PRINT_DEALER_HIT_MESSAGE);
    }

    public static void printCardsResults(ResponseCardResultDto resultDto) {
        System.out.println();
        Map<String, Hand> results = resultDto.getCardResult();
        for (String userName : results.keySet()) {
            Hand hand = results.get(userName);
            String cards = hand.get()
                    .stream()
                    .map(ResultView::getCardNumberAndType)
                    .collect(Collectors.joining(CARD_DELIMITER));
            System.out.printf(PRINT_USER_RESULT_FORMAT + System.lineSeparator(), userName, cards, hand.getTotalScore());
        }
        System.out.println();
    }

    private static String getCardNumberAndType(Card card) {
        return card.getCardNumber().getNumber() + card.getType().getType();
    }

    public static void printProfit(ResponseProfitDto profitDto) {
        System.out.println(PRINT_PROFIT_RESULT_MESSAGE);
        printDealerProfit(profitDto.getDealerProfit());
        printPlayerProfit(profitDto.getPlayersProfit());
    }

    private static void printDealerProfit(int dealerProfit) {
        System.out.println(DEALER_NAME + PRINT_RESULTS_DELIMITER + dealerProfit);
    }

    private static void printPlayerProfit(Map<String, Integer> playersProfit) {
        for (String name : playersProfit.keySet()) {
            System.out.println(name + PRINT_RESULTS_DELIMITER + playersProfit.get(name));
        }
        System.out.println();
    }
}
