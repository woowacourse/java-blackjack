package controller.mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import controller.dto.CardDto;
import domain.card.Card;
import domain.card.Face;
import domain.card.Letter;

public class CardDtoMapper {

    private static final Map<Letter, String> LETTER_MAP = Map.ofEntries(
            Map.entry(Letter.ACE, "A"),
            Map.entry(Letter.TWO, "2"),
            Map.entry(Letter.THREE, "3"),
            Map.entry(Letter.FOUR, "4"),
            Map.entry(Letter.FIVE, "5"),
            Map.entry(Letter.SIX, "6"),
            Map.entry(Letter.SEVEN, "7"),
            Map.entry(Letter.EIGHT, "8"),
            Map.entry(Letter.NINE, "9"),
            Map.entry(Letter.TEN, "10"),
            Map.entry(Letter.KING, "K"),
            Map.entry(Letter.QUEEN, "Q"),
            Map.entry(Letter.JACK, "J")
    );

    private static final Map<Face, String> FACE_MAP = Map.ofEntries(
            Map.entry(Face.DIAMOND, "다이아몬드"),
            Map.entry(Face.CLOVER, "클로버"),
            Map.entry(Face.HEART, "하트"),
            Map.entry(Face.SPADE, "스페이드")
    );

    public static List<CardDto> map(Set<Card> cards) {
        return cards
                .stream()
                .map(CardDtoMapper::map)
                .collect(Collectors.toList());
    }

    public static List<CardDto> mapFirstOf(Set<Card> cards) {
        return cards
                .stream()
                .limit(1)
                .map(CardDtoMapper::map)
                .collect(Collectors.toList());
    }


    public static CardDto map(Card card) {
        return new CardDto(map(card.getLetter()), map(card.getFace()));
    }

    private static String map(Letter letter) {
        String predefinedLetter = LETTER_MAP.get(letter);
        if (Objects.isNull(predefinedLetter)) {
            throw new IllegalArgumentException("알 수 없는 카드 문자가 있습니다");
        }
        return predefinedLetter;
    }

    private static String map(Face face) {
        String predefinedFace = FACE_MAP.get(face);
        if (Objects.isNull(predefinedFace)) {
            throw new IllegalArgumentException("알 수 없는 카드 그림이 있습니다");
        }
        return predefinedFace;
    }
}
