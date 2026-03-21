package domain.participant;

import domain.card.Card;
import exception.BlackjackException;
import java.util.List;
import java.util.function.Supplier;

public record Participants(Dealer dealer, Players players) {

    public static final String DIFFERENT_SIZE_PLAYER_BET_AMOUNT = "플레이어 목록과 베팅금액 목록의 크기가 다릅니다.";

    public static Participants of(List<PlayerName> playerNames, List<BetAmount> betAmounts) {
        validateSize(playerNames, betAmounts);
        Dealer dealer = new Dealer();
        Players players = new Players(playerNames, betAmounts);
        return new Participants(dealer, players);
    }

    private static void validateSize(List<PlayerName> playerNames, List<BetAmount> betAmounts) {
        if (playerNames.size() != betAmounts.size()) {
            throw new BlackjackException(DIFFERENT_SIZE_PLAYER_BET_AMOUNT);
        }
    }

    public boolean dealerShouldHit() {
        return dealer.shouldHit();
    }

    public void drawInitialCards(Supplier<Card> cardSupplier) {
        dealer.drawInitialCards(cardSupplier);
        players.drawInitialCards(cardSupplier);
    }

    public void drawCardsByDealer(Supplier<Card> cardSupplier) {
        dealer.drawCard(cardSupplier.get());
    }

    public Player drawCardsByPlayer(String name, Supplier<Card> cardSupplier) {
        players.addCard(name, cardSupplier.get());
        return players.getPlayer(name);
    }

    public boolean isPlayerBust(String name) {
        return players.isPlayerBust(name);
    }
}
