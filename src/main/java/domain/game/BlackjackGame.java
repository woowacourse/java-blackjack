package domain.game;

import domain.card.DealerCards;
import domain.card.Deck;
import domain.card.PlayerCards;
import domain.player.Bet;
import domain.player.Name;
import domain.player.Names;
import domain.score.ScoreBoard;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final Deck deck = new Deck();

    private final Map<Name, PlayerCards> players;
    private final DealerCards dealer;

    private BlackjackGame(Map<Name, PlayerCards> players, DealerCards dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame from(Names names) {
        Map<Name, PlayerCards> players = names.getNames().stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> new PlayerCards(deck.drawTwoCards())
                ));
        DealerCards dealer = new DealerCards(deck.drawTwoCards());
        return new BlackjackGame(players, dealer);
    }

    public ScoreBoard payout(Map<Name, Bet> bets) {
        ScoreBoard scoreBoard = new ScoreBoard(bets);
        Referee referee = new Referee(scoreBoard);
        referee.decideResult(dealer, players);
        return scoreBoard;
    }

    public void drawPlayerCards(Name name) {
        PlayerCards player = player(name);
        if (player.canDraw()) {
            player.receive(deck.draw());
        }
    }

    public void drawDealerCards() {
        dealer.receive(deck.draw());
    }

    public boolean dealerCanDraw() {
        return dealer.canDraw();
    }

    public boolean playerCanDraw(Name name) {
        PlayerCards player = player(name);
        return player.canDraw();
    }

    public PlayerCards player(Name name) {
        return players.get(name);
    }

    public Map<Name, PlayerCards> players() {
        return Collections.unmodifiableMap(players);
    }

    public DealerCards dealer() {
        return dealer;
    }
}
