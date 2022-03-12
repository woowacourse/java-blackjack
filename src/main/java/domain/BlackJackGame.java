package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import java.util.List;
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
        players = new Players(names);
        dealer = new Dealer();
        initCards();
        OutputView.printInitCardsResult();
    }

    private void initCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            players.receiveCard();
            dealer.receiveCard(CardDeck.draw());
        }
    }
}
