package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static blackjack.util.ExceptionTemplate.*;

public class BlackJackManager {
    private final Deck deck = new Deck();

    public void run() {
        final Dealer dealer = new Dealer(initCards());
        final List<Player> players = createPlayers();
        OutputView.printParticipantsInitCards(dealer, players);

        for (final Player player : players) {
            hitBy(player);
        }
        hitBy(dealer);

        showParticipantStatus(dealer, players);
        showGameResult(dealer, players);
    }

    private static void showParticipantStatus(final Dealer dealer, final List<Player> players) {
        OutputView.printParticipantCardWithResult(dealer, dealer.getTotalPoint());
        players.forEach(player -> OutputView.printParticipantCardWithResult(player, player.getTotalPoint()));
    }

    private static void showGameResult(final Dealer dealer, final List<Player> players) {
        final BlackJackResults blackJackResults = new BlackJackResults(dealer, players);
        OutputView.printBlackJackResults(blackJackResults);
    }

    private void hitBy(final Dealer dealer) {
        int hitCount = 0;
        while (dealer.isHittable()) {
            dealer.hit(deck.draw());
            hitCount++;
        }

        if (hitCount > 0) {
            OutputView.printDealerHit(hitCount);
        }
    }

    private void hitBy(final Player player) {
        while (player.isHittable()
                && repeatAndPrintCause(() -> InputView.checkPlayerAdditionalHit(player.getName().getValue()))
        ) {
            player.hit(deck.draw());
            OutputView.printParticipantsCards(player);
        }
        if (player.isHittable()) {
            OutputView.printParticipantsCards(player);
        }
    }

    private List<Player> createPlayers() {
        final List<String> names = repeatAndPrintCause(InputView::readNames);
        final List<Player> players = new ArrayList<>();
        for (final String name : names) {
            players.add(new Player(initCards(), name));
        }
        return players;
    }

    private ParticipantCards initCards() {
        return new ParticipantCards(List.of(deck.draw(), deck.draw()));
    }
}
