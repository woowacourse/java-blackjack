package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackJackGame {

    private static final int COUNT_UNIT = 1;

    private final Player dealer;
    private final Gamers gamers;

    public BlackJackGame(final Player dealer, final Gamers gamers) {
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public void handOutStartingCards(final Deck deck) {
        dealStartingCards(dealer, deck);
        for (Gamer gamer : gamers.getGamers()) {
            dealStartingCards(gamer, deck);
        }
    }

    private void dealStartingCards(final Player player, final Deck deck) {
        for (int i = 0; i < Card.START_CARD_COUNT; i++) {
            player.receiveCard(deck.draw());
        }
    }

    public Map<Player, CompareResult> calculateResultBoard() {
        return gamers.compareResult(dealer.calculateResult());
    }

    public Map<CompareResult, Integer> calculateDealerResultBoard() {
        Map<CompareResult, Integer> enumMap = new EnumMap<>(CompareResult.class);
        for (Entry<Player, CompareResult> gamerResultEntry : calculateResultBoard().entrySet()) {
            CompareResult dealerCompareResult = convertToDealerResult(gamerResultEntry.getValue());
            enumMap.put(dealerCompareResult, enumMap.getOrDefault(dealerCompareResult, 0) + COUNT_UNIT);
        }
        return enumMap;
    }

    private static CompareResult convertToDealerResult(final CompareResult compareResult) {
        if (compareResult == CompareResult.WIN) {
            return CompareResult.LOSE;
        }
        if (compareResult == CompareResult.LOSE) {
            return CompareResult.WIN;
        }
        return CompareResult.DRAW;
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Gamer> getGamers() {
        return gamers.getGamers();
    }
}
