package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.answer.Answer;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    private final Deck deck;
    private final BlackjackGame blackjackGame;

    public BlackjackController() {
        deck = Deck.create();
        blackjackGame = new BlackjackGame(deck);
    }

    public void play() {
        Dealer dealer = new Dealer();
        Players players = participatePlayers();
        inputBettingAmount(players);
        blackjackGame.initStartingCards(dealer, players);
        OutputView.printInitCard(dealer, players);

        hitOrStandPlayers(players);
        hitOrStandDealer(dealer);
        OutputView.printDrawResult(dealer, players);

        OutputView.printTotalResult(dealer.judgeResult(players));
    }

    private Players participatePlayers() {
        try {
            return new Players(InputView.inputPlayers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return participatePlayers();
        }
    }

    private void inputBettingAmount(Players players) {
        players.getPlayers().forEach(this::betAmount);
    }

    private void betAmount(Player player) {
        try {
            player.betAmount(InputView.inputBettingAmount(player));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            betAmount(player);
        }
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
