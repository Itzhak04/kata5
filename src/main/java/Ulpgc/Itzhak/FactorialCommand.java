package Ulpgc.Itzhak;

import java.util.stream.IntStream;

public class FactorialCommand implements Command{
    @Override
    public Output excute(Input input) {
        try {
            int number= Integer.parseInt(input.get(":number"));
            return isOutOfBound(number)? outOfBoundOutput():outputOf(number);
        }catch (NumberFormatException e){
            return nanOutput();
        }
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

    private int factorialOf(int number) {
        return IntStream.rangeClosed(2, number).reduce(1, (a1, b) -> a1 * b);
    }

    private Output outOfBoundOutput() {
        return new Output() {
            @Override
            public int responseCode() {
                return 404;
            }

            @Override
            public String result() {
                return "Number out of bound ";
            }
        };
    }

    private boolean isOutOfBound(int number) {
        return number<1|| number>30;

    }
}
