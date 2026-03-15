package domain;

public class FinalResult {
    private final Name name;
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

    public String getNameText() {
        return name.getName();
    }

    public String getResultText() {
        return resultType.getType();
    }
}
