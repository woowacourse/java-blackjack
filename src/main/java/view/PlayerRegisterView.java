package view;

import model.participant.Name;

public final class PlayerRegisterView {
    public View guideToInputName() {
        return () -> String.format("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)%n");
    }

    public View guideToBet(Name playerName) {
        return () -> String.format("%n%s의 배팅 금액은?%n", playerName.name());
    }
}
