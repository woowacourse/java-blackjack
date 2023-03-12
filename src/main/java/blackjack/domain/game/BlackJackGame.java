package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
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
            player.addCard(deck.draw());
            player.addCard(deck.draw());
        }
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    public void passCardToDealer() {
        Dealer dealer = participants.getDealer();
        if (dealer.canReceive()) {
            Card card = deck.draw();
            dealer.addCard(card);
        }
    }

    public void passCardToPlayer(Name name) {
        Player player = participants.getPlayer(name);
        if (player.canReceive()) {
            Card card = deck.draw();
            player.addCard(card);
        }
    }

    public Map<Name, Integer> calculatePlayersProfit() {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        return calculateProfit(players, dealer);
    }

    public int calculateDealerProfit() {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        Map<Name, Integer> playersProfit = calculateProfit(players, dealer);

        return playersProfit.values()
                .stream()
                .reduce(0, Integer::sum) * -1;
    }

    private Map<Name, Integer> calculateProfit(List<Player> players, Dealer dealer) {
        LinkedHashMap<Name, Integer> playersProfit = new LinkedHashMap<>();

        for (Player player : players) {
            Result result = Result.calculatePlayerResult(player, dealer);
            Betting initialBetting = bettingTable.getBetting(player.getName());

            int profit = result.calculateProfit(initialBetting);
            playersProfit.put(player.getName(), profit);
        }
        return playersProfit;
    }

    public boolean isDealerBlackJack() {
        return participants.getDealer().isBlackJack();
    }

    public boolean canPlayerReceive(Name name) {
        return participants.getPlayer(name).canReceive();
    }

    public boolean canDealerReceive() {
        return participants.getDealer().canReceive();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public Player getPlayer(Name name) {
        return participants.getPlayer(name);
    }

    public List<Name> getPlayerNames() {
        return participants.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
