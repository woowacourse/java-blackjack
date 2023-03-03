package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;

public class BlackJackGame {

    private static final int NUMBER_OF_INITIAL_CARD = 2;
    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final String inputNames) {
        this.dealer = new Dealer();
        this.players = new Players(inputNames);
    }

    public void handOutCardTo(final ShufflingMachine shufflingMachine, final Participant participant) {
        final Card card = Deck.from(shufflingMachine.draw());
        participant.receiveCard(card);
    }

    public void handOutInitCards(final ShufflingMachine shufflingMachine) {
        handOutInitCardsTo(shufflingMachine, dealer);
        players.getPlayers()
                .forEach(player -> handOutInitCardsTo(shufflingMachine, player));
    }

    private void handOutInitCardsTo(final ShufflingMachine shufflingMachine, final Participant participant) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARD; i++) {
            final Card card = Deck.from(shufflingMachine.draw());
            participant.receiveCard(card);
        }
    }

    public void findWinner() {
        final int sumOfDealer = dealer.calculateSumOfRank();

        for (final Player player : players.getPlayers()) {
            final int sumOfPlayer = player.calculateSumOfRank();

            if (player.isBlackJack() && dealer.isBlackJack()) {
                dealer.setResults(Result.PUSH);
                player.setResult(Result.PUSH);
                continue;
            }

            if (player.isBlackJack() && !dealer.isBlackJack()) {
                dealer.setResults(Result.LOSE);
                player.setResult(Result.WIN);
                continue;
            }

            if (player.isBust()) {
                dealer.setResults(Result.WIN);
                player.setResult(Result.LOSE);
                continue;
            }

            if (!player.isBust()) {
                if (dealer.isBust()) {
                    dealer.setResults(Result.LOSE);
                    player.setResult(Result.WIN);
                    continue;
                }

                if (dealer.isBlackJack()) {
                    dealer.setResults(Result.WIN);
                    player.setResult(Result.LOSE);
                    continue;
                }

                if (sumOfPlayer < sumOfDealer) {
                    dealer.setResults(Result.WIN);
                    player.setResult(Result.LOSE);
                    continue;
                }

                if (sumOfPlayer == sumOfDealer) {
                    dealer.setResults(Result.PUSH);
                    player.setResult(Result.PUSH);
                    continue;
                }

                if (sumOfPlayer > sumOfDealer) {
                    dealer.setResults(Result.LOSE);
                    player.setResult(Result.WIN);
                    continue;
                }
            }
        }
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
