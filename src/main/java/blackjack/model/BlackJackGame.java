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

public class BlackJackGame {
    private static final int START_CARD_COUNT = 2;

    private final Participants participants;
    private final Participant dealer;
    private final Players players;

    public BlackJackGame(final List<String> names) {
        this.participants = new Participants(names);
        this.dealer = new Dealer();
        this.players = new Players(names);
    }

    public void start() {
        giveCardsToGamers();
        giveCardsToDealer();
    }

    private void giveCardsToDealer() {
        CardDeck deck = CardDeck.getInstance();
        for (int i = 0; i < START_CARD_COUNT; i++) {
            dealer.receive(deck.draw());
        }
    }

    private void giveCardsToGamers() {
        players.giveCardsToGamer();
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

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getGamers() {
        return players.getValues();
    }
}
