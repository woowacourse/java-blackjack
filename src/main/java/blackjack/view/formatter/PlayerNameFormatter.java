package blackjack.view.formatter;

import blackjack.model.participant.Name;

public class PlayerNameFormatter {
    public static String formatWithCardComment(Name name) {
        return name.getValue() + " 카드: " ;
    }

    public static String format(Name name) {
        return name.getValue() + " : ";
    }
}
