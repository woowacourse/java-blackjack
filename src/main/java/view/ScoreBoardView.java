package view;


import static util.Keyword.WIN;
import static util.Keyword.SAME;
import static util.Keyword.LOSE;

public class ScoreBoardView {

    public static void printWin(final String name) {
        System.out.println(name + ": " + WIN.getValue());
    }

    public static void printSame(final String name) {
        System.out.println(name + ": " + SAME.getValue());
    }

    public static void printLose(final String name) {
        System.out.println(name + ": " + LOSE.getValue());
    }

    public static void printDealerScoreBoard(final String name, final int win, final int same, final int lose) {
        System.out.println(name + ": " + win + WIN.getValue() + same + SAME.getValue() + lose + LOSE.getValue());
    }
}
