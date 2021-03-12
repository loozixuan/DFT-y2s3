package domain;

public class BMI {
    private double weight;
    private double height;

    public BMI() {
    }
    
    public BMI(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public double getBMI() {
        return weight / (height * height);
    }

    public String getStatus() {
        double bmi = getBMI();
        if (bmi < 16) {
            return "Seriously underweight";
        } else if (bmi < 18) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 29) {
            return "Overweight";
        } else if (bmi < 35) {
            return "Obese Class I";
        } else {
            return "Obese Class II, III";
        }
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
    
}