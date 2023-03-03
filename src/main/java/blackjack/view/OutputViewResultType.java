package blackjack.view;

import blackjack.domain.ResultType;

import java.util.Arrays;

public enum OutputViewResultType {
    WIN("승 ", ResultType.WIN),
    TIE("무 ", ResultType.TIE),
    LOSE("패 ", ResultType.LOSE);

    private final String printResultType;
    private final ResultType resultType;

    OutputViewResultType(final String printResultType, final ResultType resultType) {
        this.printResultType = printResultType;
        this.resultType = resultType;
    }

    public static OutputViewResultType from(final ResultType resultType) {
        return Arrays.stream(values())
                .filter(it -> it.resultType == resultType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 결과입니다"));
    }

    public String getPrintResultType() {
        return printResultType;
    }
}
