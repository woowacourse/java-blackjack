package blackjack.domain.game;

import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class BlackJackGame {

    private static final int DRAWING_INITIAL_CARD_SIZE = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void handOutCards(final ShufflingMachine shufflingMachine) {
        handOutCardTo(shufflingMachine, dealer, DRAWING_INITIAL_CARD_SIZE);
        players.getPlayers()
                .forEach(player -> handOutCardTo(shufflingMachine, player, DRAWING_INITIAL_CARD_SIZE));
    }

    public void handOutCardTo(final ShufflingMachine shufflingMachine, final Participant participant, int cardSize) {
        while (cardSize-- > 0) {
            final Card card = Deck.from(shufflingMachine.draw());
            participant.receiveCard(card);
        }
    }

    public FinalProfit makePlayerProfit(final Map<Player, Money> playerProfit) {
        final int sumOfDealer = dealer.calculateSumOfRank();

        for (final Player player : players.getPlayers()) {
            final int sumOfPlayer = player.calculateSumOfRank();
            judgeResult(player, sumOfDealer, sumOfPlayer, playerProfit);
        }

        return new FinalProfit(playerProfit);
    }

    private void judgeResult(final Player player, final int sumOfDealer, final int sumOfPlayer,
                             final Map<Player, Money> playerResult) {
        if (isBlackJackPlayer(player, playerResult)) {
            return;
        }
        if (isBustPlayer(player, playerResult)) {
            return;
        }
        judgeResultWhenPlayerIsNotBust(sumOfDealer, player, sumOfPlayer, playerResult);
    }

    private boolean isBlackJackPlayer(final Player player, final Map<Player, Money> playerResult) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            playerResult.put(player, playerResult.get(player).makeZero());
            return true;
        }
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            playerResult.put(player, playerResult.get(player).calculateIfBlackJack());
            return true;
        }
        return false;
    }

    private boolean isBustPlayer(final Player player, final Map<Player, Money> playerResult) {
        if (player.isBust()) {
            playerResult.put(player, playerResult.get(player).changeSign());
            return true;
        }
        return false;
    }

    private void judgeResultWhenPlayerIsNotBust(final int sumOfDealer, final Player player, final int sumOfPlayer,
                                                final Map<Player, Money> playerResult) {
        if (dealer.isBust() || sumOfPlayer > sumOfDealer) {
            return;
        }
        if (dealer.isBlackJack() || sumOfPlayer < sumOfDealer) {
            playerResult.put(player, playerResult.get(player).changeSign());
            return;
        }
        playerResult.put(player, playerResult.get(player).makeZero());
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
