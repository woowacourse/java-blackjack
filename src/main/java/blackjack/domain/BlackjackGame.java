package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Gamer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackGame {

    private static final int SPREAD_SIZE = 1;

    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public void spreadInitialCards() {
        int cardsCount = participants.getInitialTotalCardsSize();
        final Hand hand = deck.spreadCards(cardsCount);
        participants.spreadAllTwoCards(hand);
    }

    public boolean canPlayerHit(final int index) {
        return participants.canPlayerHit(index);
    }

    public boolean canDealerHit() {
        return participants.canDealerHit();
    }

    public void spreadOneCardToPlayer(final Gamer gamer) {
        final Hand hand = deck.spreadCards(SPREAD_SIZE);
        gamer.receiveCards(new Hand(List.of(hand.getFirstCard())));
    }

    public void spreadOneCardToDealer() {
        final Hand hand = deck.spreadCards(SPREAD_SIZE);
        participants.spreadOneCardToDealer(hand.getFirstCard());
    }

    public int calculateScore(final Gamer gamer) {
        return gamer.calculateScore();
    }

    public Map<String, ResultStatus> calculateWinningResult() {
        return participants.calculateWinningResult();
    }

    public Entry<String, Hand> showInitialDealerCard() {
        return participants.showInitialDealerCards();
    }

    public Map<String, Hand> showInitialPlayersCards() {
        return participants.showInitialParticipantsCards();
    }

    public Entry<String, Hand> showDealerCard() {
        return participants.showDealerCards();
    }

    public Map<String, Hand> showPlayersCards() {
        return participants.showPlayersCards();
    }

    public Players findExtraCardsAvailablePlayers() {
        return participants.findHitAvailablePlayers();
    }

    public Gamer getPlayer(final int index) {
        return participants.getPlayers().getPlayer(index);
    }

    public List<String> getPlayersNames() {
        return participants.getPlayersNames();
    }
}
