package blackjack.domain;

import java.util.Arrays;
import java.util.List;

public enum CardNumber {
    ACE("A", List.of(1, 11)),
    TWO("2", List.of(2)),
    THREE("3", List.of(3)),
    FOUR("4", List.of(4)),
    FIVE("5", List.of(5)),
    SIX("6", List.of(6)),
    SEVEN("7", List.of(7)),
    EIGHT("8", List.of(8)),
    NINE("9", List.of(9)),
    TEN("10", List.of(10)),
    JACK("J", List.of(10)),
    QUEEN("Q", List.of(10)),
    KING("K", List.of(10));


    private final String number;
    private final List<Integer> transferNumber;

    CardNumber(String number, List<Integer> transferNumber) {
        this.number = number;
        this.transferNumber = transferNumber;
    }

    public static List<Integer> getScoresByNumber(String number) {
        return Arrays.stream(values())
                .filter(cardNumber -> cardNumber.getNumber().equals(number))
                .map(CardNumber::getTransferNumber)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("숫자를 찾을 수 없습니다."));
    }

    public String getNumber() {
        return number;
    }

    public List<Integer> getTransferNumber() {
        return transferNumber;
    }
}
