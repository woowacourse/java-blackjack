package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public final class BlackJackGame {
    private static final int INIT_CARD_COUNT = 2;

    private Players players;
    private Dealer dealer;

    public void run() {
        initParticipants();
        hit();

    }

    private void initParticipants() {
        final List<String> names = InputView.inputPlayerName();
        dealer = new Dealer();
        players = new Players(names);
        initCards();
        OutputView.printInitCardsResult(getCardsWithName());
    }

    private void initCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            dealer.receiveCard(CardDeck.draw());
            players.receiveCard();
        }
    }

    private Map<String, List<Card>> getCardsWithName() {
        Map<String, List<Card>> cardsWithNameTotal = dealer.getCardsWithName();
        assert cardsWithNameTotal != null;
        cardsWithNameTotal.putAll(players.getCardsWithName());
        return cardsWithNameTotal;
    }

    private void hit() {
        for (Player player : players.getPlayers()) {
            hitPlayer(player);
        }
        hitDealer();
    }

    private void hitPlayer(Player player) {
        int printCount = 0;
        while (player.canReceiveCard() && InputView.inputTryToHit(player.getName())) {
            player.receiveCard(CardDeck.draw());
            OutputView.printCardsWithName(player.getCardsWithName());
            printCount++;
        }
        if (printCount == 0) {
            OutputView.printCardsWithName(player.getCardsWithName());
        }
    }

    private void hitDealer() {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(CardDeck.draw());
            OutputView.printDealerHit();
        }
    }
}
