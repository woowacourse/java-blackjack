package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.utils.CardDeck;
import blackjack.utils.RandomCardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final CardDeck cardDeck = new RandomCardDeck();
        final Dealer dealer = new Dealer(cardDeck.initCards());

        String namesInput = InputView.getNames();
        Players players = new Players(namesInput, cardDeck);
        Participants participants = new Participants(players, dealer);
        OutputView.printParticipantsCards(participants);

        for (Player player : players.values()) {
            while (player.isAvailableToTake() && InputView.requestOneMoreCard(player.getName())) {
                player.takeCard(cardDeck.pop());
                OutputView.printCards(player);
            }
        }

        if (dealer.isAvailableToTake()) {
            dealer.takeCard(cardDeck.pop());
            OutputView.printDealerGetCard();
        }

        OutputView.printParticipantsResults(participants);

        OutputView.printWinOrLose(participants.finalResult());
    }
}
