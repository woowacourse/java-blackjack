package domain;

import static view.AnswerType.NO;

import java.util.ArrayList;
import java.util.List;
import view.AnswerType;

public class CardGiver {
    private static final int DEFAULT_CARD_GIVE_COUNT = 2;

    private final RandomGenerator<Card> randomGenerator;
    private final GivenCards givenCards;

    public CardGiver(RandomGenerator<Card> randomGenerator, GivenCards givenCards) {
        this.randomGenerator = randomGenerator;
        this.givenCards = givenCards;
    }

    public Cards giveDefault() {
        givenCards.checkEnoughUnique();
        List<Card> cards = new ArrayList<>();
        while (cards.size() < DEFAULT_CARD_GIVE_COUNT) {
            Card randomCard = randomGenerator.generate();
            if (!givenCards.contains(randomCard)) {
                cards.add(randomCard);
                givenCards.addUnique(randomCard);
            }
        }
        return new Cards(cards);
    }

    public Card giveOne() {
        givenCards.checkEnoughUnique();
        Card card = null;
        while (card == null) {
            Card randomCard = randomGenerator.generate();
            if (!givenCards.contains(randomCard)) {
                givenCards.addUnique(randomCard);
                card = randomCard;
            }
        }
        return card;
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
}
