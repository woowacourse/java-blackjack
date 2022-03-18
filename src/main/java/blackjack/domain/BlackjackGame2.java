package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant2.Participant;
import blackjack.domain.participant2.Participants;
import blackjack.strategy.CardsViewStrategy2;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;

public class BlackjackGame2 {

    private static final HitOrStayChoiceStrategy ALWAYS_HIT_STRATEGY = () -> true;

    private final CardStack cardDeck;
    private final Participants participants;

    public BlackjackGame2(final CardStack cardDeck,
                          final List<String> playerNames) {

        this.cardDeck = cardDeck;
        this.participants = Participants.of(playerNames, cardDeck::pop);
    }

    public void drawAllPlayerCards(final HitOrStayChoiceStrategy hitOrStayStrategy,
                                   final CardsViewStrategy2 cardsView) {

        List<Participant> players = participants.getPlayers();
        for (Participant player : players) {
            cardsView.print(player);
            player.drawAll(hitOrStayStrategy, cardsView, this::popCard);
        }
    }

    public void drawDealerCards(final CardsViewStrategy2 dealerView) {
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
