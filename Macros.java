public class Macros {
    String name;
    float calories;
    float protein;
    float carbs;
    float fat;
    public Macros(String name){
        this.name=name;
    }
    public Macros(String name, float calories, float protein, float carbs, float fat){
        this.name=name;
        this.calories=calories;
        this.carbs= carbs;
        this.fat=fat;
        this.protein=protein;
    }
}
