package blackjack;

import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        List<Player> gamers = createGamers();
    }

    private static List<Player> createGamers() {
        try {
            return toGamerList();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createGamers();
        }
    }

    private static List<Player> toGamerList() {
        List<String> names = InputView.requestPlayerName();
        checkDuplicateName(names);
        return names.stream()
                .map(Gamer::new)
                .collect(Collectors.toList());
    }

    private static void checkDuplicateName(final List<String> names) {
        Set<String> tempSet = new HashSet<>(names);
        if (names.size() != tempSet.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름은 입력할 수 없습니다.");
        }
    }
}
