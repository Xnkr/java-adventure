import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class CowFind {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("cowfind.in"));
        PrintWriter output = new PrintWriter("cowfind.out");
        String x = input.next();

        int openCount = 0;
        int result = 0;
        for (int i = 0; i < x.length()-1; i++){
            if((x.charAt(i) == '(') && (x.charAt(i+1) == '(')){
                openCount ++;
            }
            else if ((x.charAt(i) == ')') && x.charAt(i+1) == ')'){
                result += openCount;
            }
        }

        output.println(result);
        output.close();

    }
}
