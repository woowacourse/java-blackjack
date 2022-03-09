import controller.BlackJackController;
import service.dto.InitGameDto;
import service.dto.NamesDto;
import java.util.List;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackJackController controller = new BlackJackController(new BlackJackService());
        List<String> names = InputView.inputPlayerNames();
        NamesDto namesDto = new NamesDto(names);
        InitGameDto initGameDto = controller.initGame(namesDto);
        OutputView.printInit(initGameDto);
    }
}
