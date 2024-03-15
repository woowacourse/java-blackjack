package blackjack.view;

import blackjack.domain.card.CardKind;

import java.util.stream.Stream;

public enum KindView {
    SPADE_VIEW(CardKind.SPADE, "스페이드"),
    DIAMOND_VIEW(CardKind.DIAMOND, "다이아몬드"),
    HEART_VIEW(CardKind.HEART, "하트"),
    CLOVER_VIEW(CardKind.CLOVER, "클로버");

    private final CardKind cardKind;
    private final String kindName;

    KindView(CardKind cardKind, String kindName) {
        this.cardKind = cardKind;
        this.kindName = kindName;
    }

    public static String findKindName(CardKind targetCardKind) {
        KindView targetKindView = Stream.of(values())
                .filter(view -> view.cardKind == targetCardKind)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 카드 종류를 조회하였습니다."));
        return targetKindView.kindName;
    }
}
