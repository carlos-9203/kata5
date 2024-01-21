package software.ulpgc.kata5;

import java.util.stream.IntStream;

public class FactorialCommand implements Command {

    @Override
    public Output execute(Input input) {
        try {
            int number = Integer.parseInt(input.get(".number"));
            return isOutOfBound(number) ? outOfBoundOutput() : outputOf(number);
        } catch (NumberFormatException e){
            return nanOutput();
        }
    }

    private boolean isOutOfBound(int number) {
        return number < 1 || number > 20;
    }

    private Output outOfBoundOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 404;
            }

            @Override
            public String result() {
                return "Number out of bounds";
            }
        };
    }

    private Output outputOf(int number) {
        return new Output() {
            @Override
            public int responseCode() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf(factorialOf(number));
            }
        };
    }
    private static int factorialOf(int number){
        return IntStream.range(2, number).reduce(1, (a, i) ->a*i);
    }
    private Output nanOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 405;
            }

            @Override
            public String result() {
                return "Not a number";
            }
        };
    }
}
