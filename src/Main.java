public class Main {
    public static void main(String[] args) {
        double[] fitnessFunction = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        Environment environment = new Environment(fitnessFunction);
        environment.populate(10);
        System.out.println(environment.getMostFitOrganism());
        System.out.println(calculateFitness(environment.getMostFitOrganism()));
        environment.passTime(10);
        System.out.println(environment.getMostFitOrganism());
        System.out.println(calculateFitness(environment.getMostFitOrganism()));
        environment.passTime(10);
        System.out.println(environment.getMostFitOrganism());
        System.out.println(calculateFitness(environment.getMostFitOrganism()));
        environment.passTime(10);
        System.out.println(environment.getMostFitOrganism());
        System.out.println(calculateFitness(environment.getMostFitOrganism()));
        environment.passTime(10);
        System.out.println(environment.getMostFitOrganism());
        System.out.println(calculateFitness(environment.getMostFitOrganism()));
        environment.passTime(10);
        System.out.println(environment.getMostFitOrganism());
        System.out.println(calculateFitness(environment.getMostFitOrganism()));
        environment.passTime(10);
        System.out.println(environment.getMostFitOrganism());
        System.out.println(calculateFitness(environment.getMostFitOrganism()));
        environment.passTime(10);
        System.out.println(environment.getMostFitOrganism());
        System.out.println(calculateFitness(environment.getMostFitOrganism()));
    }

    private static double calculateFitness(Organism organism) {
        double[] fitnessFunction = new double[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        double[] DNA = organism.getDNA();
        double fitness = 0;
        for(int i=0;i<fitnessFunction.length;i++) {
            fitness += fitnessFunction[i] * DNA[i];
        }
        return fitness;
    }
}
