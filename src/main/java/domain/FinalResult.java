package domain;

public class FinalResult {
    private final Name name;
    // ResultType이 아닌 최종 수익이 얼마인지 확인할 수 있는 필드
    private final ResultType resultType;
    
    private FinalResult(Name name, ResultType resultType) {
        this.name = name;
        this.resultType = resultType;
    }

    public static FinalResult from(Name name, ResultType resultType) {
        return new FinalResult(name, resultType);
    }

    public Name getName() {
        return name;
    }

    public ResultType getResultType() {
        return resultType;
    }
}