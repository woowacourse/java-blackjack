package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.participant.*;

public class BlackJackGame {

    private static final int NUMBER_OF_INITIAL_CARD = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final Dealer dealer, final String inputNames) {
        this.dealer = dealer;
        this.players = new Players(inputNames);
    }

    public void handOutCardTo(final ShufflingMachine shufflingMachine, final Participant participant) {
        final Card card = Deck.from(shufflingMachine.draw());
        participant.receiveCard(card);
    }

    public void handOutCards(final ShufflingMachine shufflingMachine) {
        handOutInitCardsTo(shufflingMachine, dealer);
        players.getPlayers()
                .forEach(player -> handOutInitCardsTo(shufflingMachine, player));
    }

    private void handOutInitCardsTo(final ShufflingMachine shufflingMachine, final Participant participant) {
        int count = 0;
        while (count != NUMBER_OF_INITIAL_CARD) {
            handOutCardTo(shufflingMachine, participant);
            count++;
        }
    }

    public void findWinner() {
        final int sumOfDealer = dealer.calculateSumOfRank();

        for (final Player player : players.getPlayers()) {
            final int sumOfPlayer = player.calculateSumOfRank();
            judgeResult(player, sumOfDealer, sumOfPlayer);
        }
    }

    private void judgeResult(final Player player, final int sumOfDealer, final int sumOfPlayer) {
        if (isBlackJackPlayer(player)) {
            return;
        }
        if (isBustPlayer(player)) {
            return;
        }
        judgeResultWhenPlayerIsNotBust(sumOfDealer, player, sumOfPlayer);
    }

    private boolean isBlackJackPlayer(final Player player) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            setUpResultWhenPush(player);
            return true;
        }
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            setUpResultWhenPlayerWin(player);
            return true;
        }
        return false;
    }

    private boolean isBustPlayer(final Player player) {
        if (player.isBust()) {
            setUpResultWhenDealerWin(player);
            return true;
        }
        return false;
    }

    private void judgeResultWhenPlayerIsNotBust(final int sumOfDealer, final Player player, final int sumOfPlayer) {
        if (dealer.isBust() || sumOfPlayer > sumOfDealer) {
            setUpResultWhenPlayerWin(player);
            return;
        }
        if (dealer.isBlackJack() || sumOfPlayer < sumOfDealer) {
            setUpResultWhenDealerWin(player);
            return;
        }
        setUpResultWhenPush(player);
    }

    private void setUpResultWhenPush(final Player player) {
        dealer.setResults(Result.PUSH);
        player.setResult(Result.PUSH);
    }

    private void setUpResultWhenDealerWin(final Player player) {
        dealer.setResults(Result.WIN);
        player.setResult(Result.LOSE);
    }

    private void setUpResultWhenPlayerWin(final Player player) {
        dealer.setResults(Result.LOSE);
        player.setResult(Result.WIN);
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
