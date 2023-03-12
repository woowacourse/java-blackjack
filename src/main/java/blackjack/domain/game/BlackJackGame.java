package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Participants participants;
    private final BettingTable bettingTable;
    private final Deck deck;

    private BlackJackGame(Participants participants, BettingTable bettingTable, Deck deck) {
        this.participants = participants;
        this.bettingTable = bettingTable;
        this.deck = deck;
    }

    public static BlackJackGame create(Players players, BettingTable bettingTable) {
        Participants participants = Participants.create(players);
        Deck deck = Deck.create(DeckFactory.createShuffledCard());

        return new BlackJackGame(participants, bettingTable, deck);
    }

    public void setUp() {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        for (Player player : players) {
            passCardTo(player);
            passCardTo(player);
        }
        passCardTo(dealer);
        passCardTo(dealer);
    }

    public void passCardTo(Participant participant) {
        if (participant.canReceive()) {
            Card card = deck.draw();
            participant.addCard(card);
        }
    }

    public Map<String, Integer> calculatePlayersProfit() {
        LinkedHashMap<String, Integer> playersProfit = new LinkedHashMap<>();
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        for (Player player : players) {
            Result result = Result.calculatePlayerResult(player, dealer);
            Betting initialBetting = bettingTable.getBetting(player.getName());

            int profit = result.calculateProfit(initialBetting);
            playersProfit.put(player.getName(), profit);
        }
        return playersProfit;
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
