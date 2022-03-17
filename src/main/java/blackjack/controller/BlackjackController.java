package blackjack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.answer.Answer;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private final Deck deck = Deck.create();

    public void play() {
        Dealer dealer = new Dealer(deck.drawStartingCards());
        Players players = participatePlayers();
        OutputView.printInitCard(dealer, players);

        hitOrStandPlayers(players);
        hitOrStandDealer(dealer);
        OutputView.printDrawResult(dealer, players);

        OutputView.printTotalResult(dealer.judgeResult(players));
    }

    private Players participatePlayers() {
        try {
            return new Players(toPlayer(InputView.inputPlayers()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return participatePlayers();
        }
    }

    private List<Player> toPlayer(List<String> names) {
        return names.stream()
            .map(name -> new Player(name, deck.drawStartingCards()))
            .collect(Collectors.toList());
    }

    private void hitOrStandPlayers(Players players) {
        players.getPlayers().forEach(this::hitOrStandPlayer);
    }

    private void hitOrStandPlayer(Player player) {
        while (player.canDraw() && isHit(player)) {
            player.drawCard(deck.draw());
            OutputView.printGamerDrawCard(player);
        }
    }

    private boolean isHit(Player player) {
        try {
            Answer answer = Answer.of(InputView.inputHitOrStand(player));
            return answer.isHit();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isHit(player);
        }
    }

    private void hitOrStandDealer(Dealer dealer) {
        while (dealer.canDraw()) {
            dealer.drawCard(deck.draw());
            OutputView.printDealerDrawCardMessage();
        }
    }
}
