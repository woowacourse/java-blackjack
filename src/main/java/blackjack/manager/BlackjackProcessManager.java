package blackjack.manager;

import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.GameResultType;
import blackjack.domain.Player;
import java.util.Comparator;
import java.util.List;

public class BlackjackProcessManager {
    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;
    private final Deck deck;

    public BlackjackProcessManager(Deck deck) {
        this.deck = deck;
    }

    public void giveStartingCards(CardHolder cardHolder) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);
        cards.forEach(cardHolder::takeCard);
    }

    public void giveCard(CardHolder cardHolder) {
        List<Card> card = deck.takeCards(ADDITIONAL_CARD_SIZE);
        card.forEach(cardHolder::takeCard);
    }

    public GameResultType decideResultOfPlayer(Player player, Dealer dealer) {
        int playerValue = findOptimisticValue(player.getPossibleSums());
        int dealerValue = findOptimisticValue(dealer.getPossibleSums());

        return GameResultType.find(playerValue, dealerValue);
    }

    private int findOptimisticValue(List<Integer> possibleSums) {
        return possibleSums.stream().filter(sum -> sum <= 21)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }
}
