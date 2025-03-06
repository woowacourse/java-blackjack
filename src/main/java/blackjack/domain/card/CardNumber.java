package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardNumber {
    NUMBER_A(1, List.of(1, 11)),
    NUMBER_2(2, List.of(2)),
    NUMBER_3(3, List.of(3)),
    NUMBER_4(4, List.of(4)),
    NUMBER_5(5, List.of(5)),
    NUMBER_6(6, List.of(6)),
    NUMBER_7(7, List.of(7)),
    NUMBER_8(8, List.of(8)),
    NUMBER_9(9, List.of(9)),
    NUMBER_10(10, List.of(10)),
    NUMBER_J(11, List.of(10)),
    NUMBER_Q(12, List.of(10)),
    NUMBER_K(13, List.of(10)),
    ;
    
    private final int value;
    private final List<Integer> blackjackValue;
    
    CardNumber(final int value, final List<Integer> blackjackValue) {
        this.value = value;
        this.blackjackValue = blackjackValue;
    }
    
    public static CardNumber from(final int number) {
        return Arrays.stream(CardNumber.values())
                .filter(cardNumber -> cardNumber.value == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("숫자는 1부터 13 사이여야 합니다."));
    }
    
    public List<Integer> getBlackjackNumber() {
        return blackjackValue;
    }
}
