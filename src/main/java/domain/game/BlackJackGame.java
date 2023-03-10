package domain.game;

import domain.card.CardDeck;
import domain.player.BettingMoney;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.HitState;
import domain.player.Name;
import domain.player.Participant;
import domain.player.Revenue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static BlackJackGame defaultSetting(final CardDeck cardDeck, final Map<Name, BettingMoney> battingMoneyMap) {
        validateGamblersSize(battingMoneyMap.keySet());
        final Dealer dealer = new Dealer(cardDeck.createCardArea());
        final List<Gambler> gamblers = battingMoneyMap.entrySet()
                .stream()
                .map(entry -> new Gambler(entry.getKey(), cardDeck.createCardArea(), entry.getValue()))
                .collect(toList());
        return new BlackJackGame(gamblers, dealer, cardDeck);
    }

    private static void validateGamblersSize(final Collection<Name> gamblerNames) {
        if (gamblerNames.size() > MAX_PARTICIPANT_COUNT_INCLUDE) {
            throw new IllegalArgumentException(String.format("참가자는 %d명 이하여야 합니다.", MAX_PARTICIPANT_COUNT_INCLUDE));
        }
    }

    public List<Gambler> gamblers() {
        return new ArrayList<>(gamblers);
    }

    public Dealer dealer() {
        return dealer;
    }

    public boolean existCanHitGambler() {
        return gamblers.stream().anyMatch(Gambler::canHit);
    }

    public Gambler findCanHitGambler() {
        return gamblers.stream()
                .filter(Gambler::canHit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Hit 가능한 참여자가 없습니다."));
    }

    public void hitOrStayForGambler(final Gambler gambler, final HitState hitState) {
        validatePlayerExist(gambler);
        gambler.changeState(hitState);
        gambler.hitOrStay(cardDeck);
    }

    private void validatePlayerExist(final Gambler gambler) {
        if (!gamblers.contains(gambler)) {
            throw new IllegalArgumentException("해당 플레이어는 게임에 참여하지 않았습니다.");
        }
    }

    public boolean hitForDealerWhenShouldMoreHit() {
        return dealer.hitOrStay(cardDeck);
    }

    public Map<Participant, Revenue> revenue() {
        final Map<Participant, Revenue> participantRevenue = gamblers.stream()
                .collect(toMap(
                        identity(),
                       gambler -> gambler.compete(dealer)
                ));

        Revenue dealerRevenue = Revenue.total(participantRevenue.values()).reverse();
        participantRevenue.put(dealer, dealerRevenue);
        return participantRevenue;
    }
}
