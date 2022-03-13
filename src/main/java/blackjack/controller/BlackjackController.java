package blackjack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Cards;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void play() {
        Deck deck = initDeck();
        Dealer dealer = initDealer(deck);
        Players players = participate(deck);
        OutputView.printInitCard(dealer, players);

        hitOrStandPlayers(players, deck);
        hitOrStandDealer(dealer, deck);
        OutputView.printDrawResult(dealer, players);
    }

    private Deck initDeck() {
        Deck deck = new Deck(Deck.initCards());
        deck.shuffle();
        return deck;
    }

    private Dealer initDealer(Deck deck) {
        return new Dealer(new Cards(deck.drawStartCards()));
    }

    private Players participate(Deck deck) {
        try {
            return new Players(stringToPlayer(InputView.inputPlayers(), deck));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return participate(deck);
        }
    }

    private List<Player> stringToPlayer(List<String> names, Deck deck) {
        return names.stream()
            .map(name -> new Player(name, new Cards(deck.drawStartCards())))
            .collect(Collectors.toList());
    }

    private void hitOrStandPlayers(Players players, Deck deck) {
        players.getPlayers().forEach(player -> hitOrStandPlayer(player, deck));
    }

    private void hitOrStandPlayer(Player player, Deck deck) {
        try {
            hitOrStand(player, deck);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void hitOrStand(Player player, Deck deck) {
        while (InputView.inputHitOrStand(player)) {
            player.drawCard(deck.draw());
            OutputView.printCard(player);
        }
    }

    private void hitOrStandDealer(Dealer dealer, Deck deck) {
        try {
            dealer.drawCard(deck.draw());
            OutputView.printDealerDrawCardMessage();
            hitOrStandDealer(dealer, deck);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
