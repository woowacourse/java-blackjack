package blackjack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void play() {
        Deck deck = Deck.create();
        Dealer dealer = new Dealer(deck.drawStartingCards());
        Players players = participatePlayers(deck);
        OutputView.printInitCard(dealer, players);

        hitOrStandPlayers(players, deck);
        hitOrStandDealer(dealer, deck);
        OutputView.printDrawResult(dealer, players);

        OutputView.printTotalResult(dealer.judgeResult(players));
    }

    private Players participatePlayers(Deck deck) {
        try {
            return new Players(toPlayer(InputView.inputPlayers(), deck));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return participatePlayers(deck);
        }
    }

    private List<Player> toPlayer(List<String> names, Deck deck) {
        return names.stream()
            .map(name -> new Player(name, deck.drawStartingCards()))
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
            OutputView.printGamerDrawCard(player);
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
