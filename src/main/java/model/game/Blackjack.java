package model.game;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import model.card.Card;
import model.card.Cards;
import model.card.Deck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Participants;
import model.participant.Player;

public class Blackjack {
    public static final int DEALOUT_DRAW_COUNT = 2;
    public static final int BLACKJACK_SCORE = 21;

    private final Participants participants;
    private final Deck deck;

    private Blackjack(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static Blackjack of(Participants participants, Deck deck) {
        return new Blackjack(participants, deck);
    }

    public Map<String, Cards> dealout() {
        Map<String, Cards> dealOutResult = new LinkedHashMap<>();

        for (Participant participant : participants.asList()) {
            for (int i = 0; i < DEALOUT_DRAW_COUNT; i++) {
                Card card = drawNewCard();
                participant.receive(card);
            }

            dealOutResult.put(participant.getName(), Cards.from(participant.open()));
        }

        return dealOutResult;
    }

    public void giveCardTo(Participant participant) {
        participant.receive(drawNewCard());
    }

    private Card drawNewCard() {
        return deck.draw();
    }

    public Map<String, BigDecimal> calculateFinalResult() {
        Dealer dealer = participants.getDealer();

        BigDecimal dealerProfit = new BigDecimal(0);
        LinkedHashMap<String, BigDecimal> profitByName = new LinkedHashMap<>();

        for (Player player : participants.getPlayers()) {
            GameResult gameResult = GameResult.calculateResult(player, dealer);

            BigDecimal playerProfit = gameResult.getProfitFrom(player.getBettingAmount());
            profitByName.put(player.getName(), playerProfit);
            dealerProfit = dealerProfit.subtract(playerProfit);
        }

        profitByName.putFirst(dealer.getName(), dealerProfit);

        return profitByName;
    }
}
