package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardSelector {

    private CardSelector() {
    }

    public static List<Cards> pickCards(final List<Name> names, final CardNumberGenerator generator,
        final CardBox cardBox) {
        int totalSizeOfPlayersAndDealer = names.size() + 1;
        return IntStream.range(0, totalSizeOfPlayersAndDealer)
            .mapToObj(i -> IntStream.range(0, 2)
                .mapToObj(j -> cardBox.get(generator.generateIndex()))
                .collect(Collectors.toList()))
            .map(Cards::new)
            .collect(Collectors.toList());
    }

    public static List<Player> giveCardsToPlayers(final List<Name> names, final List<Cards> boxedCards, final Dealer dealer) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), boxedCards.get(i + 1)));
        }
        return players;
    }
    public static void playerDrawIfSelectToAddCard(final List<Player> players, final int index,
        final CardBox cardBox,
        final int cardBoxIndex) {
        boolean didntSelected = true;
        while (didntSelected) {
            didntSelected = !players.get(index).selectToPickOtherCard(cardBox, cardBoxIndex);
        }
    }

    public static void dealerPickCard(final Dealer dealer, final int cardBoxIndex, final CardBox cardBox) {
        boolean underStandard = true;
        while (underStandard) {
            underStandard = !dealer.selectToPickOtherCard(cardBox, cardBoxIndex);
        }
    }
}
