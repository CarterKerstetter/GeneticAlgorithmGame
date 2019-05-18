import java.util.*;

public class Environment {
    OrganismComparator comparator;
    ArrayList<Organism> population = new ArrayList<Organism>();

    public Environment(double[] fitnessFunction) {
        this.comparator = new OrganismComparator(fitnessFunction);
    }

    public void populate(int amount) {
        for(int i=0;i<amount;i++) {
            populate(Organism.build(10));
        }
    }

    public void populate(Organism organism) {
        this.population.add(organism);
    }

    public void populate(ArrayList<Organism> population) {
        this.population.addAll(population);
    }

    public void passTime(int generations) {
        Random random = new Random();
        passTime(generations, random.nextLong());
    }

    public void passTime(int generations, long seed) {
        Random random = new Random(seed);
        for(int i=0;i<generations;i++) {
            ArrayList<Organism> nextGeneration = new ArrayList<Organism>();
            int totalSurvivors = this.population.size()/2;
            Collections.sort(this.population, this.comparator);
            ArrayList<Organism> childPopulation = new ArrayList<Organism>();
            for(int j=0;j<totalSurvivors;j++) {
                Organism parent1 = this.population.get(random.nextInt(totalSurvivors));
                Organism parent2 = this.population.get(random.nextInt(totalSurvivors));
                Organism child = Organism.mate(parent1, parent2);
                childPopulation.add(child);
            }
            for(int j=0;j<totalSurvivors;j++) {
                childPopulation.add(this.population.get(j));
            }
            this.population = childPopulation;
        }
    }

    public Organism getMostFitOrganism() {
        Collections.sort(this.population, this.comparator);
        return this.population.get(0);
    }

    class OrganismComparator implements Comparator<Organism> {
        double[] fitnessFunction;

        public OrganismComparator(double[] fitnessFunction) {
            this.fitnessFunction = fitnessFunction;
        }

        private double calculateFitness(Organism organism) {
            double[] DNA = organism.getDNA();
            double fitness = 0;
            for(int i=0;i<fitnessFunction.length;i++) {
                fitness += fitnessFunction[i] * DNA[i];
            }
            return fitness;
        }

        @Override
        public int compare(Organism organism1, Organism organism2) {
            double fitness1 = calculateFitness(organism1);
            double fitness2 = calculateFitness(organism2);
            return Double.compare(fitness1, fitness2)*-1;
        }
    }
}
