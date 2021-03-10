package blackjack.domain.gametable;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.utils.CardDeck;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameTable implements Playable {
    private final Participant dealer;
    private final Players players;
    private final CardDeck cardDeck;

    public GameTable(Participant dealer, CardDeck cardDeck){
        this(dealer, new Players(Collections.emptyList()), cardDeck);
    }
    public GameTable(Player player, CardDeck cardDeck) {
        this(new Dealer(new Cards(Collections.emptyList())), new Players(Arrays.asList(player)),
            cardDeck);
    }

    public GameTable(Participant dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
        initCards();
    }

    private void initCards() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getUnmodifiableList());

        for (Participant participant : participants) {
            participant.takeCard(cardDeck.pop());
            participant.takeCard(cardDeck.pop());
        }

    }

    @Override
    public void giveCard(Participant participant) {
        validateRestCards();
        if (!participant.isNotAbleToTake()) {
            participant.takeCard(cardDeck.pop());
        }
    }

    private void validateRestCards() {
        if (cardDeck.isEmpty()) {
            throw new IllegalArgumentException("카드가 소진되었습니다.");
        }
    }

}
