package domain.game;

import domain.area.CardArea;
import domain.deck.CardDeck;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final List<Participant> participants;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    BlackJackGame(final List<Participant> participants, final Dealer dealer, final CardDeck cardDeck) {
        this.participants = participants;
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public static BlackJackGame defaultSetting(final CardDeck cardDeck, final List<Name> participantNames) {
        final List<Participant> participants = participantNames.stream()
                .map(it -> new Participant(it, new CardArea(cardDeck.draw(), cardDeck.draw())))
                .collect(Collectors.toList());

        final Dealer dealer = new Dealer(new CardArea(cardDeck.draw(), cardDeck.draw()));

        return new BlackJackGame(participants, dealer, cardDeck);
    }

    public boolean existCanHitParticipant() {
        return participants.stream().anyMatch(Participant::canHit);
    }

    public Participant findCanHitParticipant() {
        return participants.stream()
                .filter(Participant::canHit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Hit 가능한 참여자가 없습니다."));
    }

    public void hitOrStayForParticipant(final Participant participant) {
        participants.stream()
                .filter(participant::equals)
                .filter(Participant::wantHit)
                .findAny()
                .ifPresent(it -> it.hit(cardDeck.draw()));
    }

    public boolean isDealerShouldMoreHit() {
        return dealer.canHit();
    }

    public void hitForDealer() {
        dealer.hit(cardDeck.draw());
    }

    public List<Participant> participants() {
        return new ArrayList<>(participants);
    }

    public Dealer dealer() {
        return dealer;
    }

    public CardDeck cardDeck() {
        return cardDeck;
    }
}