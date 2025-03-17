import java.util.Scanner;

public class LeftFactoringSimplified {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of productions: ");
        int num = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter the productions (e.g., A -> ab | ac):");
        for (int i = 0; i < num; i++) {
            String production = scanner.nextLine().trim();
            String[] parts = production.split("->");
            String nonTerminal = parts[0].trim();
            String[] choices = parts[1].split("\\|");

            System.out.println("GRAMMAR: " + production);
            String prefix = choices[0];
            for (String choice : choices) {
                while (!choice.startsWith(prefix)) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty()) break;
                }
            }

            if (!prefix.isEmpty()) {
                System.out.println(nonTerminal + " -> " + prefix + nonTerminal + "'");
                System.out.print(nonTerminal + "' -> ");
                for (int j = 0; j < choices.length; j++) {
                    String suffix = choices[j].startsWith(prefix) ? choices[j].substring(prefix.length()).trim() : choices[j].trim();
                    System.out.print((suffix.isEmpty() ? "ɛ" : suffix) + (j < choices.length - 1 ? " | " : ""));
                }
                System.out.println();
            } else {
                System.out.println(nonTerminal + " has no common prefix. No left factoring needed.");
            }
        }
        scanner.close();
    }
}
