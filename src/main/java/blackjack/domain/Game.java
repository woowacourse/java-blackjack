package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {

    private final CardFactory cardFactory;
    private final Dealer dealer;
    private final Players players;

    public Game(CardFactory cardFactory, List<String> playerNames) {
        this.cardFactory = cardFactory;
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
    }

    public void init() {
        dealer.prepareGame(cardFactory);
        players.prepareGame(cardFactory);
    }

    public Optional<Player> findHitPlayer() {
        return players.findHitPlayer();
    }

    public void progressPlayerTurn(Player player, Status status) {
        if (status == Status.HIT) {
            player.hit(cardFactory);
            return;
        }
        player.stay();
    }

    public Card openCard() {
        return dealer.openFirstCard();
    }

    public boolean canDrawCard() {
        return dealer.canDrawCard();
    }

    public void drawDealer() {
        dealer.hit(cardFactory);
    }

    public RecordFactory getRecordFactory() {
        return new RecordFactory(dealer.getScore(), players.getValue());
    }

    public List<Player> getPlayers() {
        return players.getValue();
    }

    public List<Participant> getAllParticipant() {
        final List<Participant> list = new ArrayList<>();
        list.add(dealer);
        list.addAll(players.getValue());

        return list;
    }

    public List<String> getPlayerNames() {
        return players.getNames();
    }
}