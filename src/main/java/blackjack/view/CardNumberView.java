package blackjack.view;

import blackjack.domain.card.CardNumber;

import java.util.Optional;
import java.util.stream.Stream;

import static blackjack.domain.card.CardNumber.*;

public enum CardNumberView {

    ACE_VIEW(ACE, "A"), TWO_VIEW(TWO, "2"), THREE_VIEW(THREE, "3"),
    FOUR_VIEW(FOUR, "4"), FIVE_VIEW(FIVE, "5"), SIX_VIEW(SIX, "6"),
    SEVEN_VIEW(SEVEN, "7"), EIGHT_VIEW(EIGHT, "8"), NINE_VIEW(NINE, "9"),
    TEN_VIEW(TEN, "10"), JACK_VIEW(JACK, "J"), QUEEN_VIEW(QUEEN, "Q"), KING_VIEW(KING, "K");

    private final CardNumber cardNumber;
    private final String valueName;

    CardNumberView(CardNumber cardNumber, String valueName) {
        this.cardNumber = cardNumber;
        this.valueName = valueName;
    }

    public static String findValueName(CardNumber cardNumber) {
        Optional<CardNumberView> targetValueView = Stream.of(values())
                .filter(view -> view.cardNumber == cardNumber)
                .findAny();

        if (targetValueView.isPresent()) {
            return targetValueView.get().valueName;
        }

        throw new IllegalStateException("존재하지 않는 카드 종류를 조회하였습니다.");
    }
}
