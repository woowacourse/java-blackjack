package domain.service;

import domain.model.Participant;
import domain.model.Players;
import java.util.stream.IntStream;

public class CardDistributor {

    private static final int INIT_DEAL_COUNT = 2;
    private static final String CANNOT_GIVE_CARD_ERROR_MESSAGE = "더 이상 카드를 받을 수 없습니다.";
    private static final String ALREADY_CARD_EXIST_ERROR_MESSAGE = "이미 카드를 가지고 있습니다.";
    private final CardGenerator cardGenerator;

    public CardDistributor(final CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void giveCard(final Participant participant) {
        checkCanReceiveCard(participant);
        participant.addCard(cardGenerator.generate());
    }

    private static void checkCanReceiveCard(final Participant participant) {
        if (participant.canNotReceiveCard()) {
            throw new IllegalArgumentException(CANNOT_GIVE_CARD_ERROR_MESSAGE);
        }
    }

    public void giveInitCards(final Participant participant) {
        checkCardEmpty(participant);
        IntStream.range(0, INIT_DEAL_COUNT)
            .forEach(count -> participant.addCard(cardGenerator.generate()));
    }

    public void giveInitCards(final Players players) {
        checkCardEmpty(players);
        IntStream.range(0, INIT_DEAL_COUNT)
            .forEach(count -> players.addAll(cardGenerator.generate(players.count())));
    }

    private void checkCardEmpty(final Players players) {
        IntStream.range(0, players.count())
            .mapToObj(players::get)
            .forEach(this::checkCardEmpty);
    }

    private void checkCardEmpty(final Participant participant) {
        if (participant.cardsIsNotEmpty()) {
            throw new IllegalArgumentException(ALREADY_CARD_EXIST_ERROR_MESSAGE);
        }
    }
}
