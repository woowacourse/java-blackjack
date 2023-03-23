package blackjack.domain;

import blackjack.domain.betting.Betting;
import blackjack.domain.betting.BettingManager;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;
    private final BettingManager bettingManager;

    public BlackJackGame(final Participants participants, final Deck deck, final BettingManager bettingManager,
                         final int drawCount) {
        this.participants = participants;
        this.deck = deck;
        this.bettingManager = bettingManager;
        initGame(drawCount);
    }

    private void initGame(final int drawCount) {
        deck.shuffle();
        participants.drawCard(deck, drawCount);
        checkDealerBlackJack();
    }

    private void checkDealerBlackJack() {
        if (dealer().isBlackJack()) {
            players().forEach(Player::changeDrawable);
        }
    }

    public void drawOrNot(final boolean wantMoreDraw, final Participant participant) {
        if (!wantMoreDraw) {
            participant.changeDrawable();
            return;
        }
        participant.drawCard(deck.draw());
    }

    public Player findDrawablePlayer() {
        return players().stream()
                .filter(Player::isDrawable)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("카드를 받을 수 있는 플레이어가 존재하지 않습니다."));
    }

    public boolean existDrawablePlayer() {
        return players().stream()
                .anyMatch(Player::isDrawable);
    }

    public int getDealerProfit() {
        int dealerProfit = 0;
        for (final Player player : players()) {
            final Betting playerBetting = bettingManager.findBettingByName(player.getName());
            final Result playerResult = getResult(player);
            dealerProfit -= playerResult.calculateProfit(playerBetting.getAmount());
        }
        return dealerProfit;
    }

    private Result getResult(final Player player) {
        if (player.isBlackJack()) {
            return Result.BLACKJACK;
        }
        return dealer().compareScoreTo(player).reverseResult();
    }

    public Map<Player, Integer> getPlayerProfits() {
        Map<Player, Integer> playerProfits = new HashMap<>();
        for (final Player player : players()) {
            final Betting playerBetting = bettingManager.findBettingByName(player.getName());
            final Result playerResult = getResult(player);
            playerProfits.put(player, playerResult.calculateProfit(playerBetting.getAmount()));
        }
        return playerProfits;
    }

    public boolean isDealerDrawable() {
        return dealer().isDrawable();
    }

    public Participants participants() {
        return participants;
    }

    public Dealer dealer() {
        return participants.getDealer();
    }

    public List<Player> players() {
        return participants.getPlayers();
    }
}
