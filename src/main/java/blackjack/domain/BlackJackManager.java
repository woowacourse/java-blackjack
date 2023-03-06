package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.ParticipantCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackManager {
    private final Deck deck = new Deck();

    public void run() {
        Dealer dealer = new Dealer(initCards());
        List<Player> players = createPlayers();
        OutputView.printParticipantsInitCards(dealer, players);

        playGame(dealer, players);

        showParticipantStatus(dealer, players);
        showGameResult(dealer, players);
    }

    private ParticipantCards initCards() {
        return new ParticipantCards(List.of(deck.draw(), deck.draw()));
    }

    private List<Player> createPlayers() {
        List<String> names = InputView.readNames();
        List<Player> players = new ArrayList<>();
        for (final String name : names) {
            players.add(new Player(initCards(), name));
        }
        return players;
    }

    private void playGame(final Dealer dealer, final List<Player> players) {
        for (Player player : players) {
            hitBy(player);
        }
        hitBy(dealer);
    }

    private void hitBy(final Player player) {
        while (player.isHittable() && InputView.checkPlayerAdditionalHit(player.getName().getValue())) {
            player.hit(deck.draw());
            OutputView.printParticipantsCards(player);
        }
        if (player.isHittable()) {
            OutputView.printParticipantsCards(player);
        }
    }

    private void hitBy(final Dealer dealer) {
        int hitCount = 0;
        while (dealer.isHittable()) {
            dealer.hit(deck.draw());
            hitCount++;
        }

        if(hitCount > 0) {
            OutputView.printDealerHit(hitCount);
        }
    }

    private void showParticipantStatus(final Dealer dealer, final List<Player> players) {
        OutputView.printParticipantCardWithResult(dealer, dealer.getTotalPoint());
        players.forEach(player -> OutputView.printParticipantCardWithResult(player, player.getTotalPoint()));
    }

    private void showGameResult(final Dealer dealer, final List<Player> players) {
        BlackJackResults blackJackResults = new BlackJackResults(dealer, players);
        OutputView.printBlackJackResults(blackJackResults);
    }
}
