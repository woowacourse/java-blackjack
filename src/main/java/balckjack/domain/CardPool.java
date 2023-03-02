package balckjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardPool {

    public static final List<Card> cards = new ArrayList<>();

    private CardPool() {
    }

    static {

        final Pattern[] values = Pattern.values();

        for (Pattern pattern : values) {
            cards.add(new AceCard(pattern));
            for (int i = 2; i < 11; i++) {
                cards.add(new StandardCard(pattern, String.valueOf(i)));
            }
            cards.add(new CourtCard(pattern, "J"));
            cards.add(new CourtCard(pattern, "K"));
            cards.add(new CourtCard(pattern, "Q"));
        }
    }

    public static int getSize() {
        return cards.size();
    }

//    public static Card draw(CardPicker cardPicker) {
//        //패턴도 셔플
//        //그 안에 셔플
//
//        //하나의 리스트에 각 패턴마다 가져오고
//        //셔플
//        //첫번째 카드 get
//        // 0, 4
//        // 0, 13
//    }
}
