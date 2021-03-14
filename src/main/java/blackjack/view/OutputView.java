package blackjack.view;

import blackjack.domain.WinningMoney;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DRAW_TWO_CARDS_FORM =
        "%s와 %s에게 2장을 나누었습니다." + System.lineSeparator();
    private static final String CARD_PRINT_FORM = "%s : %s" + System.lineSeparator();
    private static final String FINAL_CARD_PRINT_FORM =
        "%s : %s - 결과 : %d" + System.lineSeparator();
    private static final String MONEY_PRINT_FORM = "%s : %d" + System.lineSeparator();
    private static final String DEALER_DRAWABLE_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_WINNING_MONEY = "## 최종 수익";
    private static final String JOIN_DELIMITER = ", ";
    private static final int FIRST_INDEX = 0;

    public static void printFinalCardForm(List<ShowDeckDto> showDeckDtos) {
        for (ShowDeckDto showDeckDto : showDeckDtos) {
            System.out.printf(FINAL_CARD_PRINT_FORM,
                showDeckDto.name(),
                String.join(JOIN_DELIMITER, showDeckDto.cards()),
                showDeckDto.score()
            );
        }
    }

    public static void printDealerDrawCard() {
        System.out.println(DEALER_DRAWABLE_MESSAGE);
    }

    public static void drawTwoCards(ShowDeckDto dealer, List<ShowDeckDto> participants) {
        String names = participants.stream().map(ShowDeckDto::name)
            .collect(Collectors.joining(JOIN_DELIMITER));
        System.out.printf(DRAW_TWO_CARDS_FORM, dealer.name(), names);

        printCurrentDeck(dealer, participants);
    }

    private static void printCurrentDeck(ShowDeckDto dealer, List<ShowDeckDto> participants) {
        System.out.printf(CARD_PRINT_FORM, dealer.name(), dealer.cards().get(FIRST_INDEX));
        participants.forEach(participant -> {
            String cards = String.join(JOIN_DELIMITER, participant.cards());
            System.out.printf(CARD_PRINT_FORM, participant.name(), cards);
        });
    }

    public static void printCurrentDeck(ShowDeckDto showDeckDto) {
        System.out.printf(CARD_PRINT_FORM,
            showDeckDto.name(),
            String.join(JOIN_DELIMITER,
                showDeckDto.cards())
        );
    }

    public static void printMoneyResult(List<WinningMoney> winningMoneyResult) {
        System.out.println(FINAL_WINNING_MONEY);
        for (WinningMoney winningMoney : winningMoneyResult) {
            System.out.printf(MONEY_PRINT_FORM, winningMoney.name(), winningMoney.winningMoney());
        }
    }
}
