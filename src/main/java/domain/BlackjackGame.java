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
    public static final int DEFAULT_HAND_NUMBER = 2;
    public static final Score DEALER_HIT_STAND_BOUNDARY = new Score(16);
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
        players.giveCardsToEachPlayers(deck, DEFAULT_HAND_NUMBER);
        dealer.addCards(deck.drawWithAmount(DEFAULT_HAND_NUMBER));
    }


    public void playerHitStand(Function<Player, Boolean> decideHitStandFunc, Consumer<Player> printResultFunc) {
        players.hitStandEachPlayers(decideHitStandFunc, deck, printResultFunc);
    }

    public void dealerHitStand(Consumer<Boolean> printDecisionOutput) {
        while (true) {
            boolean isHittable = dealer.isHittable(DEALER_HIT_STAND_BOUNDARY);
            if (!isHittable) {
                printDecisionOutput.accept(isHittable);
                break;
            }
            dealer.addCard(deck.draw());
            printDecisionOutput.accept(isHittable);
        }
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
