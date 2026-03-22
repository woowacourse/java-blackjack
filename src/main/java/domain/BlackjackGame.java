package domain;

import domain.card.Deck;
import domain.dto.PlayerCreateInfo;
import domain.dto.PlayerResult;
import domain.dto.Profit;
import domain.participant.BetMoney;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public class BlackjackGame {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    private BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public static BlackjackGame createNewGame(List<PlayerCreateInfo> playerCreateInfos) {
        Deck deck = Deck.createWithAllCards();
        List<Player> players = playerCreateInfos.stream()
                .map(player -> Player.of(deck.drawInitialCards(), player.name(), player.betMoney()))
                .toList();
        return new BlackjackGame(Players.from(players), Dealer.createReady(deck.drawInitialCards()), deck);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<PlayerResult> collectPlayerResults() {
        return players.getPlayers().stream()
                .map(player -> new PlayerResult(player, player.judge(dealer)))
                .toList();
    }

    public List<Profit> calculatePlayerProfits(List<PlayerResult> playerResults) {
        return playerResults.stream()
                .map(result -> new Profit(result.player(), result.player().getProfit(result.result())))
                .toList();
    }

    public BetMoney calculateDealerResult(List<Profit> profits) {
        return profits.stream()
                .map(Profit::betMoney)
                .reduce(BetMoney.ZERO, BetMoney::sub);
    }
}
