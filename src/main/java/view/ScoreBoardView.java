package view;

import static util.Keyword.LOSE;
import static util.Keyword.SAME;
import static util.Keyword.WIN;

public class ScoreBoardView {

    public static void printGrade(final String name, final String grade) {
        System.out.println(name + ": " + grade);
    }

    public static void printDealerScoreBoard(final String name, final int win, final int same, final int lose) {
        System.out.println(name + ": " + win + WIN.getValue() + same + SAME.getValue() + lose + LOSE.getValue());
    }
}
