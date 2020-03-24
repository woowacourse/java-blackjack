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

public class Controller {
    public static <T extends Player> void play(ModeStrategy<T> gameMode) {
        Players<T> players = createPlayers(gameMode);
        Dealer dealer = new Dealer();
        CardDeck deck = new CardDeck();

        dealFirstCards(dealer, deck);
        dealFirstCards(players, deck);
        OutputView.printInitialStatus(players, dealer);

        dealAdditionalCards(players, deck);
        dealAdditionalCards(dealer, deck);
        OutputView.printFinalStatus(players, dealer);

        showResult(players, dealer, gameMode);
    }

    private static <T extends Player> Players<T> createPlayers(ModeStrategy<T> gameMode) {
        List<Name> names = createNames();
        return gameMode.createPlayers(names);
    }

    private static List<Name> createNames() {
        return InputView.enterNames().stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private static void dealFirstCards(Dealer dealer, CardDeck deck) {
        deck.dealFirstCards(dealer);
    }

    private static <T extends Player> void dealFirstCards(Players<T> players, CardDeck deck) {
        players.stream()
                .forEach(deck::dealFirstCards);
    }

    private static <T extends Player> void dealAdditionalCards(Players<T> players, CardDeck deck) {
        players.stream()
                .forEach(player -> dealAdditionalCards(player, deck));
    }

    private static void dealAdditionalCards(Player player, CardDeck deck) {
        while (player.canGetMoreCard() && player.wantMoreCard(readYesOrNo(player))) {
            deck.dealAdditionalCard(player);
            OutputView.printCardsStatus(player.name(), player.showCards());
        }
    }

    private static void dealAdditionalCards(Dealer dealer, CardDeck deck) {
        while (dealer.canGetMoreCard()) {
            deck.dealAdditionalCard(dealer);
            OutputView.printDealerGetMoreCard();
        }
    }

    private static String readYesOrNo(Player player) {
        return InputView.readYesOrNo(player.name());
    }

    private static <T extends Player> void showResult(Players<T> players, Dealer dealer, ModeStrategy<T> gameMode) {
        gameMode.showResult(players, dealer);
    }
}
