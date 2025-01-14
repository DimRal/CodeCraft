
public class Macros {
    String name;
    double calories;
    double protein;
    double carbs;
    double fat;
    Double bmr ;
    int thesi;
    int fores;

    //για την πρωτη θεση με το ονομα
    public Macros(String name){
        this.name=name;
    }
    public Macros(int thesi,int fores){
        this.thesi=thesi;
        this.fores=fores;
    }

    //για τισ υπολοιπες

    public Macros(String name, double calories, double protein, double carbs, double fat,double bmr){
        this.name=name;
        this.calories=calories;
        this.carbs= carbs;
        this.fat=fat;
        this.protein=protein;
        this.bmr=bmr;
    }
    public Macros(String name, double calories, double protein, double carbs, double fat){
        this.name=name;
        this.calories=calories;
        this.carbs= carbs;
        this.fat=fat;
        this.protein=protein;
    }
    public Macros(double calories, double protein, double carbs, double fat){
        this.calories=calories;
        this.carbs= carbs;
        this.fat=fat;
        this.protein=protein;
    }

}
