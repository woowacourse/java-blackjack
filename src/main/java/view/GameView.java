package view;

import java.io.BufferedReader;
import java.io.IOException;

import static util.Keyword.NO;
import static util.Keyword.YES;

public class GameView {

    private final BufferedReader inputReader;

    public GameView(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public String getInput() throws IOException {
        return inputReader.readLine();
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

    private boolean convertAnswerToBoolean(String answer) {
        return answer.equals(YES.getValue());
    }

    private boolean isAnswerNotValid(String answer) {
        return !answer.equals(YES.getValue()) && !answer.equals(NO.getValue());
    }
}
