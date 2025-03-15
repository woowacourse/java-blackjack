package blackjack.domain.card;

import java.util.List;

public enum CardNumber {
    NUMBER_A(List.of(1, 11)),
    NUMBER_2(List.of(2)),
    NUMBER_3(List.of(3)),
    NUMBER_4(List.of(4)),
    NUMBER_5(List.of(5)),
    NUMBER_6(List.of(6)),
    NUMBER_7(List.of(7)),
    NUMBER_8(List.of(8)),
    NUMBER_9(List.of(9)),
    NUMBER_10(List.of(10)),
    NUMBER_J(List.of(10)),
    NUMBER_Q(List.of(10)),
    NUMBER_K(List.of(10)),
    ;
    
    private final List<Integer> blackjackValue;
    
    CardNumber(final List<Integer> blackjackValue) {
        this.blackjackValue = blackjackValue;
    }
    
    public List<Integer> getBlackjackNumber() {
        return blackjackValue;
    }
}
