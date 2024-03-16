package domain.blackjack;

import domain.card.Deck;
import java.util.List;
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

    public void playersDraw(Deck deck, PlayerDrawAfterCallBack playerDrawAfterCallBack,
                            DrawConfirmation drawConfirmation) {
        players.draw(deck, playerDrawAfterCallBack, drawConfirmation);
    }

    public boolean dealerTryDraw(Deck deck) {
        DrawResult drawResult = dealer.draw(deck);
        return drawResult.isSuccess();
    }

    public List<EarningMoney> calculatePlayersEarningMoney() {
        return players.calculatePlayersEarningMoney(dealer);
    }

    public EarningMoney calculateDealerEarningMoney() {
        final int reverseSigner = -1;
        int dealerRawEarningMoney = reverseSigner * players.calculatePlayersEarningMoney(dealer).stream()
                .mapToInt(EarningMoney::rawEarningMoney)
                .sum();
        return new EarningMoney(dealerRawEarningMoney);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
