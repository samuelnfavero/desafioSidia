import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] room = {1, 2, 3, 4};
        int[] rent = {100, 100, 100, 100};
        int[] requirement = {1, 2, 1, 1};
        int[] budget = {100, 100, 100, 100};

        int value = solution.execute(room,rent,requirement,budget);

        System.out.println(value);
    }


}
