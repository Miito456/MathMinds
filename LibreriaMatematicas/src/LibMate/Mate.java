package LibMate;

public class Mate {

    public static final double PI = 3.14159265358979323846;

    public static double raizCuadrada(double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("No se puede calcular la raíz cuadrada de un número negativo");
        }

        if (numero == 0) {
            return 0;
        }

        double estimacion = numero / 2;
        double precisionRelativa = 1e-15;

        double cambio;
        do {
            double nuevaEstimacion = 0.5 * (estimacion + numero / estimacion);
            cambio = valorAbsoluto((nuevaEstimacion - estimacion) / estimacion);
            estimacion = nuevaEstimacion;
        } while (cambio > precisionRelativa);

        return estimacion;
    }

    public static double valorAbsoluto(double numero) {
        if (numero < 0) {
            return -numero;
        }
        return numero;
    }

    public static double potencia(double base, double exponente) {
        if (exponente == 0) {
            return 1;
        } else if (exponente > 0) {
            double resultado = base;
            for (int i = 1; i < exponente; i++) {
                resultado *= base;
            }
            return resultado;
        } else {
            return 1 / potencia(base, -exponente);
        }
    }

    public static double exponencial(double x) {
        double resultado = 1.0;
        double termino = 1.0;
        int i = 1;

        while (termino > 1e-15) {
            termino *= x / i;
            resultado += termino;
            i++;
        }

        return resultado;
    }

    public static double seno(double angulo) {
        angulo = angulo % (2 * PI);
        double term = angulo;
        double sum = angulo;
        for (int i = 1; i <= 10; i++) {
            term = -term * angulo * angulo / ((2 * i) * (2 * i + 1));
            sum += term;
        }
        return sum;
    }

    public static double coseno(double angulo) {
        angulo = angulo % (2 * PI);
        double term = 1;
        double sum = 1;
        for (int i = 1; i <= 10; i++) {
            term = -term * angulo * angulo / ((2 * i - 1) * (2 * i));
            sum += term;
        }
        return sum;
    }

    public static double[][] potenciaMatriz(double[][] matriz, int exponente) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        if (filas != columnas) {
            throw new IllegalArgumentException("La matriz debe ser cuadrada para calcular la potencia.");
        }

        double[][] matrizResultado = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == j) {
                    matrizResultado[i][j] = 1;
                } else {
                    matrizResultado[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < exponente; i++) {
            matrizResultado = multiplicarMatrices(matrizResultado, matriz);
        }

        return matrizResultado;
    }

    public static double[][] multiplicarMatrices(double[][] matriz1, double[][] matriz2) {
        int filas1 = matriz1.length;
        int columnas1 = matriz1[0].length;
        int columnas2 = matriz2[0].length;

        if (columnas1 != matriz2.length) {
            throw new IllegalArgumentException("El número de columnas de la primera matriz debe ser igual al número de filas de la segunda matriz.");
        }

        double[][] resultado = new double[filas1][columnas2];

        for (int i = 0; i < filas1; i++) {
            for (int j = 0; j < columnas2; j++) {
                for (int k = 0; k < columnas1; k++) {
                    resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                }
            }
        }

        return resultado;
    }

    public static double calcularFuncionGamma(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("El valor de x debe ser un número real positivo.");
        }

        if (x % 1 == 0) {
            return calcularFactorial(x - 1);
        } else {
            return Mate.raizCuadrada(2 * Mate.PI / x) * Mate.potencia(x / Math.E, x) * (1 + 1 / (12 * x));
        }
    }

    private static double calcularFactorial(double n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        double factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static double logaritmo(double base, double valor) {
        if (base <= 0 || valor <= 0) {
            throw new IllegalArgumentException("Base y valor deben ser mayores que cero.");
        }
        double x = 1.0;
        while (true) {
            double fx = potencia(base, x) - valor;
            double fpx = (potencia(base, x + 0.000001) - potencia(base, x - 0.000001)) / 0.000002;
            double newX = x - fx / fpx;
            if (valorAbsoluto(newX - x) < 1e-6) {
                break;
            }
            x = newX;
        }
        return x;
    }

    private long calcularPermutaciones(int n, int r) {
        long permutaciones = 1;
        for (int i = n; i > n - r; i--) {
            permutaciones *= i;
        }
        return permutaciones;
    }

    private long calcularCombinaciones(int n, int r) {
        long combinaciones = calcularPermutaciones(n, r) / factorial(r);
        return combinaciones;
    }

    private long factorial(int n) {
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static double toRadians(double degrees) {
        return degrees * (PI / 180.0);
    }

}
