import machine.BlackjackMachine;
import view.InputView;
import view.OutputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackMachine machine = new BlackjackMachine(new InputView(), new OutputView());
        try {
            machine.run();
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
