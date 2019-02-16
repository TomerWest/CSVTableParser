public class Main {

    public static void main(String... args) {
        Parser parser = Parser.getInstance();
        parser.setFile("./example.csv", "header1", "header2");
        printMatrix(parser.parse());
    }

    private static void printMatrix(double[][] mat) {
        System.out.println("{");
        for (int i = 0; i < mat.length; i++) {
            System.out.print("\t{");
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + (j != mat[i].length-1 ? ",\t" : ""));
            }
            System.out.println("}"+(i < mat.length-1 ? "," : "" ));
        }
        System.out.println("}");
    }
}
