import myexceptions.*;

public class Input {
    public static String input(String message) throws Exception{
        System.out.println(message);
        try {
            String value = System.console().readLine();
            return value;
        } catch (Exception e) {
            throw new InputFailedException("Valor inválido!");
        }
        
    }

    public static int inputInt(String message) throws Exception{
        System.out.println(message);
        try {
            int value = Integer.parseInt(System.console().readLine());
            return value;
        } catch (Exception e) {
            throw new InputFailedException("\nValor inválido!");
        }
        
    }

    public static float inputFloat(String message) throws Exception{
        System.out.println(message);
        try {
            float value = Float.parseFloat(System.console().readLine());
            return value;
        } catch (Exception e) {
            throw new InputFailedException("\nValor inválido!");
        }
    }
}
