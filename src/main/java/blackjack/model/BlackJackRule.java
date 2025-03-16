package blackjack.model;

import blackjack.model.game.Deck;
import blackjack.model.game.DeckInitializer;
import blackjack.model.game.MoneyDistributor;
import blackjack.model.game.ParticipantResult;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.model.player.Player;

import java.util.Map;

public class BlackJackRule {

    public static Deck generateDeck() {
        return new DeckInitializer().generateDeck();
    }

    public static void initializeBlackJack(final Deck deck, final Dealer dealer, final Participants participants) {
        participants.getParticipants()
                .forEach(participant -> giveTwoCards(participant, deck));
        giveTwoCards(dealer, deck);
    }

    private static void giveTwoCards(final Player player, final Deck deck) {
        player.putCard(deck.drawCard());
        player.putCard(deck.drawCard());
    }

    public static void giveCard(final Player player, final Deck deck) {
        player.putCard(deck.drawCard());
    }

    public static void giveCardToCurrentTurnParticipant(final Participant participant, final boolean isParticipantWantCard, final Deck deck) {
        if (isParticipantWantCard) {
            giveCard(participant, deck);
        }
    }

    public static boolean isDealerDrawable(final Dealer dealer) {
        return dealer.isCardDrawable();
    }

    public static Map<Participant, Integer> calculateWinningMoney(final Dealer dealer, final Participants participants) {
        Map<Participant, ParticipantResult> participantResults = ParticipantResult.calculateParticipantResults(dealer, participants);
        return MoneyDistributor.calculateWinningMoneys(dealer, participantResults);
    }

    public static int calculateDealerWinningMoney(final Map<Participant, Integer> winningMoney) {
        return MoneyDistributor.calculateDealerMoney(winningMoney);
    }

    public static boolean isPlayerBust(final Participant participant) {
        return participant.isBust();
    }
}
