/**
 * <p>Title: Algoritmo Kuhn-Munkres</p>
 *
 * <p>Description: es un algoritmo de optimización el cual resuelve problemas
 * de asignación. La primera versión conocida del método Húngaro, fue inventado
 * y publicado por Harold Kuhn en 1955. Este fue revisado por James Munkres en
 * 1957, y ha sido conocido desde entonces como el algoritmo Húngaro,
 * el algoritmo de asignamiento de Munkres, o el algoritmo de Kuhn-Munkres.
 *
 * El algoritmo desarrollado por Kuhn está basado fundamentalmente en los
 * primeros trabajos de otros dos matemáticos Húngaros: Dénes König y
 * Jenő Egerváry. La gran ventaja del método de Kuhn es que es fuertemente
 * polinómico (ver Complejidad computacional para más detalles).
 *
 * El algoritmo construye una solución del problema primal partiendo de una
 * solución no admisible (que corresponde a una solución admisible del dual)
 * haciéndola poco a poco más admisible.</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * @author Gustavo Bazan, Armando Bracho, Juan Sandomingo & Carlos Proano
 *
 * @version 1.0
 */



package munkres;

//~--- JDK imports ------------------------------------------------------------

import java.io.*;

import java.util.*;
import java.util.ArrayList;

public class munkres {
    static Par Z0;

    static public int[] munkres(int n, double[][] costs) {
        int        paso;
        boolean    hecho;
        double[][] C   = new double[n][n];
        int[][]    M   = new int[n][n];
        int[]      Fil = new int[n];
        int[]      Col = new int[n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                C[j][i] = costs[j][i];
            }
        }

        hecho = false;
        paso  = 1;

        while (!hecho) {
            switch (paso) {
            case 1 :
                paso = paso1(n, C, M, Fil, Col);
            break;

            case 2 :
                paso = paso2(n, C, M, Fil, Col);
            break;

            case 3 :
                paso = paso3(n, C, M, Fil, Col);
            break;

            case 4 :
                paso = paso4(n, C, M, Fil, Col);
            break;

            case 5 :
                paso = paso5(n, C, M, Fil, Col);
            break;

            case 6 :
                paso = paso6(n, C, M, Fil, Col);
            break;

            default :
                hecho = true;
            }
        }

