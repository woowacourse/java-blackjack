package domain;

import static domain.Face.SPADE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTestFixture {

    List<Card> createCards(String... letters) {
        return Arrays.stream(letters)
                .map(this::letterFrom)
                .map(this::createCard)
                .collect(Collectors.toList());
    }

    Card createCard(Letter letter) {
        return new Card(SPADE, letter);
    }

    Letter letterFrom(String letter) {
        return Arrays.stream(Letter.values())
                .filter(letterEnum -> letterEnum.getLetter().equals(letter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 카드 글자입니다"));
    }

    Participants createParticipantsFrom(Dealer dealer, User... users) {
        return new Participants(dealer, Arrays.asList(users));
    }
}
