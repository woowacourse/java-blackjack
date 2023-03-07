package domain.game;

import domain.card.CardArea;
import domain.card.CardDeck;
import domain.player.*;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class BlackJackGame {

    private static final int MAX_PARTICIPANT_COUNT_INCLUDE = 10;

    private final Set<Gambler> gamblers;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    BlackJackGame(final List<Gambler> gamblers, final Dealer dealer, final CardDeck cardDeck) {
        this.gamblers = new LinkedHashSet<>(gamblers);
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public static BlackJackGame defaultSetting(final CardDeck cardDeck, final List<Name> participantNames) {
        validateParticipantsSize(participantNames);
        final List<Gambler> gamblers = participantNames.stream()
                .map(it -> new Gambler(it, new CardArea(cardDeck.draw(), cardDeck.draw())))
                .collect(toList());
        final Dealer dealer = new Dealer(new CardArea(cardDeck.draw(), cardDeck.draw()));
        return new BlackJackGame(gamblers, dealer, cardDeck);
    }

    private static void validateParticipantsSize(final List<Name> participantNames) {
        if (participantNames.size() > MAX_PARTICIPANT_COUNT_INCLUDE) {
            throw new IllegalArgumentException(String.format("참가자는 %d명 이하여야 합니다.", MAX_PARTICIPANT_COUNT_INCLUDE));
        }
    }

    public boolean existCanHitParticipant() {
        return gamblers.stream().anyMatch(Gambler::canHit);
    }

    public Gambler findCanHitParticipant() {
        return gamblers.stream()
                .filter(Gambler::canHit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Hit 가능한 참여자가 없습니다."));
    }

    public void hitOrStayForParticipant(final Gambler gambler, final HitState hitState) {
        validatePlayerExist(gambler);
        gambler.changeState(hitState);
        if (gambler.wantHit()) {
            gambler.hit(cardDeck.draw());
        }
    }

    private void validatePlayerExist(final Gambler gambler) {
        if (!gamblers.contains(gambler)) {
            throw new IllegalArgumentException("해당 플레이어는 게임에 참여하지 않았습니다.");
        }
    }

    public boolean isDealerShouldMoreHit() {
        return dealer.canHit();
    }

    public void hitForDealer() {
        dealer.hit(cardDeck.draw());
    }

    public Map<Gambler, DealerCompeteResult> statistic() {
        return gamblers.stream()
                .collect(toMap(
                        identity(),
                        dealer::compete
                ));
    }

    public List<Gambler> gamblers() {
        return new ArrayList<>(gamblers);
    }

    public Dealer dealer() {
        return dealer;
    }
}