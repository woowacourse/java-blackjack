package domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public Result getReverseResult(){
        if(result.equals(Result.WIN.result)){
            return Result.LOSE;
        }
        if(result.equals(Result.LOSE.result)){
            return Result.WIN;
        }
        return Result.DRAW;
    }

}
