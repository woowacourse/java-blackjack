package domain;

import domain.generator.CardNumberGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardSelector {

    private static final int INITIAL_PROFIT_MONEY = 0;

    private CardSelector() {
    }


    public static List<Player> giveCardsToPlayers(final List<Name> names, final CardNumberGenerator cardNumberGenerator,
        List<Integer> bettingMoneys) {
        List<Cards> givenCards = pickCards(names.size(), cardNumberGenerator);
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(givenCards.get(i + 1), names.get(i), new Betting(bettingMoneys.get(i), INITIAL_PROFIT_MONEY)));
        }
        return players;
    }

    public static List<Cards> pickCards(final int namesSize, final CardNumberGenerator generator) {
        int totalSizeOfPlayersAndDealer = namesSize + 1;
        return IntStream.range(0, totalSizeOfPlayersAndDealer)
            .mapToObj(i -> IntStream.range(0, 2)
                .mapToObj(j -> CardBox.get(generator.generateIndex()))
                .collect(Collectors.toList()))
            .map(Cards::new)
            .collect(Collectors.toList());
    }

    public static void playerDrawIfSelectToAddCard(final Player player,
        final int cardBoxIndex) {
        boolean didntSelected = true;
        while (didntSelected) {
            didntSelected = !player.selectToPickOtherCard(cardBoxIndex);
        }
    }

    public static void dealerPickCard(final Dealer dealer, final int cardBoxIndex) {
        boolean underStandard = true;
        while (underStandard) {
            underStandard = !dealer.selectToPickOtherCard(cardBoxIndex);
        }
    }
}
