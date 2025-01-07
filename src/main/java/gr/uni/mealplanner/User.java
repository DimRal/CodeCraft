package gr.uni.mealplanner;
public class User {
    private String username;
    private String email;
    private String password;
    private String gender; // male or female
    private int age;
    private String sex;
    private double height; // in cm
    private double weight; // in kg
    private String goal; // lose weight, gain muscle, maintain weight
    private String pref; //food preferences
    private String sports1;
    private String training;
    private String sports2;
    private String practice;


    // Constructor
    public User(String username, String email, String password, int age,String gender, double height, double weight,String goal,String pref,String sports1,String training,String sports2,String practice) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
        this.pref = pref;
        this.sports1 = sports1;
        this.training = training;
        this.sports2 = sports2;
        this.practice = practice;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public void setSports1(String sports1) {
        this.sports1 = sports1;
    }
    public String getSports1() {
        return sports1;
    }
    public void setSports2(String sports2) {
        this.sports2 = sports2;
    }

    public String getSports2() {
        return sports2;
    }
    public void setTraining(String training) {
        this.training = training;
    }
    public String getTraString() {
        return training;
    }
    public void setPractice(String practice) {
        this.practice = practice;
    }
    public String getPractice(){
        return practice;
    }
}
