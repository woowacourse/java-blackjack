package view.util;

import static model.CardFace.ACE;
import static model.CardFace.JACK;
import static model.CardFace.KING;
import static model.CardFace.QUEEN;
import static model.CardSuit.CLOVER;
import static model.CardSuit.DIAMOND;
import static model.CardSuit.HEART;
import static model.CardSuit.SPADE;

import java.util.EnumMap;
import java.util.Map;
import model.Card;
import model.CardFace;
import model.CardSuit;

public class CardConvertor {
    private static final Map<CardFace, String> faces = new EnumMap<>(CardFace.class);
    private static final Map<CardSuit, String> suits = new EnumMap<>(CardSuit.class);

    static {
        initSuits();
        initFaces();
    }

    private static void initSuits() {
        suits.put(SPADE, "스페이드");
        suits.put(HEART, "하트");
        suits.put(DIAMOND, "다이아몬드");
        suits.put(CLOVER, "클로버");
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

    private CardConvertor() {
    }

    public static String getCardString(Card card) {
        return faces.get(card.getCardFace()) + suits.get(card.getCardSuit());
    }
}
