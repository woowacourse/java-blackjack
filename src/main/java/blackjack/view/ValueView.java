package blackjack.view;

import blackjack.domain.card.CardNumber;

import java.util.Optional;
import java.util.stream.Stream;

public enum ValueView {

    ACE_VIEW(CardNumber.ACE, "A"), TWO_VIEW(CardNumber.TWO, "2"), THREE_VIEW(CardNumber.THREE, "3"),
    FOUR_VIEW(CardNumber.FOUR, "4"), FIVE_VIEW(CardNumber.FIVE, "5"), SIX_VIEW(CardNumber.SIX, "6"),
    SEVEN_VIEW(CardNumber.SEVEN, "7"), EIGHT_VIEW(CardNumber.EIGHT, "8"), NINE_VIEW(CardNumber.NINE, "9"),
    TEN_VIEW(CardNumber.TEN, "10"), JACK_VIEW(CardNumber.JACK, "J"), QUEEN_VIEW(CardNumber.QUEEN, "Q"), KING_VIEW(CardNumber.KING, "K");

    private final CardNumber cardNumber;
    private final String valueName;

    ValueView(CardNumber cardNumber, String valueName) {
        this.cardNumber = cardNumber;
        this.valueName = valueName;
    }

    public static String findValueName(CardNumber cardNumber) {
        Optional<ValueView> targetValueView = Stream.of(values())
                .filter(view -> view.cardNumber == cardNumber)
                .findAny();

        if (targetValueView.isPresent()) {
            return targetValueView.get().valueName;
        }

        throw new IllegalStateException("존재하지 않는 카드 종류를 조회하였습니다.");
    }
}