        int[] matching = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    matching[i] = j;
                    continue;
                }
            }
        }
        return matching;
    }

    static public boolean estrellaEnFil(int n, int[][] M, int row) {
        for (int j = 0; j < n; j++) {
            if (M[row][j] == 1) {
                return true;
            }
        }
        return false;
    }

    static public void convertirCamino( int[][] M, ArrayList<Par> path) {
        for (int i = 0; i < path.size(); i++) {
            Par point = path.get(i);

            if (M[point.row][point.column] == 1) {
                M[point.row][point.column] = 0;
            } else {
                M[point.row][point.column] = 1;
            }
        }
    }

    static public Par encontrarUnCero(int n, double[][] C, int[] R_cov, int[] C_cov) {
        int     row  = -1;
        int     col  = -1;
        boolean done = false;

        for (int i = 0; (i < n) &&!done; i++) {
            for (int j = 0; j < n; j++) {
                if ((C[i][j] == 0) && (R_cov[i] == 0) && (C_cov[j] == 0)) {
                    row  = i;
                    col  = j;
                    done = true;
                }
            }
        }

        return new Par(row, col);
    }

    static public Par encontrarPrimeEnFila(int n, int[][] M, int row) {
        int column = -1;

        for (int j = 0; j < n; j++) {
            if (M[row][j] == 2) {
                column = j;
            }
        }

        return new Par(row, column);
    }

    static public double encontrarMenor(int n, double[][] C, int[] R_cov, int[] C_cov) {
        double minval = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((R_cov[i] == 0) && (C_cov[j] == 0) && (minval > C[i][j])) {
                    minval = C[i][j];
                }
            }
        }

        return minval;
    }

    static public Par encontrarEstrellaEnCol(int n, int[][] M, int column) {
        int row = -1;

        for (int i = 0; i < n; i++) {
            if (M[i][column] == 1) {
                row = i;
            }
        }

        return new Par(row, column);
    }

    static public int encontrarEstrellaEnFil(int n, int[][] M, int row) {
        int col = -1;

        for (int j = 0; j < n; j++) {
            if (M[row][j] == 1) {
                col = j;
            }
        }

        return col;
    }

    static public void limpiarMarcas(int n, int[] R_cov, int[] C_cov) {
        for (int i = 0; i < n; i++) {
            R_cov[i] = 0;
            C_cov[i] = 0;
        }
    }

    static public void borrarPrimes(int n, int[][] M) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 2) {
                    M[i][j] = 0;
                }
            }
        }
    }

    /**
     * Busca el elemento menor por fila y se lo resta a esta
     */
    static public int paso1(int n, double[][] C, int[][] M, int[] Row, int[] Col) {
        double minval = 0;

        for (int i = 0; i < n; i++) {
            minval = C[i][0];

            for (int j = 1; j < n; j++) {
                if (minval > C[i][j]) {
                    minval = C[i][j];
                }
            }

            for (int j = 0; j < n; j++) {
                C[i][j] -= minval;
            }
        }

        return 2;
    }

    /**
     * Considero M, y se etiquetan las filas que no han sido acopladas
     * o asignadas por el algoritmo de matching máximo.
     */
    static public int paso2(int n, double[][] C, int[][] M, int[] R_cov, int[] C_cov) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((C[i][j] == 0) && (C_cov[j] == 0) && (R_cov[i] == 0)) {
                    M[i][j]  = 1;
                    C_cov[j] = 1;
                    R_cov[i] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            C_cov[i] = 0;
            R_cov[i] = 0;
        }

        return 3;
    }

    /**
     * Se etiquetan en M, las columnas que tienen los ceros en correspondencia
     */
    static public int paso3(int n, double[][] C, int[][] M, int[] R_cov, int[] C_cov) {
        int count;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    C_cov[j] = 1;
                }
            }
        }

        count = 0;

        for (int j = 0; j < n; j++) {
            count = count + C_cov[j];
        }

        int paso;

        if (count >= n) {
            paso = 7;
        } else {
            paso = 4;
        }

        return paso;
    }

    /**
     * Etiquetar las filas que no han sido ya etiquetadas y acopladas o asignadas
     * por el algoritmo de matching máximo con las columnas ya etiquetadas
     */
    static public int paso4(int n, double[][] C, int[][] M, int[] R_cov, int[] C_cov) {
        while (true) {
            Par pair = encontrarUnCero(n, C, R_cov, C_cov);

            if (pair.row == -1) {
                return 6;
            }

            M[pair.row][pair.column] = 2;

            if (estrellaEnFil(n, M, pair.row)) {
                int col = encontrarEstrellaEnFil(n, M, pair.row);

                R_cov[pair.row] = 1;
                C_cov[col]      = 0;
            } else {
                Z0 = pair;

                return 5;
            }
        }
    }

    /**
     * Borrar las filas etiquetadas y las columnas NO etiquetadas.
     */
    static public int paso5(int n, double[][] C, int[][] M, int[] R_cov, int[] C_cov) {
        int            count = 1;
        ArrayList<Par> path  = new ArrayList<Par>();

        path.add(Z0);

        boolean done = false;

        while (!done) {
            Par last = path.get(path.size() - 1);
            Par star = encontrarEstrellaEnCol(n, M, last.column);

            if (star.row > -1) {
                path.add(new Par(star.row, last.column));
            } else {
                done = true;
            }

            if (!done) {
                Par prime = encontrarPrimeEnFila(n, M, star.row);

                path.add(new Par(star.row, prime.column));
            }
        }

        convertirCamino(M, path);
        limpiarMarcas(n, R_cov, C_cov);
        borrarPrimes(n, M);

        return 3;
    }

    /**
     * Sea minval, el elemento de valor mínimo entre aquellos costos no borrado.
     * Restar minval, a cada elemento no borrado y sumarlo a los elementos
     * doblemente borrados (o donde álla intersección o cruces entre las lineas marcadas)
     */
    static public int paso6(int n, double[][] C, int[][] M, int[] R_cov, int[] C_cov) {
        double minval = encontrarMenor(n, C, R_cov, C_cov);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (R_cov[i] == 1) {
                    C[i][j] += minval;
                }

                if (C_cov[j] == 0) {
                    C[i][j] -= minval;
                }
            }
        }

        return 4;
    }

    static public class Par {
        public int column;
        public int row;

        public Par(int row, int column) {
            this.row    = row;
            this.column = column;
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
