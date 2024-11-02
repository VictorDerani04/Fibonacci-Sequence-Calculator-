
public class FibonnacciTester {
    public static void main(String[] args) {
//        if (args.length < 2) {
//            throw new IllegalArgumentException("Two integer arguments required: maxInput and maxTime.");
//        }

        int maxInput;
        long maxTime;
        maxInput = Integer.parseInt(args[0]);
        maxTime = Long.parseLong(args[1]);

        //try and catch statements to work on exceptions
//        try {
//            maxInput = Integer.parseInt(args[0]);
//            maxTime = Long.parseLong(args[1]);
//            if (maxInput < 0 || maxTime < 0) {
//                throw new IllegalArgumentException("Arguments must be non-negative integers.");
//            }
//        }
//        catch (NumberFormatException e) {
//            throw new IllegalArgumentException("Arguments must be valid integers.");
//        }

        boolean recurse = true;
        boolean dynamic = true;
        boolean closed = true;

        //loop that runs through all three functions and check if they go over maxTime.
        //If they do, it creates a false statement related to the function, and it stops calling such function.
        for(int n = 0; n <= maxInput; n++) {
            if(recurse) {
                long startTime = System.currentTimeMillis();
                long dynamicProgram = recursion(n);
                long endTime = System.currentTimeMillis();
                long timeToRun = endTime - startTime;
                System.out.println("RECURSE\t" + n + "\t" + dynamicProgram + "\t" + timeToRun);
                if(timeToRun > maxTime){
                    recurse = false;
                }
            }

            if(dynamic) {
                long startTime = System.currentTimeMillis();
                long recurseProgram = dynamic(n);
                long endTime = System.currentTimeMillis();
                long timeToRun = endTime - startTime;
                System.out.println("DYNAMIC\t" + n + "\t" + recurseProgram + "\t" + timeToRun);
                if(timeToRun > maxTime){
                    dynamic = false;
                }
            }

            if(closed) {
                long startTime = System.currentTimeMillis();
                long closedProgram = closed(n);
                long endTime = System.currentTimeMillis();
                long timeToRun = endTime - startTime;
                System.out.println("CLOSED\t" + n + "\t" + closedProgram + "\t" + timeToRun);
                if(timeToRun > maxTime){
                    closed = false;
                }
            }

            if(!recurse && !dynamic && !closed){
                break;
            }
        }
    }
    public static long recursion(int n){
        if(n < 2){
            return n;
        }
        long m = recursion(n-2);
        long p = recursion(n-1);
        return p + m;
    }
    public static long dynamic(int n){
        if(n < 2){
            return n;
        }
        long answer = 0;
        long initial0 = 0;
        long initial1 = 1;
        for(int i = 0; i < n - 1; i++){
            if(i % 2 == 0) {
                answer = initial0 + initial1;
                initial0 = answer;
            }
            else{
                answer = initial0 + initial1;
                initial1 = answer;
            }
        }
        return answer;
    }
    public static long closed(int n){
        double goldenRatio = (1 + Math.sqrt(5))/2;
        double delta = 1/(Math.pow(-goldenRatio, n));
        double answer = (Math.pow(goldenRatio, n) - delta)/Math.sqrt(5);
        return (long)answer;
    }
}
