package blackjack.view;

import blackjack.domain.card.Kind;

import java.util.Optional;
import java.util.stream.Stream;

public enum KindView {
    SPADE_VIEW(Kind.SPADE, "스페이드"),
    DIAMOND_VIEW(Kind.DIAMOND, "다이아몬드"),
    HEART_VIEW(Kind.HEART, "하트"),
    CLOVER_VIEW(Kind.CLOVER, "클로버");

    private final Kind kind;
    private final String kindName;

    KindView(Kind kind, String kindName) {
        this.kind = kind;
        this.kindName = kindName;
    }
    // TODO Optional의 orElseThrow
    public static String findKindName(Kind kind) {
        Optional<KindView> targetKindView = Stream.of(values())
                .filter(view -> view.kind == kind)
                .findAny();

        if (targetKindView.isPresent()) {
            return targetKindView.get().kindName;
        }
        throw new IllegalStateException("존재하지 않는 카드 종류를 조회하였습니다.");
    }
}
