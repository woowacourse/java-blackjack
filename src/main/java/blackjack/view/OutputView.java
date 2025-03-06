package blackjack.view;

import blackjack.common.Constants;
import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printStartingCardsStatuses(Dealer dealer, Players players) {
        String names = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
        System.out.println(parseDealerCardStatus(dealer));
        for (Player player : players.getPlayers()) {
            System.out.println(parsePlayerCardStatus(player));
        }
    }

    private static String parsePlayerCardStatus(Player player) {
        return player.getName() + "카드: " + parseStartingCardStatus(player.getCardHolder());
    }

    private static String parseDealerCardStatus(Dealer dealer) {
        return "딜러카드: " + dealer.revealFirstCard().toCardName();
    }

    private static String parseStartingCardStatus(CardHolder cardHolder) {
        return cardHolder.getAllCards().stream()
                .map(Card::toCardName)
                .collect(Collectors.joining(", "));

    }

    public static void printMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(Players players, Dealer dealer) {
        System.out.println(parseDealerResult(dealer));
        players.getPlayers()
                .forEach(player -> System.out.println(parsePlayerResult(player)));
    }

    public static void printBustedPlayer(Player player) {
        System.out.println(player.getName() + "는 버스트되어 더 이상 카드를 뽑을 수 없습니다!");
    }

    // TODO: 카드 추가적으로 뽑을 때 busted 돼서 넘어가겠다고 출력
    // TODO: 플레이어 7명
    // TODO: 승패 출력

    private static String parseDealerResult(Dealer dealer) {
        CardHolder cardHolder = dealer.getCardHolder();
        int value = cardHolder.getOptimisticValue();
        return parseDealerCardStatus(dealer) + " - 결과:" + value;
    }

    private static String parsePlayerResult(Player player) {
        String message = parsePlayerCardStatus(player) + " - 결과: ";
        CardHolder cardHolder = player.getCardHolder();
        int value = cardHolder.getOptimisticValue();

        if (value == Constants.BUSTED_VALUE) {
            return message + "Busted!";
        }

        return message + value;
    }


}
