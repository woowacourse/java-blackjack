package blackjack;

import blackjack.cardMachine.CardRandomMachine;
import blackjack.gamer.Dealer;
import blackjack.gamer.Players;
import blackjack.view.InputView;

public class Blackjack {
    private final Dealer dealer;
    private final Players players;

    public Blackjack() {
        this.dealer = new Dealer(new CardRandomMachine());
        this.players = new Players();
    }

    public void run() {
        final InputView inputView = new InputView();

        initDealer();
        makePlayers(inputView);

    }

    private void initDealer() {
        dealer.initCardMachine();
    }

    private void makePlayers(final InputView inputView) {
        final String names = inputView.readNames();
        try {
            players.addPlayersFrom(names);
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
            makePlayers(inputView);
        }
    }

}
