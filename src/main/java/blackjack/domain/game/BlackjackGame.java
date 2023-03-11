package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Participants participants;

    public BlackjackGame(Participants participants, CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public BlackjackGame(Participants participants) {
        this(participants, new CardDeck());
    }

    public void dealOutCard() {
        dealOutPlayerCard();
        dealOutDealerCard();
    }

    private void dealOutDealerCard() {
        Dealer dealer = participants.getDealer();
        dealer.addCards(pickTwice());
    }

    private void dealOutPlayerCard() {
        for (Player player : participants.getPlayers()) {
            player.addCards(pickTwice());
        }
    }

    private List<Card> pickTwice() {
        List<Card> pick = new ArrayList<>();
        pick.add(cardDeck.pick());
        pick.add(cardDeck.pick());
        return pick;
    }

    public BlackjackResult getResult() {
        Map<Player, GameResult> result = participants.getPlayers().stream()
                .collect(Collectors.toMap(player -> player,
                        player -> of(player, participants.getDealer()),
                        (o1, o2) -> o1,
                        LinkedHashMap::new));
        return new BlackjackResult(result);
    }

    public void drawCard(Participant participant) {
        participant.addCard(cardDeck.pick());
    }

    public Participants getParticipants() {
        return participants;
    }

    private GameResult of(Player player, Dealer dealer) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return GameResult.TIE;
        }
        if (player.getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }
}
