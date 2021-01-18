package model;

/**
 *
 * @author Danny Loor
 */
public abstract class Pesable {
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
    
    public double getKB(){
        double d = (double) weight/1024;
        return (double)((int)(d*10))/10;
    }
    
    public double getMB(){
        double d = (double)(weight/1024)/1024;
        return (double)((int)(d*10))/10;
    }
    
    public double getGB(){
        double d = (double)((weight/1024)/1024)/1024;
        return (double)((int)(d*100))/100;
    }

    @Override
    public String toString() {
        if (weight > (Math.pow(1024, 3))){
            return name + " - " + getGB() + " GB";
        }
        else if (weight < (Math.pow(1024, 2))){
            return name + " - " + getKB() + " KB";
        }
        return name + " - " + getMB() + " MB";
    }
}
