package domain.model;

import java.util.List;
import java.util.stream.IntStream;

public class CardDistributor {

    private static final int START_DEAL_COUNT = 2;

    private final CardGenerator cardGenerator;

    public CardDistributor(final CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void dealInitialCards(final Dealer dealer, final List<Player> players) {
        dealCard(List.of(dealer));
        dealCard(players);
    }

    private void dealCard(final List<Player> players) {
        IntStream.range(0, START_DEAL_COUNT)
            .mapToObj(i -> players)
            .forEach(this::giveCard);
    }

    public void giveCard(final List<Player> players) {
        players.forEach(this::giveCard);
    }

    public void giveCard(final Player player) {
        player.addCard(cardGenerator.generate());
    }

    public boolean canGiveCard(final Player player) {
        return player.canReceiveCard();
    }
}
