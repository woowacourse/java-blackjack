package domain.cardtable;

import domain.area.CardArea;
import domain.deck.CardDeck;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.participant.Participant;
import domain.player.participant.ParticipantResult;

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

    public Map<Participant, ParticipantResult> determineWinner(final List<Participant> participants,
                                                              final Dealer dealer) {
        return participants.stream()
                           .collect(Collectors.toMap(
                                   Function.identity(),
                                   participant -> matchBetween(participant, dealer))
                           );
    }

    private ParticipantResult matchBetween(final Participant participant, final Dealer dealer) {
        if (participant.isBust()) {
            return ParticipantResult.LOSER;
        }
        if (dealer.isBust()) {
            return ParticipantResult.WINNER;
        }
        if (participant.score().isGreaterThan(dealer.score())) {
            return ParticipantResult.WINNER;
        }
        if (participant.score().equals(dealer.score())) {
            return ParticipantResult.DRAWER;
        }
        return ParticipantResult.LOSER;
    }

    public void dealCardTo(Player player) {
        player.hit(cardDeck.draw());
    }
}
