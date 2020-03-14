package blackjack.view;

import blackjack.domain.Response;
import blackjack.domain.player.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMA = ",";

    public static List<String> inputUserNames() {
        OutputView.printInputUserNamesGuideMessage();
        return Arrays.stream(SCANNER.nextLine().split(COMMA))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String askOneMoreCard(User user) {
        OutputView.printAskOneMoreCardMessage(user);
        String response = SCANNER.nextLine();
        if (Response.isCorrect(response)) {
            return response;
        }
        OutputView.printCorrectResponseMessage();
        return askOneMoreCard(user);
    }
}
