package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.attribute.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Controller<T extends Player> {
    private final CardDeck deck = new CardDeck();
    private final ModeStrategy<T> gameMode;

    public Controller(ModeStrategy<T> gameMode) {
        this.gameMode = gameMode;
    }

    public void play() {
        Players<T> players = createPlayers();
        Dealer dealer = new Dealer();

        dealFirstCards(dealer);
        dealFirstCards(players);
        OutputView.printInitialStatus(players, dealer);

        dealAdditionalCards(players);
        dealAdditionalCards(dealer);
        OutputView.printFinalStatus(players, dealer);

        showResult(players, dealer);
    }

    private Players<T> createPlayers() {
        List<Name> names = createNames();
        return gameMode.createPlayers(names);
    }

    private List<Name> createNames() {
        return InputView.enterNames().stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private void dealFirstCards(Dealer dealer) {
        deck.dealFirstCards(dealer);
    }

    private void dealFirstCards(Players<T> players) {
        players.stream()
                .forEach(deck::dealFirstCards);
    }

    private void dealAdditionalCards(Players<T> players) {
        players.stream()
                .forEach(this::dealAdditionalCards);
    }

    private void dealAdditionalCards(Player player) {
        while (player.canGetMoreCard() && player.wantMoreCard(readYesOrNo(player))) {
            deck.dealAdditionalCard(player);
            OutputView.printCardsStatus(player.name(), player.showCards());
        }
    }

    private void dealAdditionalCards(Dealer dealer) {
        while (dealer.canGetMoreCard()) {
            deck.dealAdditionalCard(dealer);
            OutputView.printDealerGetMoreCard();
        }
    }

    private String readYesOrNo(Player player) {
        return InputView.readYesOrNo(player.name());
    }

    private void showResult(Players<T> players, Dealer dealer) {
        gameMode.showResult(players, dealer);
    }
}
