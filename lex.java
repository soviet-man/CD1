import java.util.*;

class LexicalAnalyzer {
    static final List<String> keywords = Arrays.asList("if", "else", "while", "do", "break", "continue", "int", "double", "float", "return", "char", "case", "sizeof", "long", "short", "typedef", "switch", "unsigned", "void", "static", "struct", "goto");
    static final List<String> operators = Arrays.asList("+", "-", "*", "/", ">", "<", "=", "!=", "<=", ">=");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the code to analyze and type 'exit' to finish:");
        
        while (true) {
            String input = sc.nextLine();
            if (input.equals("exit")) break;
            parse(input);
        }
        sc.close();
    }

    static void parse(String str) {
        for (String token : str.split("\\s+")) {
            if (keywords.contains(token)) {
                System.out.println("'" + token + "' IS A KEYWORD");
            } else if (operators.contains(token)) {
                System.out.println("'" + token + "' IS AN OPERATOR");
            } else if (token.matches("\\d+")) {
                System.out.println("'" + token + "' IS AN INTEGER");
            } else if (token.matches("\\d+\\.\\d+")) {
                System.out.println("'" + token + "' IS A REAL NUMBER");
            } else if (Character.isLetter(token.charAt(0))) {
                System.out.println("'" + token + "' IS A VALID IDENTIFIER");
            } else {
                System.out.println("'" + token + "' IS NOT A VALID IDENTIFIER");
            }
        }
    }
}
