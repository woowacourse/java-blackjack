package domain.game;

import domain.CardShuffler;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.ParticipantMoney;

import java.util.LinkedHashMap;
import java.util.Map;

public final class GameManager {

    private final Deck deck;
    private final ParticipantMoney participantMoney;

    private GameManager(final Deck deck, final ParticipantMoney participantMoney) {
        this.deck = deck;
        this.participantMoney = participantMoney;
    }

    public static GameManager create(final Dealer dealer,
                                     final Map<Participant, BettingMoney> playerInfo,
                                     final CardShuffler cardShuffler) {
        return new GameManager(Deck.create(cardShuffler), ParticipantMoney.create(dealer, playerInfo));
    }

    public ParticipantMoney handFirstCards(final Dealer dealer) {
        final Map<Participant, BettingMoney> participantMoneyInfo = participantMoney.getParticipantMoney();
        participantMoneyInfo.keySet()
                .forEach(participant -> participant.addCard(deck.draw(), deck.draw()));
        return ParticipantMoney.create(dealer, participantMoneyInfo);
    }

    public void handCard(final Participant participant) {
        participant.addCard(deck.draw());
    }

    public ParticipantMoney getFirstBettingResult(final Dealer dealer,
                                                  final ParticipantMoney participantMoneyInfo) {
        final Map<Participant, BettingMoney> playerMoney =
                new LinkedHashMap<>(participantMoneyInfo.getPlayerMoney());
        playerMoney.replaceAll((player, money) -> getBettingMoney(dealer, player, money));
        return ParticipantMoney.create(dealer, playerMoney);
    }

    private BettingMoney getBettingMoney(final Dealer dealer, final Participant player, final BettingMoney money) {
        if (dealer.isBlackJack() && !player.isBlackJack()) {
            return BettingMoney.zero();
        }
        return money;
    }
}
