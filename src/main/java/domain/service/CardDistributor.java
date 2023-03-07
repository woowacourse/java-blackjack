package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import java.util.List;
import java.util.stream.IntStream;

public class CardDistributor {

    private static final int INIT_DEAL_COUNT = 2;
    private static final String CANNOT_GIVE_CARD_ERROR_MESSAGE = "더 이상 카드를 받을 수 없습니다.";
    private final CardGenerator cardGenerator;

    public CardDistributor(final CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void giveCard(final Player player) {
        if (player.isBust()) {
            throw new IllegalArgumentException(CANNOT_GIVE_CARD_ERROR_MESSAGE);
        }
        player.addCard(cardGenerator.generate());
    }

    public void giveInitCards(final Dealer dealer, final List<Player> players) {
        IntStream.range(0, INIT_DEAL_COUNT)
            .forEach(i -> {
                giveCard(dealer);
                players.forEach(this::giveCard);
            });
    }
}
