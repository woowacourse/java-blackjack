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

    public BlackJackGame(final List<String> playerNames) {
        this.cardDeck = CardDeck.generate();
        this.dealer = new Dealer(cardDeck.provideInitCards());
        this.players = new Players(initPlayers(playerNames, cardDeck));
    }

    private List<Player> initPlayers(final List<String> playerNames, final CardDeck cardDeck) {
        return playerNames.stream()
                .map(name -> new Player(name, cardDeck.provideInitCards()))
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
        return new OutComeResult(playerResults);
    }
}
