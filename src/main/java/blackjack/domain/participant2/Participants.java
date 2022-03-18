package blackjack.domain.participant2;

import static blackjack.validator.PlayerNameValidator.validateNoDuplicateNames;
import static blackjack.validator.PlayerNameValidator.validatePlayerExistence;

import blackjack.domain.card.Card;
import blackjack.domain.hand.CardHand;
import blackjack.domain.hand.OneCard;
import blackjack.strategy.CardSupplier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final Participant dealer;
    private final List<Participant> players;

    private Participants(final Participant dealer, final List<Participant> players) {
        this.dealer = dealer;
        this.players = Collections.unmodifiableList(players);
    }

    public static Participants of(final List<String> playerNames,
                                  final CardSupplier cardSupplier) {

        final Participant dealer = new Dealer(initialCardHand(cardSupplier));
        final List<Participant> players = initializePlayers(playerNames, cardSupplier);

        return new Participants(dealer, players);
    }

    private static List<Participant> initializePlayers(final List<String> playerNames,
                                                       final CardSupplier cardSupplier) {
        validatePlayerExistence(playerNames);
        validateNoDuplicateNames(playerNames);
        return playerNames.stream()
                .map(name -> new Player(name, initialCardHand(cardSupplier)))
                .collect(Collectors.toUnmodifiableList());
    }

    private static CardHand initialCardHand(CardSupplier cardSupplier) {
        Card card1 = cardSupplier.getCard();
        Card card2 = cardSupplier.getCard();

        return new OneCard(card1).hit(card2);
    }

    public List<Participant> getValue() {
        List<Participant> participants = new ArrayList<>();

        participants.add(dealer);
        participants.addAll(players);

        return Collections.unmodifiableList(participants);
    }

    @Override
    public String toString() {
        return "Participants{" +
                "dealer=" + dealer +
                ", players=" + players +
                '}';
    }
}
