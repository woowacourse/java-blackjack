package controller;

import model.Name;
import view.PlayerRegisterView;
import view.View;

public final class Controller {
    private final PlayerRegisterView playerRegisterView = new PlayerRegisterView();

    public View guideToInputName() {
        return playerRegisterView.guideToInputName();
    }

    public View guideToBet(Name playerName) {
        return playerRegisterView.guideToBet(playerName);
    }
}
