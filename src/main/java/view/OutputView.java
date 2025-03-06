package view;

import domain.Card;
import domain.Participant;
import java.util.List;
import view.support.OutputFormatter;

public class OutputView {

    private final OutputFormatter outputFormatter;

    public OutputView(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    public void printInitialCards(Participant dealer, List<Participant> players) {
        List<String> playerNames = players.stream()
                .map(Participant::name)
                .toList();
        String parsedPlayerNames = outputFormatter.formatPlayerNames(playerNames);

        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealer.name(), parsedPlayerNames);

        Card card = dealer.getFirstCard();
        System.out.printf("%s카드: %s\n", dealer.name(), outputFormatter.formatCard(card));

        players.forEach(
                player ->
                System.out.printf("%s카드: %s\n", player.name(), outputFormatter.formatCards(player.cards()))
        );
    }

    public void printCurrentCard(Participant player) {
        System.out.printf("%s카드: %s\n", player.name(), outputFormatter.formatCards(player.cards()));
    }
}
