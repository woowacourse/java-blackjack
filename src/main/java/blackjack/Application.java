package blackjack;

import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    private static final String AGREE = "y";
    private static final String DECLINE = "n";

    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        List<Player> players = InputView.inputPlayerNames()
                                        .stream()
                                        .map(Player::new)
                                        .collect(Collectors.toList());
        Participants participants = Participants.of(dealer, players);
        participants.receiveDefaultCards(cardDeck);
        OutputView.printDefaultCardMessage(dealer, players);
        players.forEach(player -> drawMoreCard(player, cardDeck));
        if (dealer.isAbleToReceiveCard()) {
            dealer.receiveCard(cardDeck.draw());
            OutputView.printDealerDrawingMessage(dealer);
        }
        OutputView.printFinalCardsAndScore(participants);
    }

    private static void drawMoreCard(Player player, CardDeck cardDeck) {
        while (player.isAbleToReceiveCard()) {
            String answer = InputView.inputAnswerToAdditionalCardQuestion(player);
            if (answer.equals(AGREE)) {
                player.receiveCard(cardDeck.draw());
            }
            OutputView.printEachPlayerCards(player);
            if (answer.equals(DECLINE)) {
                break;
            }
        }
    }
}
