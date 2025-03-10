package domain;

import static util.ExceptionConstants.*;
import static view.AnswerType.NO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import view.AnswerType;

public class CardGiver {

    private static final int DEFAULT_CARD_GIVE_COUNT = 2;
    private static final String NO_EXIST_CARD = "카드를 모두 나눠주었습니다.";

    private final RandomGenerator<Card> randomGenerator;
    private final GivenCards givenCards;

    public CardGiver(RandomGenerator<Card> randomGenerator, GivenCards givenCards) {
        this.randomGenerator = randomGenerator;
        this.givenCards = givenCards;
    }

    public Cards giveDefault() {
        givenCards.checkEnoughUnique();
        List<Card> cards = Stream.generate(randomGenerator::generate)
                .filter(givenCards::addUnique)
                .limit(DEFAULT_CARD_GIVE_COUNT)
                .collect(Collectors.toList());

        return new Cards(cards);
    }

    public Card giveOne() {
        return Stream.generate(randomGenerator::generate)
                .filter(givenCards::addUnique)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(ERROR_HEADER + NO_EXIST_CARD));
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
