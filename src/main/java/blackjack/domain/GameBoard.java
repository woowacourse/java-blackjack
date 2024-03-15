package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {

    public static final int INITIAL_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public GameBoard(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void drawInitialDealerHand() {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.draw();
        }
    }

    public void drawInitialPlayersHand() {
        players.drawInitialHand(dealer);
    }

    //TODO: get은 필드에 대한 getter 느낌을 주기 때문에 메서드 명 더 적절하게 변경하기
    public Card getDealerFirstCard() {
        return dealer.openFirstCard();
    }

    public void hitDealer() {
        dealer.draw();
    }

    public void hitPlayer(final Player player) {
        player.draw(dealer.drawPlayerCard());
    }

    public boolean canDealerHit() {
        return dealer.canDraw();
    }

    public boolean canPlayerHit(final Player player) {
        return player.canDraw();
    }

    //TODO: get은 필드에 대한 getter 느낌을 주기 때문에 메서드 명 더 적절하게 변경하기
    public Money getDealerProfit() {
        final List<Money> playerProfits = new ArrayList<>(getPlayerProfits().values());
        return Outcome.calculateDealerProfit(playerProfits);
    }

    //TODO: get은 필드에 대한 getter 느낌을 주기 때문에 메서드 명 더 적절하게 변경하기
    public Map<Name, Money> getPlayerProfits() {
        final Map<Name, Money> playerProfits = new LinkedHashMap<>();
        //TODO: 일급 컬렉션인 Players에게 직접 시키는 방향으로 변경하기
        for (final Player player : players.getPlayers()) {
            playerProfits.put(player.getName(), Outcome.calculatePlayerProfit(dealer, player));
        }
        return Collections.unmodifiableMap(playerProfits);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
