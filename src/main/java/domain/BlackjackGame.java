package domain;

import domain.card.Deck;
import domain.dto.PlayerResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BlackjackGame {
    public static final int INITIAL_CARD_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    private BlackjackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public static BlackjackGame createNewGame(List<Player> players) {
        return new BlackjackGame(Players.from(players), Dealer.createReady(), Deck.createWithAllCards());
    }

    public void giveHand() {
        players.giveCardsToEachPlayers(deck, INITIAL_CARD_COUNT);
        dealer.addCards(deck.drawWithAmount(INITIAL_CARD_COUNT));
    }

    public void hitStandEachPlayers(Predicate<Player> decideHitStandFunc, Consumer<Player> printResultFunc) {
        players.hitStandEachPlayers(player -> hitStandPlayer(deck, decideHitStandFunc, printResultFunc, player));
    }

    private void hitStandPlayer(Deck deck, Predicate<Player> hitStandDecisionFunc,
                                Consumer<Player> printResultFunc, Player player) {
        while (!player.isBust() && hitStandDecisionFunc.test(player)) {
            player.addCard(deck.draw());
            printResultFunc.accept(player);
        }
    }

    public void hitStandDealer(Consumer<Boolean> printDecisionOutput) {
        while (dealer.isHittable(Score.DEALER_HIT_STAND_BOUNDARY)) {
            dealer.addCard(deck.draw());
            printDecisionOutput.accept(true);
        }
        printDecisionOutput.accept(false);
    }

    public List<PlayerResult> collectPlayerProfits() {
        return players.collectResults(dealer);
    }

    public BetMoney calculateDealerResult(List<PlayerResult> playerResults) {
        return playerResults.stream()
                .map(PlayerResult::betMoney)
                .reduce(BetMoney.ZERO, BetMoney::sub);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
