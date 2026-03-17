package model;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import model.cardpicker.CardPicker;
import model.participant.Participant;

public final class BlackJack {
    private static final int STARTING_CARDS = 2;

    private final Participants participants;
    private final Set<Card> pickedCards;
    private final CardPicker cardPicker;

    private BlackJack(Participants participants, CardPicker cardPicker) {
        this.participants = participants;
        this.cardPicker = cardPicker;
        this.pickedCards = new HashSet<>();
    }

    public static BlackJack from(Participants participants, CardPicker cardPicker) {
        return new BlackJack(participants, cardPicker);
    }

    public void dealOut() {
        dealerDealout();

        for (Participant participant : participants) {
            for (int i = 0; i < STARTING_CARDS; i++) {
                Card pick = cardPicker.pick();
                pick = pickUniqueCard(pick);
                pickedCards.add(pick);
                participant.draw(pick);
            }
        }
    }

    public Map<String, Integer> calculateRevenue() {
        Map<String, Integer> result = new LinkedHashMap<>();
        Participant dealer = participants.getDealer();
        int dealerRevenue = 0;
        result.put(dealer.getName(), dealerRevenue);

        for (Participant player : participants.getPlayers()) {
            int revenue = player.calculateRevenue(dealer.calculateScore(), dealer.isBust(), dealer.isBlackJack());

            result.put(player.getName(), revenue);
            dealerRevenue -= revenue;
        }

        result.merge(dealer.getName(), dealerRevenue, Integer::sum);
        return result;
    }

    private void dealerDealout() {
        Participant dealer = participants.getDealer();
        for (int i = 0; i < STARTING_CARDS; i++) {
            Card pick = cardPicker.pick();
            pick = pickUniqueCard(pick);
            pickedCards.add(pick);
            dealer.draw(pick);
        }
    }

    private Card pickUniqueCard(Card pick) {
        while (pickedCards.contains(pick)) {
            pick = cardPicker.pick();
        }
        return pick;
    }
}
