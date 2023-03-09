package ui;

import domain.Result;

public class MatchResultMapper {
    public static String transformToPrintResult(Result result) {
        if (result == Result.WIN) {
            return "승";
        }
        if (result == Result.LOSE) {
            return "패";
        }
        return "무";
    }
}
