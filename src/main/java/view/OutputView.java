package view;

import domain.Participant;
import domain.Participants;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printInitialMessage(final List<String> names) {
        String dealerName = names.get(0);
        String playerNames = names.stream().skip(1).collect(Collectors.joining(", "));
        System.out.printf("%s와 %s에게 2장을 나누었습니다." + System.lineSeparator(), dealerName, playerNames);
    }

    public void printInitialState(final Participants participants) {
        for (Participant participant : participants.getParticipants()) {
        }
    }
}
