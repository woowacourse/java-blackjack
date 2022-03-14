package util;

import static model.card.CardFace.ACE;
import static model.card.CardFace.JACK;
import static model.card.CardFace.KING;
import static model.card.CardFace.QUEEN;
import static model.card.CardSuit.CLOVER;
import static model.card.CardSuit.DIAMOND;
import static model.card.CardSuit.HEART;
import static model.card.CardSuit.SPADE;

import java.util.EnumMap;
import java.util.Map;
import model.card.Card;
import model.card.CardFace;
import model.card.CardSuit;

public class CardConvertor {
    private static final Map<CardFace, String> faces = new EnumMap<>(CardFace.class);
    private static final Map<CardSuit, String> suits = new EnumMap<>(CardSuit.class);

    static {
        initSuits();
        initFaces();
    }

    private static void initFaces() {
        faces.put(ACE, "A");
        faces.put(KING, "K");
        faces.put(QUEEN, "Q");
        faces.put(JACK, "J");
        for (int i = 0; i < CardFace.values().length; i++) {
            CardFace face = CardFace.values()[i];
            faces.putIfAbsent(face, String.valueOf(face.getCardScore()));
        }
    }

    private static void initSuits() {
        suits.put(SPADE, "스페이드");
        suits.put(HEART, "하트");
        suits.put(DIAMOND, "다이아몬드");
        suits.put(CLOVER, "클로버");
    }

    public static String getCardString(Card card) {
        return faces.get(card.getCardFace()) + suits.get(card.getCardSuit());
    }
}
