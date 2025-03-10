package domain;

import static view.AnswerType.NO;

import java.util.ArrayList;
import java.util.List;
import view.AnswerType;

public class CardGiver {
    private static final int DEFAULT_CARD_GIVE_COUNT = 2;

    private final RandomGenerator<Card> randomGenerator;

    public CardGiver(RandomGenerator<Card> randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public Card giveOne() {
        return randomGenerator.generate();
    }

    public void giveDefaultTo(List<Participant> participants) {
        participants.forEach(participant -> {
            participant.addCards(giveDefault());
        });
    }

    public void giveAdditionalCard(Player player, AnswerType answerType) {
        if (answerType.isEqualTo(NO)) {
            return;
        }
        player.addCard(giveOne());
    }

    private Cards giveDefault() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < DEFAULT_CARD_GIVE_COUNT; i++) {
            cards.add(randomGenerator.generate());
        }
        return new Cards(cards);
    }
}
