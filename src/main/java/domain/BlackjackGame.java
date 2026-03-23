package domain;

import domain.card.Deck;
import domain.dto.PlayerCreateDto;
import domain.dto.Profit;
import domain.dto.TotalProfit;
import domain.participant.BetMoney;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    public static final int INITIAL_CARD_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;
    private int hitSequence = 0;

    private BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public static BlackjackGame createNewGame(List<PlayerCreateDto> playerCreateInfos) {
        Deck deck = Deck.createWithAllCards();
        List<Player> players = playerCreateInfos.stream()
                .map(player -> Player.of(deck.drawInitialCards(), player.name(), player.betMoney()))
                .toList();
        return new BlackjackGame(Players.from(players), Dealer.createReady(deck.drawInitialCards()), deck);
    }

    public boolean isPlayerHitAvailable() {
        return players.canHit(hitSequence);
    }

    public void hitPlayer(boolean hit) {
        if (hit) {
            players.hit(hitSequence, deck.draw());
            return;
        }
        players.stay(hitSequence++);
    }

    public Player currentHitPlayer() {
        return players.getPlayer(hitSequence);
    }

    public boolean isCurrentPlayerFinished() {
        return players.isFinished(hitSequence);
    }

    public void changeToNextPlayer() {
        hitSequence++;
    }

    public List<Boolean> hitStandDealer() {
        List<Boolean> history = new ArrayList<>();
        while (!dealer.isFinished()) {
            hitDealerByCondition(history);
        }
        history.add(false);
        return history;
    }

    private void hitDealerByCondition(List<Boolean> history) {
        if (dealer.isHittable()) {
            dealer.draw(deck.draw());
            history.add(true);
            return;
        }
        dealer.stay();
    }

    public TotalProfit getParticipantsProfits() {
        List<Profit> playerProfits = collectPlayerResults();
        BetMoney betMoney = calculateDealerResult(playerProfits);
        return new TotalProfit(betMoney, playerProfits);
    }

    private List<Profit> collectPlayerResults() {
        return players.getPlayers().stream()
                .map(player -> new Profit(player, player.getProfit(player.judge(dealer))))
                .toList();
    }

    private BetMoney calculateDealerResult(List<Profit> profits) {
        return profits.stream()
                .map(Profit::betMoney)
                .reduce(BetMoney.ZERO, BetMoney::subtract);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

}
