package domain.blackjack;

import domain.card.Deck;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

public class BlackJackGame {
    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(List<String> playerNames, List<Integer> battingMoneys) {
        this.dealer = Dealer.of(HoldingCards.of());
        this.players = new Players(playerNames, battingMoneys);
    }

    public void initialDraw(Deck deck) {
        final int initialDrawCount = 2;
        IntStream.range(0, initialDrawCount).forEach(index -> {
            players.drawOnce(deck);
            dealer.draw(deck);
        });
    }

    public void playersDraw(Deck deck, Consumer<Player> doAfterEachPlayerDraw,
                            Function<String, Boolean> playerWantDraw) {
        players.draw(deck, doAfterEachPlayerDraw, playerWantDraw);
    }

    public boolean dealerTryDraw(Deck deck) {
        DrawResult drawResult = dealer.draw(deck);
        return drawResult.isSuccess();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public List<EarningMoney> calculatePlayersEarningMoney() {
        return players.calculatePlayersEarningMoney(dealer);
    }

    public EarningMoney calculateDealerEarningMoney() {
        int dealerRawEarningMoney = -players.calculatePlayersEarningMoney(dealer).stream()
                .mapToInt(EarningMoney::rawEarningMoney)
                .sum();
        return new EarningMoney(dealerRawEarningMoney);
    }
}
