package blackjack.domain.game;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class BlackJackGame {

    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void hit(final ShufflingMachine shufflingMachine, final Participant participant) {
        final Card card = Deck.from(shufflingMachine.draw());
        participant.receiveCard(card);
    }

    public void handOutCards(final ShufflingMachine shufflingMachine) {
        handOutInitCardsTo(shufflingMachine, dealer);
        players.getPlayers()
                .forEach(player -> handOutInitCardsTo(shufflingMachine, player));
    }

    private void handOutInitCardsTo(final ShufflingMachine shufflingMachine, final Participant participant) {
        int cardSize = 2;
        while (cardSize-- > 0) {
            hit(shufflingMachine, participant);
        }
    }

    public Map<Player, ResultType> makePlayerResult() {
        final Map<Player, ResultType> playerResult = new LinkedHashMap<>();
        final int sumOfDealer = dealer.calculateSumOfRank();

        for (final Player player : players.getPlayers()) {
            final int sumOfPlayer = player.calculateSumOfRank();
            judgeResult(player, sumOfDealer, sumOfPlayer, playerResult);
        }

        return playerResult;
    }

    private void judgeResult(final Player player, final int sumOfDealer, final int sumOfPlayer,
                             final Map<Player, ResultType> playerResult) {
        if (isBlackJackPlayer(player, playerResult)) {
            return;
        }
        if (isBustPlayer(player, playerResult)) {
            return;
        }
        judgeResultWhenPlayerIsNotBust(sumOfDealer, player, sumOfPlayer, playerResult);
    }

    private boolean isBlackJackPlayer(final Player player, final Map<Player, ResultType> playerResult) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            playerResult.put(player, ResultType.PUSH);
            return true;
        }
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            playerResult.put(player, ResultType.WIN);
            return true;
        }
        return false;
    }

    private boolean isBustPlayer(final Player player, final Map<Player, ResultType> playerResult) {
        if (player.isBust()) {
            playerResult.put(player, ResultType.LOSE);
            return true;
        }
        return false;
    }

    private void judgeResultWhenPlayerIsNotBust(final int sumOfDealer, final Player player, final int sumOfPlayer,
                                                final Map<Player, ResultType> playerResult) {
        if (dealer.isBust() || sumOfPlayer > sumOfDealer) {
            playerResult.put(player, ResultType.WIN);
            return;
        }
        if (dealer.isBlackJack() || sumOfPlayer < sumOfDealer) {
            playerResult.put(player, ResultType.LOSE);
            return;
        }
        playerResult.put(player, ResultType.PUSH);
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
