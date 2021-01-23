package model;

/**
 *
 * @author Danny Loor
 */
public class Pesable {

    private String name;
    private Long weight;

    public Pesable(String name, Long weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeight() {
        return weight;
    }

    public void Long(Long weight) {
        this.weight = weight;
    }

    public double getWeightKB() {
        double d = (double) weight / 1024;
        return (double) ((int) (d * 10)) / 10;
    }

    public double getWeightMB() {
        double d = (double) (weight / 1024) / 1024;
        return (double) ((int) (d * 10)) / 10;
    }

    public double getWeightGB() {
        double d = (double) ((weight / 1024) / 1024) / 1024;
        return (double) ((int) (d * 100)) / 100;
    }

    @Override
    public String toString() {
        if (weight > (Math.pow(1024, 3))) {
            return name + " - " + getWeightGB() + " GB";
        } else if (weight < (Math.pow(1024, 2))) {
            return name + " - " + getWeightKB() + " KB";
        }
        return name + " - " + getWeightMB() + " MB";
    }
}
