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

    public void printDealerDraw(String dealerName) {
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.", dealerName);
    }

    public void printDealerNoDraw(String dealerName) {
        System.out.printf("%s는 17이상이라 카드를 추가로 받지 않았습니다.", dealerName);
    }
}
