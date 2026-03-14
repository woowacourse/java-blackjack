package domain;

import domain.card.Deck;
import domain.dto.TotalResult;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackjackGame {
    public static final int INITIAL_CARD_COUNT = 2;
    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    public BlackjackGame(List<String> players) {
        validate(players);
        this.players = Players.from(players);
        this.dealer = Dealer.createReady();
        this.deck = Deck.createWithAllCards();
    }

    private void validate(List<String> players) {
        if (players == null) {
            throw new IllegalArgumentException(CommonExceptionMessage.FIELD_CAN_NOT_BE_NULL.getMessage());
        }
    }

    public void setBetMoney(Function<Player, Long> getBetMoneyFunc) {
        players.setBetMoneyEachPlayers(getBetMoneyFunc);
    }

    public void giveHand() {
        players.giveCardsToEachPlayers(deck, INITIAL_CARD_COUNT);
        dealer.addCards(deck.drawWithAmount(INITIAL_CARD_COUNT));
    }

    public void hitStandEachPlayers(Function<Player, Boolean> decideHitStandFunc, Consumer<Player> printResultFunc) {
        players.hitStandEachPlayers(player -> hitStandPlayer(deck, decideHitStandFunc, printResultFunc, player));
    }

    private void hitStandPlayer(Deck deck, Function<Player, Boolean> hitStandDecisionFunc,
                                Consumer<Player> printResultFunc, Player player) {
        while (!player.isBust() && hitStandDecisionFunc.apply(player)) {
            player.addCard(deck.draw());
            printResultFunc.accept(player);
        }
        printResultFunc.accept(player);
    }

    public void hitStandDealer(Consumer<Boolean> printDecisionOutput) {
        while (dealer.isHittable(Score.DEALER_HIT_STAND_BOUNDARY)) {
            dealer.addCard(deck.draw());
            printDecisionOutput.accept(true);
        }
        printDecisionOutput.accept(false);
    }

    public TotalResult getResult() {
        return players.getResults(dealer);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
