package blackjack.view;

import blackjack.domain.result.Money;
import blackjack.domain.gamers.Name;
import blackjack.domain.card.Card;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class OutputView {

    private static final String DEALER = "딜러";

    public static void printInitialState(DealerDto dealerDto, List<PlayerDto> playerDtos) {
        System.out.println(System.lineSeparator() + DEALER + "와 " + makePlayerName(playerDtos) + "에게 2장을 나누었습니다.");
        System.out.println(DEALER + "카드: " + makeCardsState(dealerDto.getCards()));
        for (PlayerDto playerDto : playerDtos) {
            System.out.println(playerDto.getName().value() + "카드: " + makeCardsState(playerDto.getCards()));
        }
        System.out.println();
    }

    private static String makePlayerName(List<PlayerDto> playerDtos) {
        final StringJoiner nameJoiner = new StringJoiner(", ");
        for (PlayerDto playerDto : playerDtos) {
            nameJoiner.add(playerDto.getName().value());
        }
        return nameJoiner.toString();
    }

    private static String makeCardsState(List<Card> cards) {
        final StringJoiner cardJoiner = new StringJoiner(", ");
        for (Card card : cards) {
            cardJoiner.add(RankTranslator.translate(card.getRank()) + SuitTranslator.translate(card.getSuit()));
        }
        return cardJoiner.toString();
    }

    public static void printCurrentState(final PlayerDto playerDto) {
        System.out.println(playerDto.getName().value() + "카드: " + makeCardsState(playerDto.getCards()));
    }

    public static void printDealerDrawMessage() {
        System.out.println(DEALER + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalState(final DealerDto dealerDto, final List<PlayerDto> playerDtos) {
        System.out.println(DEALER + "카드: " + makeCardsState(dealerDto.getCards())
                + " - 결과: " + dealerDto.getScore());
        for (PlayerDto playerDto : playerDtos) {
            System.out.println(playerDto.getName().value() + "카드: " + makeCardsState(playerDto.getCards())
                    + " - 결과: " + playerDto.getScore());
        }
        System.out.println();
    }

    public static void printFinalProfits(final Money dealerProfit, final Map<Name, Money> playerProfits) {
        System.out.println("## 최종 수익");
        System.out.println(DEALER + ": " + formatMoney(dealerProfit));
        printPlayerProfits(playerProfits);
    }

    private static void printPlayerProfits(final Map<Name, Money> playerProfits) {
        for (final Entry<Name, Money> playerProfit : playerProfits.entrySet()) {
            System.out.println(playerProfit.getKey().value() + ": " + formatMoney(playerProfit.getValue()));
        }
    }

    private static String formatMoney(final Money money) {
        String formattedMoney = String.valueOf(money.value());
        if (money.isInt()) {
            formattedMoney = String.valueOf(money.toInt());
        }
        return formattedMoney;
    }

    public static void printLineSeparator() {
        System.out.println();
    }
}
