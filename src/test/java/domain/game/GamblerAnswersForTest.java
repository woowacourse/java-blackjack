package domain.game;

import domain.participant.Gambler;

public class GamblerAnswersForTest {

    static GamblerAnswer neverCalled() {
        return new GamblerAnswer() {
            @Override
            public boolean isAnswerOK(Gambler gambler) {
                throw new IllegalStateException("선택조차 할 수 없어야 합니다.");
            }

            @Override
            public void notifyResult(Gambler gambler) {
                throw new IllegalStateException("받는다는 선택을 했으므로 실패입니다.");
            }
        };
    }

    static GamblerAnswer alwaysOK() {
        return new GamblerAnswer() {
            @Override
            public boolean isAnswerOK(Gambler gambler) {
                return true;
            }

            @Override
            public void notifyResult(Gambler gambler) {
                // do Nothing
            }
        };
    }

    static GamblerAnswer neverOK() {
        return new GamblerAnswer() {
            @Override
            public boolean isAnswerOK(Gambler gambler) {
                return false;
            }

            @Override
            public void notifyResult(Gambler gambler) {
                throw new IllegalStateException("이 구현체는 절대 OK라는 선택을 하지 않습니다.");
            }
        };
    }
}
