package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;

public class BlackJackGame {

    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final String inputNames) {
        this.dealer = new Dealer();
        this.players = new Players(inputNames);
    }

    public void handOutCardTo(final CardMachine cardMachine, final Participant participant) {
        Card card = Deck.from(cardMachine.draw());
        participant.receiveCard(card);
    }

    public void handOutInitCards(final CardMachine cardMachine) {
        handOutInitCardsTo(cardMachine, dealer);
        players.getPlayers()
                .forEach(player -> handOutInitCardsTo(cardMachine, player));
    }

    private void handOutInitCardsTo(final CardMachine cardMachine, final Participant participant) {
        for (int i = 0; i < 2; i++) {
            Card card = Deck.from(cardMachine.draw());
            participant.receiveCard(card);
        }
    }

    public void findWinner() {
        int sumOfDealer = dealer.calculateSumOfRank();

        for (Player player : players.getPlayers()) {
            int sumOfPlayer = player.calculateSumOfRank();
            if (sumOfPlayer < sumOfDealer) {
                dealer.setResults(Result.WIN);
                player.setResult(Result.LOSE);
                continue;
            }
            dealer.setResults(Result.LOSE);
            player.setResult(Result.WIN);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
