package domain.blackjack;

import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Blackjack {
    private final Players players;
    private final Deck deck;

    public Blackjack(final Players players) {
        this.players = players;
        this.deck = new Deck();
        players.register(Dealer.getInstance());
        dealCardsToPlayers();
    }

    public Blackjack(final Players players, final Player dealer) {
        this.players = players;
        this.deck = new Deck();
        players.register(dealer);
    }

    public void dealCard(final Player player) {
        player.hit(deck.draw());
    }

    private void dealCardsToPlayers() {
        players.getAllPlayers().forEach(this::dealInitialCards);
    }

    public GameResult finishGame() {
        final Player dealer = players.getDealer();
        final List<Player> participants = players.getParticipants();
        final Map<Player, OneOnOneResult> result = new LinkedHashMap<>();

        judgeGameResult(participants, result, dealer);

        return new GameResult(result);
    }

    private void judgeGameResult(
            final List<Player> participants,
            final Map<Player, OneOnOneResult> result,
            final Player dealer) {
        OneOnOneResult dealerResult = OneOnOneResult.EMPTY;

        for (final Player participant : participants) {
            result.put(participant, participant.determineWinnerByComparing(dealer));
            dealerResult = dealerResult.updateStatusByWinOrLose(dealer.determineWinnerByComparing(participant));
        }

        result.put(dealer, dealerResult);
    }

    private void dealInitialCards(final Player player) {
        player.hit(deck.draw());
        player.hit(deck.draw());
    }

    public List<Player> getPlayers() {
        return players.getAllPlayers();
    }

    public Player getDealer() {
        return players.getDealer();
    }

    public List<Player> getParticipants() {
        return players.getParticipants();
    }
}
