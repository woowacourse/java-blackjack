package domain.service;

import domain.model.Player;
import java.util.List;
import java.util.stream.IntStream;

public class CardDistributor {

    private static final int INIT_DEAL_COUNT = 2;
    private static final String CANNOT_GIVE_CARD_ERROR_MESSAGE = "더 이상 카드를 받을 수 없습니다.";
    private static final String ALREADY_CARD_EXIST_ERROR_MESSAGE = "이미 카드를 가지고 있습니다.";
    private final CardGenerator cardGenerator;

    public CardDistributor(final CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void giveCard(final Player player) {
        checkBust(player);
        player.addCard(cardGenerator.generate());
    }

    private static void checkBust(final Player player) {
        if (player.isBust()) {
            throw new IllegalArgumentException(CANNOT_GIVE_CARD_ERROR_MESSAGE);
        }
    }

    public void giveInitCards(final Player player) {
        checkCardEmpty(player);
        IntStream.range(0, INIT_DEAL_COUNT)
            .forEach(count -> player.addCard(cardGenerator.generate()));
    }

    public void giveInitCards(final List<Player> players) {
        players.forEach(this::giveInitCards);
    }

    private void checkCardEmpty(final Player player) {
        if (player.cardsIsNotEmpty()) {
            throw new IllegalArgumentException(ALREADY_CARD_EXIST_ERROR_MESSAGE);
        }
    }
}
