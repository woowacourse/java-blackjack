package blackjackgame.view;

import blackjackgame.dto.CardDto;
import blackjackgame.dto.DealerResultDto;
import blackjackgame.dto.GuestResultDto;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";

    public void printFirstDealerCards(final String playerName, final List<CardDto> cards) {
        CardDto cardDto = cards.get(1);
        System.out.printf("%s%s: %s%s", System.lineSeparator(), playerName, cardDto.getValue(), cardDto.getSymbol());
    }

    public void printCards(final String playerName, final List<CardDto> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.printf("%s%s: ", System.lineSeparator(), playerName);
        for (final CardDto cardDto : cards) {
            stringBuilder.append(cardDto.getValue())
                    .append(cardDto.getSymbol())
                    .append(DELIMITER);
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(DELIMITER));
        System.out.print(stringBuilder);
    }

    public void dealerAddCard() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScore(final int score) {
        System.out.print(" - 결과: " + score);
    }

    public void printResult(final List<DealerResultDto> dealerResult, final List<GuestResultDto> guestResult) {
        System.out.println();
        StringBuilder stringBuilder = new StringBuilder("## 최종 승패" + System.lineSeparator() + "딜러: ");
        appendDealerResult(dealerResult, stringBuilder);
        appendGuestsResult(guestResult, stringBuilder);
        System.out.println(System.lineSeparator() + stringBuilder);
    }

    private void appendDealerResult(final List<DealerResultDto> dealerResult, final StringBuilder stringBuilder) {
        for (final DealerResultDto dealerResultDto : dealerResult) {
            stringBuilder.append(dealerResultDto.getOutcomeNumber())
                    .append(dealerResultDto.getOutCome())
                    .append(" ");
        }
    }

    private void appendGuestsResult(final List<GuestResultDto> guestResult, final StringBuilder stringBuilder) {
        for (final GuestResultDto guestResultDto : guestResult) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(guestResultDto.getName())
                    .append(": ")
                    .append(guestResultDto.getOutcome());
        }
    }
}
