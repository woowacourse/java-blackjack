package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Blackjack {
    private static final int INITIAL_CARD_NUMBER = 2;
    private final Dealer dealer;
    private Players players;

    public Blackjack(List<String> playerNames) {
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
    }

    public List<Card> distributeInitialCards(NumberGenerator numberGenerator) {
        return IntStream.range(0, INITIAL_CARD_NUMBER)
                .boxed()
                .map(index -> distributeCard(numberGenerator))
                .collect(Collectors.toList());
    }

    public void distributeInitialCardsToDealer(NumberGenerator numberGenerator) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; ++i) {
            distributeCardToDealer(numberGenerator);
        }
    }

    public Card distributeCard(NumberGenerator numberGenerator) {
        return dealer.handOutCard(numberGenerator);
    }

    public int distributeCardToDealerUntilHit(NumberGenerator numberGenerator) {
        int count = 0;
        while (dealer.isHit()) {
            distributeCardToDealer(numberGenerator);
            ++count;
        }
        return count;
    }

    private void distributeCardToDealer(NumberGenerator numberGenerator) {
        dealer.addCard(dealer.handOutCard(numberGenerator));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Map<String, Result> results(List<Player> players) {
        return zipToMap(players.stream()
                        .map(Player::getName)
                        .collect(Collectors.toList())
                , players.stream()
                        .map(dealer::isWin)
                        .collect(Collectors.toList()));
    }

    private <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }
}
