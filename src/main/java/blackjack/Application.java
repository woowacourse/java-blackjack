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
        for (Player player : players) {
            int betAmount = InputView.inputBetAmount(player);
            player.setBetAmount(betAmount);
        }
        participants.receiveDefaultCards(cardDeck);
        OutputView.printDefaultCardMessage(dealer, players);
        players.forEach(player -> drawMoreCard(player, cardDeck));
        receiveDealerCard(dealer, cardDeck);
        OutputView.printFinalCardsAndScore(participants);
        OutputView.printFinalBetProfits(participants.calculateFinalBetProfits());
    }

    private static void drawMoreCard(Player player, CardDeck cardDeck) {
        while (player.isAbleToReceiveCard()) {
            String answer = InputView.inputAnswerToAdditionalCardQuestion(player);
            if (AGREE.equals(answer)) {
                player.receiveCard(cardDeck.draw());
            }
            OutputView.printEachPlayerCards(player);
            if (DECLINE.equals(answer)) {
                return;
            }
        }
    }

    private static void receiveDealerCard(Dealer dealer, CardDeck cardDeck) {
        if (dealer.isAbleToReceiveCard()) {
            dealer.receiveCard(cardDeck.draw());
            OutputView.printDealerDrawingMessage(dealer);
        }
    }
}
