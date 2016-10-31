package IA.Azamon;

import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;

import java.util.*;

public class AzamonProblem {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        int ops = 0;
        while (ops != -3) {
            opcions(0);
            ops = in.nextInt();
            if (ops == -1) {
                System.out.println("Introduce una seed o '-1' si deseas usar una seed random:");
                Integer seed = in.nextInt();
                if (seed < 0) seed = random.nextInt();
                else seed = random.nextInt(10000);
                System.out.println("Introduce el número de paquetes:");
                Integer paqnum = in.nextInt();
                System.out.println("Introduce la proporción paquetes/ofertas:");
                Double prop = in.nextDouble();
                AzamonEstado estat = new AzamonEstado(paqnum, seed, prop, seed);
                System.out.println("Si deseas usar el generador de soluciones iniciales 1, introduce '1'; si deseas usar el generador 2, introduce cualquier otro número:");
                if (in.nextInt() == 1) estat.generaSolInicial1();
                else estat.generaSolInicial2();
                boolean succ1, felicidad;
                System.out.println("Si deseas usar los operadores de mover y permutar, introduce '1'; si deseas usar sólo mover, introduce cualquier otro número:");
                if (in.nextInt() == 1) succ1 = true;
                else succ1 = false;
                System.out.println("Si deseas usar la función heurística 1, introduce '1'; si deseas usar la función heurística 2, introduce cualquier otro número:");
                if (in.nextInt() == 1) felicidad = false;
                else felicidad = true;

                AzamonHillClimbingSearch(estat, felicidad, succ1);

            }
            else if (ops == -2) {
                System.out.println("Introduce una seed o '-1' si deseas usar una seed random:");
                Integer seed = in.nextInt();
                if (seed < 0) seed = random.nextInt();
                System.out.println("Introduce el número de paquetes:");
                Integer paqnum = in.nextInt();
                System.out.println("Introduce la proporción paquetes/ofertas:");
                Double prop = in.nextDouble();
                AzamonEstado estat = new AzamonEstado(paqnum, seed, prop, seed);
                System.out.println("Si deseas usar el generador de soluciones iniciales 1, introduce '1'; si deseas usar el generador 2, introduce cualquier otro número:");
                if (in.nextInt() == 1) estat.generaSolInicial1();
                else estat.generaSolInicial2();
                boolean succ1, felicidad;
                System.out.println("Si deseas usar los operadores de mover y permutar, introduce '1'; si deseas usar sólo mover, introduce cualquier otro número:");
                if (in.nextInt() == 1) succ1 = true;
                else succ1 = false;
                System.out.println("Si deseas usar la función heurística 1, introduce '1'; si deseas usar la función heurística 2, introduce cualquier otro número:");
                if (in.nextInt() == 1) felicidad = false;
                else felicidad = true;

                AzamonSimulatedAnnealingSearch(estat, felicidad, succ1);
            }

        }






    }

    public static void opcions(int op) {
        if (op == 0) {
            System.out.println("Hill Climbing: -1");
            System.out.println("Simulated Annealing: -2");
            System.out.println("Salir: -3");
        }

    }

    private static void AzamonHillClimbingSearch(AzamonEstado estat, boolean felicitat, boolean succ1) {
        try {
            Problem problem;
            if (felicitat && succ1) problem = new Problem(estat, new AzamonSuccessorFunction(), new AzamonGoalTest(), new AzamonHeuristicFunction2());
            else if (felicitat) problem = new Problem(estat, new AzamonSuccessorFunction2(), new AzamonGoalTest(), new AzamonHeuristicFunction2());
            else if (succ1) problem = new Problem(estat, new AzamonSuccessorFunction(), new AzamonGoalTest(), new AzamonHeuristicFunction1());
            else problem = new Problem(estat, new AzamonSuccessorFunction2(), new AzamonGoalTest(), new AzamonHeuristicFunction1());
            Search search = new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem, search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            System.out.print(((AzamonEstado) search.getGoalState()).toString());
            System.out.println("\n" + ((AzamonEstado) search.getGoalState()).correspondenciasToString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void AzamonSimulatedAnnealingSearch(AzamonEstado estat, boolean felicitat, boolean succ1) {
        try {
            Problem problem;
            if (felicitat && succ1) problem = new Problem(estat, new AzamonSuccessorFunction(), new AzamonGoalTest(), new AzamonHeuristicFunction2());
            else if (felicitat) problem = new Problem(estat, new AzamonSuccessorFunction2(), new AzamonGoalTest(), new AzamonHeuristicFunction2());
            else if (succ1) problem = new Problem(estat, new AzamonSuccessorFunction(), new AzamonGoalTest(), new AzamonHeuristicFunction1());
            else problem = new Problem(estat, new AzamonSuccessorFunction2(), new AzamonGoalTest(), new AzamonHeuristicFunction1());
            Search search = new SimulatedAnnealingSearch(10000, 100, 5, 0.001);
            SearchAgent agent = new SearchAgent(problem, search);

            System.out.println("\n" + ((AzamonEstado) search.getGoalState()).toString());
            System.out.println("\n" + ((AzamonEstado) search.getGoalState()).correspondenciasToString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

}
