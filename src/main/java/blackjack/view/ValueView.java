package blackjack.view;

import blackjack.domain.Value;

import java.util.Optional;
import java.util.stream.Stream;

public enum ValueView {

    ACE_VIEW(Value.ACE, "A"), TWO_VIEW(Value.TWO, "2"), THREE_VIEW(Value.THREE, "3"),
    FOUR_VIEW(Value.FOUR, "4"), FIVE_VIEW(Value.FIVE, "5"), SIX_VIEW(Value.SIX, "6"),
    SEVEN_VIEW(Value.SEVEN, "7"), EIGHT_VIEW(Value.EIGHT, "8"), NINE_VIEW(Value.NINE, "9"),
    TEN_VIEW(Value.TEN, "10"), JACK_VIEW(Value.JACK, "J"), QUEEN_VIEW(Value.QUEEN, "Q"), KING_VIEW(Value.KING, "K");

    private final Value value;
    private final String valueName;

    ValueView(Value value, String valueName) {
        this.value = value;
        this.valueName = valueName;
    }

    public static String findValueName(Value value) {
        Optional<ValueView> targetValueView = Stream.of(values())
                .filter(view -> view.value == value)
                .findAny();

        if (targetValueView.isPresent()) {
            return targetValueView.get().valueName;
        }

        throw new IllegalStateException("존재하지 않는 카드 종류를 조회하였습니다.");
    }
}
