package domain.cardtable;

import domain.deck.CardDeck;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.participant.Money;
import domain.player.participant.Participant;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardTable {

    private final CardDeck cardDeck;

    private CardTable(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public static CardTable readyToPlayBlackjack(final CardDeck cardDeck) {
        return new CardTable(cardDeck);
    }

    public Map<Participant, Money> determineParticipantsBettingMoney(final List<Participant> participants,
                                                                     final Dealer dealer) {

        return participants.stream()
                           .peek(participant -> participant.finalMatchWith(dealer))
                           .collect(Collectors.toMap(
                                   Function.identity(),
                                   Participant::determineBetMoney)
                           );
    }

    public Money determineDealerMoney(final List<Participant> participants, final Dealer dealer) {

        final Map<Participant, Money> participantsResultMoney = determineParticipantsBettingMoney(participants, dealer);

        return participantsResultMoney.values()
                                      .stream()
                                      .reduce(Money.MIN, Money::plus)
                                      .lose();
    }

    public void matchAfterFirstDeal(final List<Participant> participants, final Dealer dealer) {

        participants.forEach(participant -> participant.firstMatchWith(dealer));
    }

    public void dealCardTo(Player player) {
        if (player.canHit()) {
            player.hit(cardDeck.draw());
        }
    }
}
