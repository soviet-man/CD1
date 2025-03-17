import java.util.Scanner;

class ProductionRule {
    String left;
    String right;

    ProductionRule(String left, String right) {
        this.left = left;
        this.right = right;
    }
}

public class SRP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input, stack = "";
        int ruleCount;

        // User input for the number of production rules
        System.out.println("Enter the number of production rules: ");
        ruleCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // User input for each production rule in the form 'left->right'
        ProductionRule[] rules = new ProductionRule[ruleCount];
        System.out.println("Enter the production rules (in the form 'left->right'): ");
        for (int i = 0; i < ruleCount; i++) {
            String[] temp = scanner.nextLine().split("->");
            rules[i] = new ProductionRule(temp[0], temp[1]);
        }

        // User input for the input string
        System.out.println("Enter the input string: ");
        input = scanner.nextLine();

        int i = 0;
        while (true) {
            // If there are more characters in the input string, add the next character to the stack
            if (i < input.length()) {
                char ch = input.charAt(i);
                i++;
                stack += ch;
                System.out.print(stack + "\t");
                System.out.print(input.substring(i) + "\tShift " + ch + "\n");
            }

            // Iterate through the production rules
            for (int j = 0; j < ruleCount; j++) {
                // Check if the right-hand side of the production rule matches a substring in the stack
                int substringIndex = stack.indexOf(rules[j].right);
                if (substringIndex != -1) {
                    // Replace the matched substring with the left-hand side of the production rule
                    stack = stack.substring(0, substringIndex) + rules[j].left;
                    System.out.print(stack + "\t");
                    System.out.print(input.substring(i) + "\tReduce " + rules[j].left + "->" + rules[j].right + "\n");
                    j = -1; // Restart the loop to ensure immediate reduction of the newly derived production rule
                }
            }

            // Check if the stack contains only the start symbol and if the entire input string has been processed
            if (stack.equals(rules[0].left) && i == input.length()) {
                System.out.println("\nAccepted");
                break;
            }

            // Check if the entire input string has been processed but the stack doesn't match the start symbol
            if (i == input.length()) {
                System.out.println("\nNot Accepted");
                break;
            }
        }

        scanner.close();
    }
}