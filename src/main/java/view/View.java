package view;

import dto.*;

public class View {

    private final InputView inputView;
    private final OutputView outputView;

    public View(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public String inputPlayerNames() {
        return inputView.inputPlayerNames();
    }

    public String inputAdditionalCard(String name) {
        return inputView.inputAdditionalCard(name);
    }

    public void outputInitialMessage(InitialDto initialDto) {
        outputView.outputInitialMessage(initialDto);
    }

    public void playerResultMessage(ResultDto resultDto) {
        outputView.playerResultMessage(resultDto);
    }

    public void outputPlayerDeckDtos(PlayerResultDto playerResultDto) {
        outputView.outputPlayerDeckDtos(playerResultDto);
    }

    public void outputDealerAdditionCardMessage() {
        outputView.outputDealerAdditionCardMessage();
    }
}
