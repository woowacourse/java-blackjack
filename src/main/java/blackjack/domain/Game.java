package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {

    private final CardFactory cardFactory;
    private final Dealer dealer;
    private final List<Player> players;

    public Game(CardFactory cardFactory, List<String> playerNames) {
        validateDuplicate(playerNames);
        this.cardFactory = cardFactory;
        this.dealer = new Dealer();
        this.players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicate(final List<String> playerNames) {
        final long countNoDuplicate = playerNames.stream()
                .distinct()
                .count();

        if (countNoDuplicate != playerNames.size()) {
            throw new IllegalArgumentException("이름은 중복을 허용하지 않습니다.");
        }
    }

    public void init() {
        dealer.prepareGame(cardFactory);
        players.forEach(player -> player.prepareGame(cardFactory));
    }

    public Optional<Player> findHitPlayer() {
        return players.stream()
                .filter(Player::isHit)
                .findFirst();
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
        return new RecordFactory(dealer.getScore(), players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Participant> getAllParticipant() {
        final List<Participant> list = new ArrayList<>();
        list.add(dealer);
        list.addAll(players);

        return list;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}