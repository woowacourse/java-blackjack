package blackjack;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
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
        List<Player> players = generatePlayers();
        Participants participants = Participants.of(dealer, players);

        participants.receiveDefaultCards(cardDeck);
        OutputView.printDefaultCardMessage(dealer, players);
        players.forEach(player -> drawMoreCard(player, cardDeck));
        receiveDealerCard(dealer, cardDeck);

        OutputView.printFinalCardsAndScore(participants);
        OutputView.printFinalResult(dealer, players);
    }

    private static List<Player> generatePlayers() {
        return InputView.inputPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private static void drawMoreCard(Player player, CardDeck cardDeck) {
        if (!player.isAbleToReceiveCard()) {
            return;
        }
        String answer = InputView.inputAnswerForAdditionalCardDraw(player);
        if (answer.equals(AGREE)) {
            player.receiveCard(cardDeck.draw());
        }
        OutputView.printEachPlayerCards(player);
        if (answer.equals(DECLINE)) {
            return;
        }
        if (player.isAbleToReceiveCard()) {
            drawMoreCard(player, cardDeck);
        }
    }

    private static void receiveDealerCard(Dealer dealer, CardDeck cardDeck) {
        if (dealer.isAbleToReceiveCard()) {
            dealer.receiveCard(cardDeck.draw());
            OutputView.printDealerDrawingMessage(dealer);
        }
    }
}
