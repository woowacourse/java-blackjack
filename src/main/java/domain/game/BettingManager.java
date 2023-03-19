package domain.game;

import domain.participant.Dealer;
import domain.participant.ParticipantMoney;
import domain.participant.Player;

import java.util.function.BiConsumer;

public enum BettingManager {
    FIRST_TURN_DEALER_WIN(BettingManager::distributeDealerFirstTurnWin),
    FIRST_TURN_PLAYER_WIN(BettingManager::distributePlayerFirstTurnWin),
    IS_DEALER_WIN(BettingManager::distributeDealerWin),
    IS_PLAYER_WIN(BettingManager::distributePlayerWin);

    private final BiConsumer<Dealer, Player> referee;

    BettingManager(final BiConsumer<Dealer, Player> referee) {
        this.referee = referee;
    }

    private static void distributeDealerFirstTurnWin(final Dealer dealer, final Player player) {
        if (dealer.isBlackJack() && !player.isBlackJack()) {
            dealer.earnMoney(ParticipantMoney.create(player.getMoney()));
            player.loseMoney();
        }
    }

    private static void distributePlayerFirstTurnWin(final Dealer dealer, final Player player) {
        if (!dealer.isBlackJack() && player.isBlackJack()) {
            player.earnBonusMoney();
            dealer.loseMoney(ParticipantMoney.create(player.getMoney()));
        }
    }

    private static void distributeDealerWin(final Dealer dealer, final Player player) {
        if (dealer.isBlackJack()
                || dealer.isBust() && player.isBust()
                || !dealer.isBust() && dealer.calculateScore() > player.calculateScore()) {
            dealer.earnMoney(ParticipantMoney.create(player.getMoney()));
            player.loseMoney();
        }
    }

    private static void distributePlayerWin(final Dealer dealer, final Player player) {
        if (player.isBlackJack()
                || dealer.calculateScore() < player.calculateScore()) {
            player.earnMoney();
            dealer.loseMoney(ParticipantMoney.create(player.getMoney()));
        }
    }

    public BiConsumer<Dealer, Player> getReferee() {
        return referee;
    }
}

