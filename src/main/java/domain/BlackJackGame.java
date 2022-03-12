package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.Dealer;
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
    }

    private void initParticipants() {
        final List<String> names = InputView.inputPlayerName();
        dealer = new Dealer();
        players = new Players(names);
        initCards();
        OutputView.printInitCardsResult(getCardsWithName());
    }

    private Map<String, List<Card>> getCardsWithName() {
        Map<String, List<Card>> cardsWithNameTotal = dealer.getCardsWithName();
        assert cardsWithNameTotal != null;
        cardsWithNameTotal.putAll(players.getCardsWithName());
        return cardsWithNameTotal;
    }

    private void initCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            dealer.receiveCard(CardDeck.draw());
            players.receiveCard();
        }
    }
}
