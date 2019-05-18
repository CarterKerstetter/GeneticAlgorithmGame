import java.util.Arrays;
import java.util.Random;

public class Organism {
    private double[] DNA;
    private double minDNA = -5;
    private double maxDNA = 5;


    public Organism(double[] DNA) {
        this.DNA = DNA;
    }

    public Organism(double[] DNA, double minDNA, double maxDNA) {
        this.DNA = DNA;
        this.minDNA = minDNA;
        this.maxDNA = maxDNA;
    }

    double[] getDNA() {
        return this.DNA;
    }

    double getMinDNA() {
        return this.minDNA;
    }

    double getMaxDNA() {
        return this.maxDNA;
    }

    @Override
    public String toString() {
        String string = "DNA: " + Arrays.toString(DNA) + "\nminDNA: " + minDNA + "\nmaxDNA: " + maxDNA;
        return string;
    }

    public static Organism mate(Organism organism1, Organism organism2) {
        Random random = new Random();
        return mate(organism1, organism2, random.nextLong());
    }

    public static Organism mate(Organism parent1, Organism parent2, long seed) {
        double[] DNA1 = parent1.getDNA();
        double minDNA1 = parent1.getMinDNA();
        double maxDNA1 = parent1.getMaxDNA();
        double[] DNA2 = parent2.getDNA();
        double minDNA2 = parent2.getMinDNA();
        double maxDNA2 = parent2.getMaxDNA();
        double mutationRange = 0.4*(maxDNA1 - minDNA1);
        if(DNA1.length == DNA2.length && minDNA1 == minDNA2 && maxDNA1 == maxDNA2) {
            double[] childDNA = new double[DNA1.length];
            Random random = new Random(seed);
            for(int i = 0; i< childDNA.length; i++) {
                int parentStrand = random.nextInt(1);
                double newGene;
                if(parentStrand == 0) {
                    newGene = DNA1[i];
                }
                else {
                    newGene = DNA2[i];
                }
                //mutation between 0 and 1
                double mutation = random.nextDouble();
                //mutation centered on 0 (-0.5 to 0.5)
                mutation -= 0.5;
                //multiplied to be scaled to the range of the gene
                mutation *= mutationRange;
                newGene += mutation;
                if(newGene < minDNA1) {
                    newGene = minDNA1;
                }
                if(newGene > maxDNA1) {
                    newGene = maxDNA1;
                }
                childDNA[i] = newGene;
            }
            Organism child = new Organism(childDNA);
            return child;
        }
        else {
            //incompatible species
            return null;
        }
    }

    public static Organism build(int length) {
        Random random = new Random();
        return build(length, -5, 5, random.nextLong());
    }

    public static Organism build(int length, double minDNA, double maxDNA, long seed) {
        double[] DNA = new double[length];
        Random random = new Random(seed);
        for(int i=0;i<length;i++) {
            double gene = random.nextDouble();
            gene = gene*(maxDNA - minDNA) + minDNA;
            DNA[i] = gene;
        }
        Organism organism = new Organism(DNA, minDNA, maxDNA);
        return organism;
    }
}
