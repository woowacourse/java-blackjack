package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Player;
import blackjack.strategy.CardBundleStrategy;
import blackjack.strategy.CardBundleSupplier;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackjackGame {

    private final CardStack cardDeck;
    private final GameParticipants participants;

    public BlackjackGame(final CardStack cardDeck,
                         final List<String> playerNames,
                         final CardBundleStrategy strategy) {

        this.cardDeck = cardDeck;
        this.participants = generateParticipantsFrom(playerNames, strategy);
    }

    private GameParticipants generateParticipantsFrom(final List<String> playerNames,
                                                      final CardBundleStrategy strategy) {

        CardBundleSupplier cardBundleSupplier = () -> strategy.initCardBundle(cardDeck);
        return GameParticipants.of(playerNames, cardBundleSupplier);
    }

    public void distributeAllCards(final Function<String, Boolean> drawOrStayStrategy,
                                   final Consumer<Player> viewStrategy,
                                   final Runnable dealerViewStrategy) {

        List<Player> players = participants.getPlayers();

        for (Player player : players) {
            player.drawAllCards(drawOrStayStrategy, this::popCard, viewStrategy);
        }
        drawAllDealerCards(dealerViewStrategy);
    }

    private void drawAllDealerCards(final Runnable dealerViewStrategy) {
        Dealer dealer = participants.getDealer();
        while (dealer.canDraw()) {
            dealer.receiveCard(popCard());
            dealerViewStrategy.run();
        }
    }

    private Card popCard() {
        return cardDeck.pop();
    }

    public boolean isBlackjackDealer() {
        return participants.getDealer().isBlackjack();
    }

    public GameParticipants getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", participants=" + participants +
                '}';
    }
}
