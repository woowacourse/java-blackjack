package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public abstract class BlackJackController {
    protected final CardDeck deck = new CardDeck();

    public void play() {
        Players players = createPlayers();
        Dealer dealer = new Dealer();

        dealFirstCards(dealer);
        dealFirstCards(players);
        OutputView.printInitialStatus(players, dealer);

        dealAdditionalCards(players);
        dealAdditionalCards(dealer);
        OutputView.printFinalStatus(players, dealer);

        showResult(players, dealer);
    }

    protected abstract Players createPlayers();

    protected abstract void showResult(Players players, Dealer dealer);

    protected void dealFirstCards(Dealer dealer) {
        deck.dealFirstCards(dealer);
    }

    protected void dealFirstCards(Players players) {
        for (Player player : players.getPlayers()) {
            deck.dealFirstCards(player);
        }
    }

    protected void dealAdditionalCards(Players players) {
        for (Player player : players.getPlayers()) {
            while (player.canGetMoreCard() && player.wantMoreCard(readYesOrNo(player))) {
                deck.dealAdditionalCard(player);
                OutputView.printCardsStatus(player.name(), player.showCards());
            }
        }
    }

    protected void dealAdditionalCards(Dealer dealer) {
        while (dealer.canGetMoreCard()) {
            deck.dealAdditionalCard(dealer);
            OutputView.printDealerGetMoreCard();
        }
    }

    protected String readYesOrNo(Player player) {
        return InputView.readYesOrNo(player.name());
    }
}
