package utils;

import domain.card.Card;
import domain.card.Face;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CardConvertor {
    private CardConvertor() {
    }

    public static List<String> convertToString(final List<Card> cards) {
        final List<String> result = new ArrayList<>();
        for (final Card card : cards) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(FaceMapper.get(card.getFace()))
                    .append(SuitMapper.get(card.getSuit()));
            result.add(stringBuilder.toString());
        }
        return result;
    }

    private static class FaceMapper {
        private static final Map<Face, String> FACE_MAPPER = new EnumMap<>(Face.class);

        static {
            FACE_MAPPER.put(Face.ACE, "A");
            FACE_MAPPER.put(Face.JACK, "10");
            FACE_MAPPER.put(Face.KING, "10");
            FACE_MAPPER.put(Face.QUEEN, "10");
            for (Face value : Face.values()) {
                FACE_MAPPER.putIfAbsent(value, String.valueOf(value.getScore()));
            }
        }

        static String get(final Face face) {
            return FACE_MAPPER.get(face);
        }

        private FaceMapper() {
        }
    }

    private static class SuitMapper {
        private static final Map<Suit, String> SUIT_MAPPER = new EnumMap<>(Suit.class);

        static {
            SUIT_MAPPER.put(Suit.CLUB, "클로버");
            SUIT_MAPPER.put(Suit.HEART, "하트");
            SUIT_MAPPER.put(Suit.DIAMOND, "다이아몬드");
            SUIT_MAPPER.put(Suit.SPADE, "스페이드");
        }

        static String get(final Suit suit) {
            return SUIT_MAPPER.get(suit);
        }

        private SuitMapper() {
        }
    }
}
