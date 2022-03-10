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

    public Map<Player, Result> calculateResultBoard() {
        return gamers.compareResult(dealer.calculateResult());
    }

    public Map<Result, Integer> calculateDealerResultBoard() {
        Map<Result, Integer> enumMap = new EnumMap<>(Result.class);
        for (Entry<Player, Result> gamerResultEntry : calculateResultBoard().entrySet()) {
            Result dealerResult = convertToDealerResult(gamerResultEntry.getValue());
            enumMap.put(dealerResult, enumMap.getOrDefault(dealerResult, 0) + COUNT_UNIT);
        }
        return enumMap;
    }

    private static Result convertToDealerResult(final Result result) {
        if (result == Result.WIN) {
            return Result.LOSE;
        }
        if (result == Result.LOSE) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Gamer> getGamers() {
        return gamers.getGamers();
    }
}
