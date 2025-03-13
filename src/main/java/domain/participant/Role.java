package domain.participant;

import domain.Bet;
import domain.card.Score;

/**
 * 블랙잭 게임에서 참가자가 수행할 수 있는 역할을 정의합니다.
 * <p>
 * 각 역할은 게임 내에서 취할 수 있는 행동과 보유한 속성을 결정합니다.
 */
public interface Role {

  boolean isHit(final Score score);

  String getName();

  Bet getBet();
}
