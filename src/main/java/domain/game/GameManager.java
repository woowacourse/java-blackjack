package domain.game;

import domain.CardShuffler;
import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.ParticipantMoney;
import domain.participant.Participants;
import domain.participant.Player;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static domain.game.BettingManager.FIRST_TURN_DEALER_WIN;
import static domain.game.BettingManager.FIRST_TURN_PLAYER_WIN;
import static domain.game.BettingManager.IS_DEALER_WIN;
import static domain.game.BettingManager.IS_PLAYER_WIN;

public final class GameManager {
    private final Deck deck;
    private final Participants participants;

    private GameManager(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static GameManager create(final CardShuffler cardShuffler, final Participants participants) {
        return new GameManager(Deck.create(cardShuffler), participants);
    }

    public void dealingFirstTurn() {
        participants.getDealer().addCard(deck.draw(), deck.draw());
        participants.getPlayers().forEach(player -> player.addCard(deck.draw(), deck.draw()));
    }

    public void judgeFirstBettingResult() {
        final Dealer dealer = participants.getDealer();
        participants.getPlayers()
                .forEach(player -> {
                    FIRST_TURN_DEALER_WIN.getReferee().accept(dealer, player);
                    FIRST_TURN_PLAYER_WIN.getReferee().accept(dealer, player);
                });
    }

    public Card getCard() {
        return deck.draw();
    }

    public void calculateProfit(final Map<Player, ParticipantMoney> bettingInfo) {
        final Dealer dealer = participants.getDealer();
        if (dealer.isBust()) {
            resetBettingMoney(dealer, bettingInfo);
            return;
        }
        calculatePlayerMoney(dealer, bettingInfo);
    }

    public void canDealerDrawCard(final Consumer<String> consumer) {
        final Dealer dealer = participants.getDealer();
        while (dealer.canDrawCard()) {
            dealer.addCard(getCard());
            consumer.accept(dealer.getName());
        }
    }

    private void calculatePlayerMoney(final Dealer dealer,
                                      final Map<Player, ParticipantMoney> bettingINfo) {
        participants.getPlayers()
                .stream()
                .filter(Predicate.not(Player::isBust))
                .forEach(player -> judgeBettingResult(dealer, player, bettingINfo));
    }

    private void judgeBettingResult(final Dealer dealer, final Player player,
                                    final Map<Player, ParticipantMoney> initMoneyInfo) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            resetBettingMoney(dealer, initMoneyInfo);
            return;
        }
        IS_DEALER_WIN.getReferee().accept(dealer, player);
        IS_PLAYER_WIN.getReferee().accept(dealer, player);
    }

    private void resetBettingMoney(final Dealer dealer, final Map<Player, ParticipantMoney> bettingInfo) {
        participants.getPlayers().forEach(player -> {
            dealer.loseMoney(bettingInfo.get(player));
            player.resetMoney(bettingInfo.get(player));
        });
    }

    public Participants getParticipants() {
        return participants;
    }
}
