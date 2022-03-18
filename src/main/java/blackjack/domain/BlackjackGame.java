package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayStrategy;
import java.util.List;

public class BlackjackGame {

    private static final HitOrStayStrategy ALWAYS_HIT_STRATEGY = () -> true;

    private final CardStack cardDeck;
    private final Participants participants;

    public BlackjackGame(final CardStack cardDeck,
                         final List<String> playerNames) {

        this.cardDeck = cardDeck;
        this.participants = Participants.of(playerNames, cardDeck::pop);
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

    private Card popCard() {
        return cardDeck.pop();
    }

    private Participant getDealer() {
        return participants.getDealer();
    }

    public Participants getParticipants() {
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
