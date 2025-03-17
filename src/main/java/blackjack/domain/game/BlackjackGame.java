package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.Judge;
import blackjack.domain.result.PlayerResults;
import java.util.List;

public class BlackjackGame {
    private final Deck deck;
    private final Players players;
    private final Dealer dealer;
    private final Judge judge;

    public BlackjackGame(Deck deck, Players players, Dealer dealer, Judge judge) {
        this.deck = deck;
        this.players = players;
        this.dealer = dealer;
        this.judge = judge;
    }

    public void giveStartingCards() {
        players.getPlayers().forEach(this::giveStartingCards);
        giveStartingCards(dealer);
    }

    private void giveStartingCards(Participant participant) {
        List<Card> cards = deck.takeStartingCards();
        cards.forEach(participant::takeCard);
    }

    public void giveMoreCard(Participant participant) {
        Card card = deck.takeSingleCard();
        participant.takeCard(card);
    }

    public void judgeResults() {
        judge.calculateAllResults(dealer, players);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public PlayerResults getPlayerResults() {
        return judge.getPlayerResults();
    }

    public DealerResult getDealerResult() {
        return judge.getDealerResult();
    }
}
