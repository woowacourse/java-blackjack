package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import model.deck.Deck;
import model.deck.DeckImpl;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;

public class BlackJack {
    private final Participants participants;
    private final Deck deck;

    private BlackJack(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJack from(Participants participants) {
        return new BlackJack(participants, DeckImpl.of(Suit.VALUES, Rank.VALUES));
    }

    public static BlackJack of(Participants participants, Deck deck) {
        return new BlackJack(participants, deck);
    }

    public Map<String, List<String>> dealOut() {
        Map<String, List<String>> dealOutResult = new LinkedHashMap<>();
        for (Participant participant : participants) {
            for (int i = 0; i < 2; i++) {
                Card card = drawNewCard();
                participant.receive(card);
            }

            dealOutResult.put(participant.getName(), participant.open());
        }

        return dealOutResult;
    }

    public void startPlayerTurn(Function<String, Boolean> askHit, Consumer<Participant> afterHit) {
        for (Player player : participants.getPlayers()) {
            while (player.canHit() && askHit.apply(player.getName())) {
                giveCardTo(player);
                afterHit.accept(player);
            }
        }
    }

    public void startDealerTurn(Consumer<Boolean> afterDealerTurn) {
        Dealer dealer = participants.getDealer();

        boolean draw = dealer.needDraw();
        if (draw) {
            giveCardTo(dealer);
        }

        afterDealerTurn.accept(draw);
    }

    public void giveCardTo(Participant participant) {
        participant.receive(drawNewCard());
    }

    private Card drawNewCard() {
        return deck.draw();
    }

    public Map<String, Integer> calculateDealerResult() {
        return participants.getDealer()
                .calculateStatistics(participants.getPlayers());
    }

    public Map<String, Boolean> calculatePlayerResult() {
        Map<String, Boolean> resultMap = new HashMap<>();

        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        for (Participant player : players) {
            if (dealer.calculateScore() > player.calculateScore() || player.isBust()) {
                resultMap.put(player.getName(), Boolean.FALSE);
                continue;
            }

            resultMap.put(player.getName(), Boolean.TRUE);
        }

        return resultMap;
    }
}
