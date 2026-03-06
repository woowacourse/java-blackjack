package blackjack.domain.participant;

import blackjack.domain.deck.Deck;
import blackjack.domain.hand.Score;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    private Deck deck;

    public Dealer(Deck deck) {
        super(new Name(DEALER_NAME));
        this.deck = deck;
    }

    public void dealInitialCardsTo(Players players) {
        for (int i = 0; i < 2; i++) {
            dealRound(players);
        }
    }

    private void dealRound(Players players) {
        players.getPlayers()
                .forEach(player -> player.receiveCard(deck.draw()));
        receiveCard(deck.draw());
    }

    public void process(Players players) {
        processPlayersTurn(players);
        processDealerTurn();
    }

    private void processPlayersTurn(Players players) {
        players.getPlayers().forEach(player -> processPlayerTurn(player));
    }

    private void processPlayerTurn(final Player player) {
        boolean hasHit = false;
        while (player.canReceiveCard()) {
            final boolean hit = askHitAndProcess(player);
            hasHit = hasHit || hit;
        }
        printCardsIfNeverHit(player, hasHit);
    }

    private boolean askHitAndProcess(Player player) {
        final boolean wantsHit = InputView.readHitDecision(player.getName());
        if (!wantsHit) {
            player.stay();
            return false;
        }
        player.receiveCard(deck.draw());
        OutputView.printPlayerCards(player);
        return true;
    }

    private void printCardsIfNeverHit(final Player player, final boolean hasHit) {
        if (!hasHit) {
            OutputView.printPlayerCards(player);
        }
    }

    private void processDealerTurn() {
        while (canReceiveCard()) {
            receiveCard(deck.draw());
            OutputView.printDealerHit();
        }
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore().isLessThanOrEqualTo(new Score(HIT_THRESHOLD));
    }
}
