package domain.game;

import controller.dto.response.PlayerOutcome;
import domain.constants.Outcome;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;

public class Referee {
    private final Participants participants;

    public Referee(final Participants participants) {
        this.participants = participants;
    }

    // TODO : 메서드 길이 10라인으로 줄이기
    public List<PlayerOutcome> judge() {
        if (participants.getDealer().isBlackJack()) {
            return participants.getPlayersOutcomeIf(
                    this::judgeWhenDealerIsBlackJack
            );
        }

        if (participants.getDealer().isBusted()) {
            return participants.getPlayersOutcomeIf(
                    this::judgeWhenDealerIsBusted
                    // TODO: 두 장이고 21이면 BLACKJACK / 그냥 isNotBusted 이면 WIN / 그 외 BUST면 LOSE / 여기서 참가자가 블랙잭이면 BLACKJACK
            );
        }

        return participants.getPlayersOutcomeIf(
                this::judgeWhenDealerIsNotBustedAndNotBlackJack
                // TODO: 딜러가 블랙잭이고 플레이어도 블랙잭이면 TIE / 딜러만 블랙잭이면 LOSE / 참가자만 블랙잭이면 BLACKJACK / 딜러도 플레이어도 블랙잭이 아니면 isWinner 함수 호출
        );
    }

    private Outcome judgeWhenDealerIsBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return Outcome.TIE;
        }
        return Outcome.LOSE;
    }

    private Outcome judgeWhenDealerIsBusted(final Player player) {
        if (player.isBlackJack()) {
            return Outcome.BLACKJACK;
        }
        if (player.isNotBusted()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private Outcome judgeWhenDealerIsNotBustedAndNotBlackJack(final Player player) {
        if (player.isBlackJack()) {
            return Outcome.BLACKJACK;
        }
        if (player.isBusted()) {
            return Outcome.LOSE;
        }
        if (player.isNotSameScoreAs(participants.getDealer())) {
            return verifyHasMoreScoreThanDealer(player, participants.getDealer());
        }
        return verifyHasLessOrSameCardThanDealer(player, participants.getDealer());
    }

    // TODO : 아래 두 함수를 개선할 수 없나? Outcome에서 하고 싶은데 true, false를 넘기기도 애매하고 ................
    private Outcome verifyHasMoreScoreThanDealer(final Player player, final Dealer dealer) {
        if (player.hasMoreScoreThan(dealer)) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private Outcome verifyHasLessOrSameCardThanDealer(final Player player, final Dealer dealer) {
        if (player.hasLessOrSameCardThan(dealer)) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }
}
