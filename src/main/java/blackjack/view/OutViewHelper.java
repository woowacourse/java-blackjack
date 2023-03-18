package blackjack.view;

import blackjack.domain.player.User;

import java.util.stream.Collectors;

public class OutViewHelper {
    private OutViewHelper() {
    }

    public static String getUserCards(User user) {
        return user.getHand().stream()
                .map(card -> card.getCardNumber().getNumber() + card.getSymbol())
                .collect(Collectors.joining(", "));
    }
}
