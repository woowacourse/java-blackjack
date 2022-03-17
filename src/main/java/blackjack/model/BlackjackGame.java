package blackjack.model;

import blackjack.dto.ParticipantDto;
import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Players;
import blackjack.model.result.BlackjackGameResult;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BlackjackGame {
    private final Players players;
    private final Participant dealer;
    private final CardDeck cardDeck;

    public BlackjackGame(final List<String> names) {
        this.players = Players.from(names);
        this.dealer = new Dealer();
        this.cardDeck = new CardDeck();
    }

    private BlackjackGame(final Players players, final Participant dealer) {
        this.players = players;
        this.dealer = dealer;
        this.cardDeck = null;
    }

    public BlackjackGame start() {
        Players copyOfPlayers = players.drawBy(cardDeck);
        return new BlackjackGame(copyOfPlayers, drawToDealer());
    }

    private Participant drawToDealer() {
        Participant newDealer = null;
        for (int i = 0; i < Cards.START_CARD_COUNT; i++) {
            newDealer = dealer.receive(cardDeck.draw());
        }
        return newDealer;
    }

    public void performEachTurn(Predicate<String> predicate, Consumer<Participant> consumer) {
        for (Participant player : players.getPlayerGroup()) {
            hitOrStayToPlayer(player, predicate, consumer);
        }
        hitOrStayToDealer(consumer);
    }

    private void hitOrStayToPlayer(Participant player, Predicate<String> predicate, Consumer<Participant> consumer) {
        while (canHit(player) && isHitSign(player, predicate)) {
            consumer.accept(player.hit(cardDeck.draw()));
        }
    }

    private boolean isHitSign(Participant player, Predicate<String> predicate) {
        return predicate.test(player.getName());
    }

    private void hitOrStayToDealer(Consumer<Participant> consumer) {
        Participant dealer = null;
        while (canHit(this.dealer)) {
            dealer = this.dealer.hit(cardDeck.draw());
        }
        consumer.accept(dealer);
    }

    private boolean canHit(Participant participant) {
        return !participant.isBust() && !participant.isBlackjack() && !participant.isFinish();
    }

    public List<ParticipantDto> getParticipant() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(ParticipantDto.from(dealer));
        for (Participant player : players.getPlayerGroup()) {
            participantDtos.add(ParticipantDto.from(player));
        }
        return participantDtos;
    }

    public BlackjackGameResult createMatchResult() {
        return new BlackjackGameResult(dealer, players);
    }

    public List<Participant> getPlayers() {
        return players.getPlayerGroup();
    }

    public Participant getDealer() {
        return dealer;
    }
}
