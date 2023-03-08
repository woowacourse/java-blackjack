package domain.cardtable;

import domain.deck.CardDeck;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.participant.Money;
import domain.player.participant.Participant;
import domain.player.participant.betresult.BetResultState;
import domain.player.participant.betresult.BreakEvenState;
import domain.player.participant.betresult.LoseState;
import domain.player.participant.betresult.WinState;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Map<Participant, Money> determineBettingMoney(final List<Participant> participants,
                                                         final Dealer dealer) {
        return participants.stream()
                           .collect(Collectors.toMap(
                                   Function.identity(),
                                   participant -> {
                                       matchAfterFinalDeal(dealer, participant);
                                       return participant.determineBetMoney();
                                   })
                           );
    }

    private void matchAfterFinalDeal(final Dealer dealer, final Participant participant) {
        if (participant.hasNotBetState()) {
            participant.determineBetState(matchBetween(participant, dealer));
        }
    }

    private BetResultState matchBetween(final Participant participant, final Dealer dealer) {
        if (participant.isBust()) {
            return new LoseState();
        }
        if (dealer.isBust()) {
            return new BreakEvenState();
        }
        if (participant.score().isLessThan(dealer.score())) {
            return new LoseState();
        }
        return new BreakEvenState();
    }

    public boolean dealCardTo(Player player) {
        if (player.canHit()) {
            player.hit(cardDeck.draw());
            return true;
        }
        return false;
    }

    public void matchAfterFirstDeal(final List<Participant> participants,
                                    final Dealer dealer) {
        participants.forEach(
                participant -> matchFirstDealBetween(participant, dealer).
                        ifPresent(participant::determineBetState)
        );
    }

    private Optional<BetResultState> matchFirstDealBetween(final Participant participant, final Dealer dealer) {
        if (participant.isBlackjack() && dealer.isBlackjack()) {
            return Optional.of(new BreakEvenState());
        }

        if (participant.isBlackjack() && !dealer.isBlackjack()) {
            return Optional.of(new WinState());
        }

        if (!participant.isBlackjack() && dealer.isBlackjack()) {
            return Optional.of(new LoseState());
        }

        return Optional.empty();
    }
}
