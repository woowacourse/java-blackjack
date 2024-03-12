package view.viewer;

import domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;
import view.viewer.card.CardViewer;

public class HandViewer {
    public static String show(List<Card> cards) {
        return cards.stream()
                .map(CardViewer::show)
                .collect(Collectors.joining(", "));
    }
}
