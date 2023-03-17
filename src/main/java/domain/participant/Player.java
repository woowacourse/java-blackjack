package domain.participant;

import domain.card.Card;

import java.util.List;

import static domain.game.EarningRate.BONUS;
import static domain.game.EarningRate.LOSE;
import static domain.game.EarningRate.WIN;
import static domain.participant.Participant.DEALER_NAME;

public final class Player implements Playable {
    private final Participant participant;
    private ParticipantMoney participantMoney;

    private Player(final Participant participant) {
        this.participant = participant;
    }

    public static Player create(final String name) {
        validatePlayerName(name);
        return new Player(Participant.create(name));
    }

    private static void validatePlayerName(final String name) {
        if (DEALER_NAME.isSame(name)) {
            throw new IllegalArgumentException(
                    String.format("플레이어는 '%s'라는 이름을 가질 수 없습니다.", DEALER_NAME.getName()));
        }
    }

    public void bet(final ParticipantMoney participantMoney) {
        this.participantMoney = participantMoney;
    }

    public void earnMoney() {
        participantMoney = WIN.calculateMoney(participantMoney);
    }

    public void loseMoney() {
        participantMoney = LOSE.calculateMoney(participantMoney);
    }

    public void earnBonusMoney() {
        participantMoney = BONUS.calculateMoney(participantMoney);
    }

    @Override
    public void addCard(final Card... cards) {
        participant.addCard(cards);
    }

    @Override
    public boolean canDrawCard() {
        return !participant.isBust() && !participant.isBlackJack();
    }

    @Override
    public boolean isBlackJack() {
        return participant.isBlackJack();
    }

    @Override
    public boolean isBust() {
        return participant.isBust();
    }

    @Override
    public int calculateScore() {
        return participant.calculateScore().getScore();
    }

    @Override
    public void resetMoney(final ParticipantMoney initMoney) {
        participantMoney = initMoney;
    }

    public String getName() {
        return participant.getName();
    }

    public List<Card> getHand() {
        return participant.getHand();
    }

    public int getMoney() {
        return (int) participantMoney.getMoney();
    }
}
