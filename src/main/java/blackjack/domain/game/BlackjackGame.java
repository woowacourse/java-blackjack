package blackjack.domain.game;

import blackjack.domain.card.CardDeck;
import blackjack.domain.game.betting.Profits;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private Dealer dealer;
    private final Players players;

    public BlackjackGame(final Map<String, Integer> moneysByName) {
        this.cardDeck = CardDeck.generate();
        this.dealer = new Dealer(cardDeck.provideInitCards());
        this.players = new Players(initPlayers(moneysByName, cardDeck));
    }

    private List<Player> initPlayers(final Map<String, Integer> moneysByName, final CardDeck cardDeck) {
        return moneysByName.keySet()
                .stream()
                .map(name -> new Player(name, moneysByName.get(name), cardDeck.provideInitCards()))
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
        dealer = dealer.draw(cardDeck.provideCard());
    }

    public void stayDealer() {
        if (!dealer.isBust()) {
            dealer = dealer.stay();
        }
    }

    public Player getCurrentTurnPlayer() {
        return players.getCurrentTurnPlayerInfo();
    }

    public List<Participant> getParticipants() {
        final List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getValues());
        return participants;
    }

    public Map<String, Integer> getParticipantsProfit() {
        final Profits profits = new Profits(players.calculateProfit(dealer));
        return profits.getAllProfits();
    }
}
