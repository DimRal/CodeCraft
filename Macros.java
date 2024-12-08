public class Macros {
    String name;
    float calories;
    float protein;
    float carbs;
    float fat;
    double calories2;
    double protein2;
    double carbs2;
    double fat2;
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
    public Macros(String name, float calories, float protein, float carbs, float fat){
        this.name=name;
        this.calories=calories;
        this.carbs= carbs;
        this.fat=fat;
        this.protein=protein;
    }

    public Macros(String name, double calories, double protein, double carbs, double fat,double bmr){
        this.name=name;
        this.calories2=calories;
        this.carbs2= carbs;
        this.fat2=fat;
        this.protein2=protein;
        this.bmr=bmr;
    }

}
