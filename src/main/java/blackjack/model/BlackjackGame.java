package blackjack.model;

import blackjack.model.card.CardDeck;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Players;
import blackjack.model.result.BlackJackGameResult;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BlackjackGame {
    private final Players players;
    private final Participant dealer;
    private final CardDeck cardDeck;

    public BlackjackGame(final List<String> names) {
        this.players = Players.from(names);
        this.dealer = new Dealer();
        this.cardDeck = new CardDeck();
    }

    private BlackjackGame(final Players players, final Participant dealer) {
        this.players = players;
        this.dealer = dealer;
        this.cardDeck = null;
    }

    public BlackjackGame start() {
        return new BlackjackGame(players.drawCardsBy(cardDeck), dealer.drawCardsBy(cardDeck));
    }

    public void performEachTurn(Predicate<String> predicate, Consumer<Participant> consumer) {
        for (Participant player : players.getPlayerGroup()) {
            hitOrStayToPlayer(player, predicate, consumer);
        }
        hitOrStayToDealer(consumer);
    }

    private void hitOrStayToPlayer(Participant player, Predicate<String> predicate, Consumer<Participant> consumer) {
        while (player.canHit() && isHitSign(player, predicate)) {
            consumer.accept(player.hitBy(cardDeck));
        }
    }

    private boolean isHitSign(Participant player, Predicate<String> predicate) {
        return predicate.test(player.getName());
    }

    private void hitOrStayToDealer(Consumer<Participant> consumer) {
        Participant dealer = null;
        while (this.dealer.canHit()) {
            dealer = this.dealer.hitBy(cardDeck);
        }
        consumer.accept(dealer);
    }

    public BlackJackGameResult createMatchResult() {
        return new BlackJackGameResult(dealer, players);
    }

    public List<Participant> getPlayers() {
        return players.getPlayerGroup();
    }

    public Participant getDealer() {
        return dealer.getCopyInstance();
    }
}
