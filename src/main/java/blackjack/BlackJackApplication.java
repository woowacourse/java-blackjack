package blackjack;

import blackjack.domain.card.CardFactory;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GamersResult;
import blackjack.domain.result.PlayerResultMatcher;
import blackjack.domain.rule.PlayerAnswer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    private static final int INITIAL_HAND_COUNT = 2;

    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.generate());
        Dealer dealer = new Dealer();
        Players players = Players.ofComma(InputView.askPlayerNames());

        initializeHand(dealer, players, deck);
        OutputView.printInitialCards(dealer, players);

        drawMoreCard(dealer, players, deck);
        OutputView.printGamerScore(dealer, players);

        GamersResult gamersResult = PlayerResultMatcher.report(dealer, players);
        OutputView.printGamersResult(gamersResult);
    }

    private static void initializeHand(Dealer dealer, Players players, Deck deck) {
        for (int i = 0; i < INITIAL_HAND_COUNT; i++) {
            drawCard(dealer, players, deck);
        }
    }

    private static void drawCard(Dealer dealer, Players players, Deck deck) {
        dealer.draw(deck.pick());
        for (Player player : players) {
            player.draw(deck.pick());
        }
    }

    private static void drawMoreCard(Dealer dealer, Players players, Deck deck) {
        hitOrStandForPlayers(players, deck);
        drawCardForDealer(dealer, deck);
    }

    private static void hitOrStandForPlayers(Players players, Deck deck) {
        for (Player player : players) {
            hitOrStand(player, deck);
        }
    }

    private static void drawCardForDealer(Dealer dealer, Deck deck) {
        while (dealer.shouldDrawCard()) {
            dealer.draw(deck.pick());
            OutputView.printDealerDrewCard();
        }
    }

    private static void hitOrStand(Player player, Deck deck) {
        while (!player.isBusted() && wantMoreCard(player)) {
            player.draw(deck.pick());
            OutputView.printPlayerHand(player);
        }
    }

    private static boolean wantMoreCard(Player player) {
        PlayerAnswer answer = PlayerAnswer.of(InputView.askHitOrStand(player.getName()));
        return answer.isYes();
    }
}
