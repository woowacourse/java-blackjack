package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Participant;
import blackjack.strategy.CardBundleStrategy;
import blackjack.strategy.CardBundleSupplier;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;

public class BlackjackGame {

    private static final HitOrStayChoiceStrategy ALWAYS_HIT_STRATEGY = () -> true;

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

    public void drawAllPlayerCards(final HitOrStayChoiceStrategy hitOrStayStrategy,
                                   final CardsViewStrategy viewStrategy) {

        List<Participant> players = participants.getPlayers();
        for (Participant player : players) {
            drawPlayerCards(player, hitOrStayStrategy, viewStrategy);
        }
    }

    public void drawPlayerCards(final Participant player,
                                final HitOrStayChoiceStrategy hitOrStayStrategy,
                                final CardsViewStrategy playerView) {

        playerView.print(player);
        player.drawAllCards(hitOrStayStrategy, playerView, this::popCard);
    }

    public void drawDealerCards(final CardsViewStrategy dealerView) {
        Participant dealer = getDealer();
        dealer.drawAllCards(ALWAYS_HIT_STRATEGY, dealerView, this::popCard);
    }

    private Card popCard() {
        return cardDeck.pop();
    }

    public boolean isBlackjackDealer() {
        return getDealer().isBlackjack();
    }

    public GameParticipants getParticipants() {
        return participants;
    }

    private Participant getDealer() {
        return participants.getDealer();
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", participants=" + participants +
                '}';
    }
}
