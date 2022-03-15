package blackjack.model;

import blackjack.model.card.CardDeck;
import blackjack.model.player.Dealer;
import blackjack.model.player.Players;
import blackjack.model.player.Participants;
import blackjack.model.player.Participant;
import blackjack.model.result.BlackJackGameResult;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BlackjackGame {
    private final Participants participants;
    private final CardDeck cardDeck;
    private final Participant dealer;
    private final Players players;

    public BlackjackGame(final List<String> names) {
        this.participants = new Participants(names);
        this.cardDeck = new CardDeck();
        this.dealer = new Dealer();
        this.players = Players.from(names);
    }

    private BlackjackGame(final Participants participants, final CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
        this.dealer = new Dealer();
        this.players = null;
    }

    public BlackjackGame start() {
        return new BlackjackGame(participants.drawCardsBy(cardDeck), this.cardDeck);
    }

    public void hitOrStayUntilPossible(Predicate<String> predicate, Consumer<Participant> consumer) {
        players.hitOrStayToGamer(predicate, consumer);
        hitOrStayToDealer(consumer);
    }

    private void hitOrStayToDealer(Consumer<Participant> consumer) {
        CardDeck deck = CardDeck.getInstance();
        while (!dealer.isBlackJack() && !dealer.isImpossibleHit()) {
            dealer.receive(deck.draw());
        }
        consumer.accept(dealer);
    }

    public BlackJackGameResult createMatchResult() {
        return new BlackJackGameResult(dealer, players);
    }

    public Participant getDealer2() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getGamers() {
        return players.getValues();
    }
}
