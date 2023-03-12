package domain;

import static domain.card.Face.SPADE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.bank.Money;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Face;
import domain.card.Letter;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.User;

public abstract class AbstractTestFixture {

    public static List<Card> createCards(String... cards) {
        return Arrays.stream(cards)
                .map(AbstractTestFixture::parse)
                .collect(Collectors.toList());
    }

    public static List<Card> createCards(List<String> cards) {
        return cards.stream()
                .map(AbstractTestFixture::parse)
                .collect(Collectors.toList());
    }

    private static Card parse(String card) {
        String[] cardInfo = card.split("-");
        if (cardInfo.length > 1) {
            return new Card(faceFrom(cardInfo[0]), letterFrom(cardInfo[1]));
        }
        return new Card(SPADE, letterFrom(cardInfo[0]));
    }

    public static Card createCard(Letter letter) {
        return new Card(SPADE, letter);
    }

    public static Letter letterFrom(String letter) {
        return Arrays.stream(Letter.values())
                .filter(letterEnum -> letterEnum.getLetter().equals(letter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 카드 글자입니다"));
    }

    public static Face faceFrom(String face) {
        return Arrays.stream(Face.values())
                .filter(faceEnum -> faceEnum.name().equals(face))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 카드 그림입니다"));
    }

    public static Participants createParticipantsFrom(Dealer dealer, User... users) {
        return new Participants(dealer, Arrays.asList(users));
    }

    public Game createGameFrom(Dealer dealer, User... users) {
        var participants = createParticipantsFrom(dealer, users);
        return new Game(participants, new Deck());
    }

    public static Money createMoney(int value) {
        if (value < 0) {
            return Money.of(-value).multiply(-1);
        }
        return Money.of(value);
    }
}
