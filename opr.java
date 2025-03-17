import java.util.*;

public class opr {
    public static void main(String[] args) {
        char[] stack = new char[20], ip = new char[20], ter = new char[10];
        char[][] opt = new char[10][10];
        int i, j, k, n, top = 0, col = 0, row = 0;
        Scanner sc = new Scanner(System.in);

        // Input number of terminals
        System.out.println("Enter the number of terminals:");
        n = sc.nextInt();

        // Input terminals
        System.out.println("Enter the terminals:");
        ter = sc.next().toCharArray();

        // Input operator precedence table
        System.out.println("Enter the table values:");
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                System.out.print("Enter the value for " + ter[i] + " " + ter[j] + ": ");
                opt[i][j] = sc.next().charAt(0);
            }
        }

        // Display the operator precedence table
        System.out.println("\nOPERATOR PRECEDENCE TABLE:");
        System.out.print("\t");
        for (i = 0; i < n; i++) {
            System.out.print(ter[i] + "\t");
        }
        System.out.println();
        for (i = 0; i < n; i++) {
            System.out.print(ter[i] + "\t");
            for (j = 0; j < n; j++) {
                System.out.print(opt[i][j] + "\t");
            }
            System.out.println();
        }

        // Start parsing input string
        stack[top] = '$';
        System.out.println("\nEnter the input string:");
        ip = sc.next().toCharArray();
        i = 0;

        System.out.println("\nSTACK\t\tINPUT\t\tACTION");
        System.out.println("$\t\t" + String.valueOf(ip) + "\t\tShift i"); // Fix initial display

        while (true) {
            for (k = 0; k < n; k++) {
                if (stack[top] == ter[k])
                    col = k;
                if (i < ip.length && ip[i] == ter[k])
                    row = k;
            }

            if (stack[top] == '$' && i == ip.length) {
                System.out.println("String is accepted");
                break;
            } else if (i < ip.length && (opt[col][row] == '<' || opt[col][row] == '=')) {
                stack[++top] = opt[col][row];
                stack[++top] = ip[i];
                i++;
                System.out.println(String.valueOf(stack, 0, top + 1) + "\t\t" + String.valueOf(ip, i, ip.length - i) + "\t\tShift");
            } else if (opt[col][row] == '>') {
                while (stack[top] != '<') {
                    --top;
                }
                top--; // Reduce action, remove top two symbols
                System.out.println(String.valueOf(stack, 0, top + 1) + "\t\t" + String.valueOf(ip, i, ip.length - i) + "\t\tReduce");
            } else {
                System.out.println("String is not accepted");
                break;
            }
        }
        sc.close();
    }
}
