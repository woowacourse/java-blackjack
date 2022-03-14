package blackjack.domain.game;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    private BlackJackGame(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackJackGame init(final List<String> playerNames) {
        final CardDeck cardDeck = CardDeck.generate();
        final Dealer dealer = new Dealer(cardDeck.provideInitCards());
        final List<Player> players = initPlayers(playerNames, cardDeck);
        return new BlackJackGame(cardDeck, dealer, new Players(players));
    }

    private static List<Player> initPlayers(final List<String> playerNames, final CardDeck cardDeck) {
        return playerNames.stream()
                .map(name -> Player.newInstance(name, cardDeck.provideInitCards()))
                .collect(Collectors.toList());
    }

    public boolean isAllPlayersEnd() {
        return players.isAllTurnEnd();
    }

    public Player drawCurrentPlayer() {
        return players.drawCurrentPlayer(cardDeck.provideCard());
    }

    public Player drawNextPlayer() {
        final Player currentPlayer = players.getCurrentTurnPlayerInfo();
        players.turnToNextPlayer();
        return currentPlayer;
    }

    public boolean isDealerTurnEnd() {
        return !dealer.canDraw();
    }

    public void drawDealer() {
        dealer.draw(cardDeck.provideCard());
    }

    public void stayDealer() {
        if (!dealer.isBust()) {
            dealer.stay();
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getInitPlayers() {
        return players.getInitPlayers();
    }

    public Player getCurrentTurnPlayer() {
        return players.getCurrentTurnPlayerInfo();
    }

    public List<Participant> getParticipants() {
        final List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getInitPlayers());
        return participants;
    }

    public OutComeResult getWinningResult() {
        final Map<String, GameOutcome> playerResults = players.calculateAllResults(dealer);
        return OutComeResult.from(playerResults);
    }
}
