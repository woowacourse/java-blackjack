package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.hand.CardHand;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayStrategy;
import java.util.List;

public class BlackjackGame {

    private static final HitOrStayStrategy ALWAYS_HIT_STRATEGY = () -> true;

    private final CardStack cardStack;
    private final Participants participants;

    public BlackjackGame(final CardStack cardStack,
                         final List<String> playerNames) {

        this.cardStack = cardStack;
        this.participants = Participants.of(playerNames, cardStack::pop);
    }

    public void drawAllPlayerCards(final HitOrStayStrategy hitOrStayStrategy,
                                   final CardsViewStrategy cardsView) {

        List<Participant> players = participants.getPlayers();
        for (Participant player : players) {
            cardsView.print(player);
            player.drawAll(hitOrStayStrategy, cardsView, this::popCard);
        }
    }

    public void drawDealerCards(final CardsViewStrategy dealerView) {
        Participant dealer = getDealer();
        dealer.drawAll(ALWAYS_HIT_STRATEGY, dealerView, this::popCard);
    }

    public boolean isBlackjackDealer() {
        CardHand dealerHand = getDealer().getHand();
        return dealerHand.isBlackjack();
    }

    public Participants getParticipants() {
        return participants;
    }

    private Card popCard() {
        return cardStack.pop();
    }

    private Participant getDealer() {
        return participants.getDealer();
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardStack=" + cardStack +
                ", participants=" + participants +
                '}';
    }
}
