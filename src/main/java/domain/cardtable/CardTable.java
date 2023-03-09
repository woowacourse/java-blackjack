package domain.cardtable;

import domain.deck.CardDeck;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.participant.Money;
import domain.player.participant.Participant;
import domain.player.participant.betresult.BetResultState;
import domain.player.participant.betresult.NotYetState;

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
        final Map<Participant, BetResultState> result = matchAfterFirstDeal(participants, dealer);

        return result.keySet()
                     .stream()
                     .collect(Collectors.toMap(
                             Function.identity(),
                             participant -> {
                                 if (result.get(participant) instanceof NotYetState) {
                                     return participant.determineBetMoney(participant.finalMatchWith(dealer));
                                 }
                                 return participant.determineBetMoney(result.get(participant));
                             }
                     ));
    }

    public Money determineDealerMoney(final List<Participant> participants, final Dealer dealer) {

        final Map<Participant, Money> participantsResultMoney = determineParticipantsBettingMoney(participants, dealer);

        return participantsResultMoney.keySet()
                                      .stream()
                                      .map(Participant::determineBetMoney)
                                      .reduce(Money.MIN, Money::plus)
                                      .lose();
    }

    public Map<Participant, BetResultState> matchAfterFirstDeal(final List<Participant> participants,
                                                                final Dealer dealer) {

        return participants.stream()
                           .collect(Collectors.toMap(
                                   Function.identity(),
                                   participant -> participant.firstMatchWith(dealer)
                           ));
    }

    public boolean dealCardTo(Player player) {
        if (player.canHit()) {
            player.hit(cardDeck.draw());
            return true;
        }
        return false;
    }
}
