package blackjack.domain.game;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final CardDeck cardDeck;
    private Dealer dealer;
    private final Players players;

    public BlackJackGame(final Map<String, String> playerNames) {
        this.cardDeck = CardDeck.generate();
        this.dealer = new Dealer(cardDeck.provideInitCards());
        this.players = new Players(initPlayers(playerNames, cardDeck));
    }

    private List<Player> initPlayers(final Map<String, String> moneysByName, final CardDeck cardDeck) {
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
        participants.addAll(players.getInitPlayers());
        return participants;
    }

    public Map<String, Integer> getParticipantsProfit() {
        final Map<String, Integer> playersProfit = players.calculateProfit(dealer);
        final Map<String, Integer> participantsProfit = new HashMap<>();
        participantsProfit.put(dealer.getName(), calculateDealerProfit(playersProfit.values()));
        participantsProfit.putAll(playersProfit);
        return participantsProfit;
    }

    private int calculateDealerProfit(final Collection<Integer> playerResults) {
        int totalPlayersProfit = playerResults.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return totalPlayersProfit * -1;
    }
}
