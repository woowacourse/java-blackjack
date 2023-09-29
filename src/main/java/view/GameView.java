package view;

import model.name.Name;

import java.io.BufferedReader;
import java.io.IOException;

import static util.Keyword.NO;
import static util.Keyword.YES;

public class GameView {

    private static final String WIN = "승 ";
    private static final String SAME = "무 ";
    private static final String LOSE = "패 ";

    private final BufferedReader inputReader;

    public GameView(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public String getInput() throws IOException {
        return inputReader.readLine();
    }

    public void printPlayerDefaultStatus(final String name, final String cardNames) {
        if (name.equals(Name.getDealer())) {
            StatusView.dealerDefaultAnswer(name, cardNames);
            return;
        }
       StatusView.playerDefaultAnswer(name, cardNames);
    }

    public boolean askWantMoreCard(String name) throws IOException {
        AskView.askWantMoreCard(name);

        String answer = getInput();
        while (isAnswerNotValid(answer)) {
            AskView.askWantMoreCard(name);
            answer = getInput();
        }

        return convertAnswerToBoolean(answer);
    }

    public void printWin(final String name) {
        System.out.println(name + ": " + WIN);
    }

    public void printSame(final String name) {
        System.out.println(name + ": " + SAME);
    }

    public void printLose(final String name) {
        System.out.println(name + ": " + LOSE);
    }

    public void printDealerScoreBoard(final String name, final int win, final int same, final int lose) {
        System.out.println(name + ": " + win + WIN + same + SAME + lose + LOSE);
    }

    private boolean convertAnswerToBoolean(String answer) {
        return answer.equals(YES.getValue());
    }

    private boolean isAnswerNotValid(String answer) {
        return !answer.equals(YES.getValue()) && !answer.equals(NO.getValue());
    }
}
