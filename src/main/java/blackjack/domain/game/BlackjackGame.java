package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Player;
import blackjack.strategy.CardBundleStrategy;
import blackjack.strategy.CardBundleSupplier;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.DealerViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import blackjack.strategy.PlayerViewStrategy;
import java.util.List;

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

    public void distributeAllCards(final HitOrStayChoiceStrategy hitOrStayStrategy,
                                   final PlayerViewStrategy viewStrategy,
                                   final DealerViewStrategy dealerViewStrategy) {

        List<Player> players = participants.getPlayers();

        for (Player player : players) {
            drawAllPlayerCards(player, hitOrStayStrategy, this::popCard, viewStrategy);
        }
        drawAllDealerCards(dealerViewStrategy);
    }

    public void drawAllPlayerCards(final Player player,
                                   final HitOrStayChoiceStrategy hitOrStayStrategy,
                                   final CardSupplier cardSupplier,
                                   final PlayerViewStrategy viewStrategy) {

        viewStrategy.printOf(player);
        while (player.canDraw()) {
            player.hitOrStay(hitOrStayStrategy, cardSupplier);
            viewStrategy.printOf(player);
        }
    }

    private void drawAllDealerCards(final DealerViewStrategy dealerView) {
        Dealer dealer = participants.getDealer();
        while (dealer.canDraw()) {
            dealer.receiveCard(popCard());
            dealerView.print();
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
