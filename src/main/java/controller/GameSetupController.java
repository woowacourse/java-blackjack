package controller;

import model.participant.Name;
import view.PlayerRegisterView;
import view.View;

public final class GameSetupController {
    private final PlayerRegisterView playerRegisterView = new PlayerRegisterView();

    public View guideToInputName() {
        return playerRegisterView.guideToInputName();
    }

    public View guideToBet(Name playerName) {
        return playerRegisterView.guideToBet(playerName);
    }
}
