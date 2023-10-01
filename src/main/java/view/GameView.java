package view;

import java.io.BufferedReader;
import java.io.IOException;

import static util.Keyword.NO;
import static util.Keyword.YES;

public class GameView {

    private final BufferedReader inputReader;

    public GameView(final BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public String getInput() throws IOException {
        return inputReader.readLine();
    }

    public boolean askWantMoreCard(final String name) throws IOException {
        AskView.askWantMoreCard(name);

        String answer = getInput();
        while (isAnswerNotValid(answer)) {
            AskView.askWantMoreCard(name);
            answer = getInput();
        }

        return convertAnswerToBoolean(answer);
    }

    private boolean isAnswerNotValid(final String answer) {
        return !answer.equals(YES.getValue()) && !answer.equals(NO.getValue());
    }

    private boolean convertAnswerToBoolean(final String answer) {
        return answer.equals(YES.getValue());
    }
}
